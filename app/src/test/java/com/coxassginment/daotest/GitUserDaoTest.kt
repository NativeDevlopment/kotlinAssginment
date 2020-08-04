package com.coxassginment.daotest

import com.coxassginment.BaseUnitTest
import com.coxassginment.data.local.dao.GitUsersDao
import com.coxassginment.data.local.entity.GitUsers
import com.coxassginment.data.local.database.AppDb
import com.coxassginment.utils.TestCoroutineRule
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate

@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnit4::class)
class GitUserDaoTest :BaseUnitTest() {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var gitUsersDao: GitUsersDao
    @Before
    override fun setUp(){
        super.setUp()
        gitUsersDao=appDb.gitUsersDao()
    }
    @After
    override fun tearDown() {

    }

    @Test
    fun should_Insert_User_Item() {
            testCoroutineRule.runDispatcherTest {
                val gitUsers = GitUsers(
                    8110201,
                    "moyheen",
                    "MDQ6VXNlcjgxMTAyMDE",
                    "https://avatars2.githubusercontent.com/u/8110201?v=4"
                    ,
                    "",
                    "https://api.github.com/users/moyheen",
                    "https://github.com/moyheen",
                    "https://api.github.com/users/moyheen/followers"
                )
                gitUsersDao.insert(gitUsers)
                val userTest = gitUsersDao.findById(gitUsers.id)
                Assert.assertEquals(gitUsers.login, userTest.login)
            }
        }





}