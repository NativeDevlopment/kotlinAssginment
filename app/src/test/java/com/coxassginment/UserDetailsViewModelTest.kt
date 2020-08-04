package com.coxassginment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.presentation.ui.UserDetailsViewModel
import com.coxassginment.data.local.database.AppDb
import com.coxassginment.utils.TestCoroutineRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate

@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnit4::class)
class UserDetailsViewModelTest:BaseUnitTest() {
    //It will tell JUnit to force tests to be executed synchronously, especially when using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: UserDetailsViewModel

    @Before
    override fun setUp(){
        super.setUp()
        viewModel = UserDetailsViewModel(appDb.gitUsersDao())

    }

    @Test
    fun testFetchUserSuccess(){
    testCoroutineRule.runDispatcherTest {
      viewModel.getUserData(8110201)}
    }

}