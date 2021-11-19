package com.movieApp.app.repo

import com.movieApp.app.CoreTest
import com.movieApp.app.R
import com.movieApp.app.api.Resource
import com.movieApp.app.api.response.CoreRes
import com.movieApp.app.constant.Session
import com.movieApp.app.helper.getAppContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class UserRepoTest : CoreTest() {
    private lateinit var repo: UserRepo

    @Before
    override fun setup() {
        super.setup()
        repo = UserRepo(api, session)
    }

    @Test
    fun `Return boolean based on auth token when isLoggedIn() called`() {
        `when`(session.get<Auth?>(Session.AUTH)).thenReturn(null)
        assertFalse(repo.isLoggedIn())
        `when`(session.get<Auth?>(Session.AUTH)).thenReturn(Mockito.mock(Auth::class.java))
        assertTrue(repo.isLoggedIn())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return common error when all API Call has no connection`() =
        runBlockingTest {
            val message = getAppContext().getString(R.string.error_common)
            given(api.init()).willAnswer { throw IOException() }
            given(api.login(any())).willAnswer { throw IOException() }
            given(api.register(any())).willAnswer { throw IOException() }
            given(api.sendOTP(any())).willAnswer { throw IOException() }
            given(api.changePassword(any())).willAnswer { throw IOException() }
            given(api.verification(any())).willAnswer { throw IOException() }
            given(api.getProfile()).willAnswer { throw IOException() }

            Assert.assertEquals(message, repo.initApps().message)
            Assert.assertEquals(message, repo.login("", "").message)
            Assert.assertEquals(
                message, repo.register(
                    "", "", "", "", ""
                ).message
            )
            Assert.assertEquals(message, repo.forgotPassword("").message)
            Assert.assertEquals(message, repo.changePassword("").message)
            Assert.assertEquals(message, repo.verification("").message)
        }

    @Test
    fun `Return false when onboarding showed already`() {
        `when`(session.get<Boolean>(Session.ONBOARDING_SHOWED)).thenReturn(false)
        assertFalse(repo.isOnboardingShowed())
    }

    @Test
    fun `Return true when onboarding showed already`() {
        `when`(session.get<Boolean>(Session.ONBOARDING_SHOWED)).thenReturn(true)
        assertTrue(repo.isOnboardingShowed())
    }

    @Test
    fun `Set flag to session when setOnboardingShowed called`() {
        repo.setOnboardingShowed()
        Mockito.verify(session, times(1)).put(Session.ONBOARDING_SHOWED, true)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return error from response when init API call failed`() =
        runBlockingTest {
//            MockInterceptor.setResponse(403, readFromFile("error.json"))
            val response = createResponse(403, "error.json", Init::class.java)
            `when`(api.init()).thenReturn(response)
            val res = repo.initApps()
            Assert.assertNotNull(res.message)
            Assert.assertNull(res.data?.initialId)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return object when init API call success`() =
        runBlockingTest {
            val response = createResponse(201, "init.json", Init::class.java)
            `when`(api.init()).thenReturn(response)
            val res = repo.initApps()
            Assert.assertNotNull(res.data?.initialId)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return error from response when login failed`() =
        runBlockingTest {
            val response = createResponse(403, "error.json", Auth::class.java)
            `when`(api.login(any())).thenReturn(response)
            val res = repo.login("", "")
            Assert.assertNotNull(res.message)
            Assert.assertNull(res.data)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return resource success when login success`() =
        runBlockingTest {
            val response = createResponse(200, "login.json", Auth::class.java)
            `when`(api.login(any())).thenReturn(response)
            val res = repo.login("", "")
            Mockito.verify(session, times(1)).put<Auth?>(anyString(), any())
            assertTrue(res is Resource.Success)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return error from response when forgot password failed`() =
        runBlockingTest {
            val response = createResponse(403, "error.json", SampleMessage::class.java)
            `when`(api.sendOTP(any())).thenReturn(response)
            val res = repo.forgotPassword("")
            Assert.assertNotNull(res.message)
            Assert.assertNull(res.data?.message)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return user object when forgot password success`() =
        runBlockingTest {
            val response = createResponse(200, "success_message.json", SampleMessage::class.java)
            `when`(api.sendOTP(any())).thenReturn(response)
            val res = repo.forgotPassword("")
            Assert.assertNotNull(res.message)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return error from response when verification OTP failed`() =
        runBlockingTest {
            val response = createResponse(403, "error.json", SampleMessage::class.java)
            `when`(api.verification(any())).thenReturn(response)
            val res = repo.verification("")
            Assert.assertNotNull(res.message)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return message when verification OTP success`() =
        runBlockingTest {
            val response = createResponse(200, "success_message.json", SampleMessage::class.java)
            `when`(api.verification(any())).thenReturn(response)
            val res = repo.verification("")
            Assert.assertNotNull(res.message)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return error from response when register failed`() =
        runBlockingTest {
            val response = createResponse(403, "error.json", CoreRes::class.java)
            `when`(api.register(any())).thenReturn(response)
            val res = repo.register("", "", "", "", "")
            Assert.assertNotNull(res.message)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return success message when forgot password success`() =
        runBlockingTest {
            val response = createResponse(200, "register.json", CoreRes::class.java)
            `when`(api.register(any())).thenReturn(response)
            val res = repo.register("", "", "", "", "")
            Assert.assertNotNull(res.data)
            Mockito.verify(session, times(1)).put(anyString(), anyString())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return error from response when change password failed`() =
        runBlockingTest {
            val response = createResponse(400, "error.json", CoreRes::class.java)
            `when`(api.changePassword(any())).thenReturn(response)
            val res = repo.changePassword("")
            assertTrue(res is Resource.Error)
            Assert.assertNotNull(res.message)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Return success message when change password success`() =
        runBlockingTest {
            val response = createResponse(200, "success.json", CoreRes::class.java)
            `when`(api.changePassword(any())).thenReturn(response)
            val res = repo.changePassword("")
            assertTrue(res is Resource.Success)
            Assert.assertNotNull(res.message)
        }
}
