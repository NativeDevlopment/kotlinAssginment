package com.coxassginment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.network.GitRepository
import com.coxassginment.data.network.IGitApi
import com.coxassginment.presentation.ui.UserListViewModel
import com.coxassginment.utils.MockResponse
import com.coxassginment.data.local.database.AppDb
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import java.net.HttpURLConnection

@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnit4::class)
class UserListViewModelTest:BaseUnitTest() {
    //It will tell JUnit to force tests to be executed synchronously, especially when using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var iGitApi: IGitApi
    private lateinit var viewModel: UserListViewModel
    private lateinit var gitRepository: GitRepository
    private lateinit var gitUsersDao: GitUsersDao
    private lateinit var appDb: AppDb

    @Before
    override fun setUp(){
        super.setUp()
        iGitApi = apiModule.provideGitApi(retrofit = retrofit)
        appDb=dbModule.provideAppDatabase()
        gitUsersDao=dbModule.provideGitUsersDao(appDb)
        gitRepository = repositoryModule.provideGitRepository(iGitApi,gitUsersDao)
        viewModel = UserListViewModel(gitRepository,gitUsersDao)

    }

    @Test
    fun testFetchCarDetailSuccess(){
        mockWebServer.enqueue(
            MockResponse.createMockResponse(
                "get_git_user_list_success",
                HttpURLConnection.HTTP_OK
            )
        )
        Assert.assertNotNull(viewModel)
        Assert.assertNull("error")
    }

}