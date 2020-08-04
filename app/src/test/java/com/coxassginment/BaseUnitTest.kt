package com.coxassginment

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import com.coxassginment.data.local.database.AppDb
import com.coxassginment.di.module.application.*
import io.kotlintest.mock.`when`
import io.reactivex.schedulers.TestScheduler
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.Answer
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Retrofit


@RunWith(PowerMockRunner::class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(
    Log::class,
    Handler::class,
    Looper::class,
    TextUtils::class,
    BuildConfig::class
)
abstract class BaseUnitTest{
    lateinit var context: Context
    lateinit var mockWebServer: MockWebServer
    lateinit var repositoryModule: RepositoryModule
    lateinit var dbModule: DbModule
    lateinit var apiModule: ApiModule
    lateinit var appDb: AppDb
    lateinit var retrofit: Retrofit
    lateinit var testScheduler: TestScheduler

    @Before
    @Throws(Exception::class)
    open fun setUp() {
        // Initializes the mock environment
        MockitoAnnotations.initMocks(this)

        // Initializes the mock webserver
        mockWebServer = MockWebServer()
        startMockWebserver()

        // Mocks the generic android dependencies such as Context, Looper, etc.
        mockAndroidDependencies()

        // Mocks android logs
        mockLogs()



        // Initializes the retrofit dependencies
        initDependencies()


    }

    @After
    open fun tearDown(){
        stopMockWebserver()
    }

    /**
     * Method which starts the mockwebserver
     */
    private fun startMockWebserver(){
        mockWebServer.start(1234)
    }

    /**
     * Method which stops the mock webserver
     */
    private fun stopMockWebserver(){
        mockWebServer.shutdown()
    }


    /**
     * This function will mock all the android Log related dependencies
     */
    private fun mockLogs(){
        PowerMockito.mockStatic(Log::class.java)
        val logAnswer = Answer<Void> { invocation ->
            val tag = invocation.arguments[0] as String
            val msg = invocation.arguments[1] as String
            // @Lajesh : Why we are using println here
//            println(invocation.method.name.toUpperCase() + "/[" + tag + "] " + msg)
            null
        }


        PowerMockito.doAnswer(logAnswer).`when`(Log::class.java, "d",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        PowerMockito.doAnswer(logAnswer).`when`(Log::class.java, "i",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        PowerMockito.doAnswer(logAnswer).`when`(Log::class.java, "w",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        PowerMockito.doAnswer(logAnswer).`when`(Log::class.java, "e",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        PowerMockito.doAnswer(logAnswer).`when`(Log::class.java, "wtf",
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )

        PowerMockito.doAnswer { invocation ->
            val s = invocation.arguments[0] as String
            s.isEmpty()
        }.`when`(TextUtils::class.java, "isEmpty", ArgumentMatchers.anyString())

    }



    /**
     * This method initializes the retrofit module
     */
    private fun initDependencies(){
        repositoryModule = RepositoryModule()
        apiModule = ApiModule()
        dbModule= DbModule()
       appDb= dbModule.provideAppDatabase(context)
        val retrofitModule = RetrofitModule()
        val interceptor = Interceptors()
       val interceptors = interceptor.networkConnectionInterceptor(context)
        val okHttpClientModule = OkHttpClientModule()
        val createLoggingInterceptor = okHttpClientModule.httpLoggingInterceptor()
        val httpClient = okHttpClientModule.okHttpClient(/*cache,*/ createLoggingInterceptor,interceptors)
        val gson = retrofitModule.gson()
        val gsonConverter = retrofitModule.gsonConverterFactory(gson)
        val serverUrl = mockWebServer.url("/").toString()
        retrofit = retrofitModule.provideRetrofit(serverUrl, httpClient, gsonConverter)
    }

    private fun mockAndroidDependencies(){
        context = PowerMockito.mock(Context::class.java)
        testScheduler = TestScheduler()
        PowerMockito.mockStatic(Looper::class.java)
        PowerMockito.mockStatic(Handler::class.java)
        PowerMockito.mockStatic(TextUtils::class.java)
        //PowerMockito.`when`(Looper.getMainLooper()).thenReturn(null)
        PowerMockito.whenNew(Handler::class.java).withAnyArguments().thenReturn(null)
    }
}