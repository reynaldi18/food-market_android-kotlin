package com.movieApp.app

import android.app.Application
import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.movieApp.app.api.ApiService
import com.movieApp.app.core.CoreListAdapter
import com.movieApp.app.helper.getAppContext
import com.movieApp.app.storage.SessionStorage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.startKoin
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import retrofit2.Response
import java.io.IOException

open class CoreTest {

    private var resourceEnabled = true

    @Mock
    protected lateinit var api: ApiService

    @Mock
    protected lateinit var session: SessionStorage

    @Mock
    protected lateinit var db: FirebaseFirestore

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Mock
    internal lateinit var mockResources: Resources

    init {
        if (KoinContextHandler.getOrNull() == null) {
            startKoin { androidContext(mock(Application::class.java)) }
        }
        if (resourceEnabled) mockStrings()
    }

    @Before
    open fun setup() {
    }

    protected fun mockValidationName() {
        mockInt(R.integer.min_name, 3)
        mockInt(R.integer.max_name, 32)
    }

    protected fun mockValidationPhone() {
        mockInt(R.integer.min_phone, 10)
        mockInt(R.integer.max_phone, 15)
    }

    protected fun mockValidationPassword() {
        mockInt(R.integer.min_password, 8)
        mockInt(R.integer.max_password, 32)
    }

    protected fun mockValidationOTP() {
        mockInt(R.integer.min_otp, 4)
    }

    private fun mockInt(resId: Int, value: Int) {
        `when`(getAppContext().resources).thenReturn(mockResources)
        `when`(mockResources.getInteger(resId)).thenReturn(value)
    }

    fun <T> String.toGson(clazz: Class<T>): T = Gson().fromJson(this, clazz)

    protected fun <T> createResponse(
        code: Int,
        samplePath: String,
        clazz: Class<T>,
    ): Response<T>? {
        val json = readFromFile(samplePath)
        return if (code in 200..299) {
            val resp = Response.success(code, json.toGson(clazz))
            resp
        } else {
            Response.error(
                code,
                json.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
        }
    }

    // a method to read text file.
    @Throws(IOException::class)
    fun readFromFile(filename: String?): String {
        val inputStream = javaClass.getResourceAsStream("/" + filename!!)
        val stringBuilder = StringBuilder()
        var i: Int
        val b = ByteArray(4096)
        while (inputStream!!.read(b).also { i = it } != -1) {
            stringBuilder.append(String(b, 0, i))
        }
        return stringBuilder.toString()
    }

    /** Mock all string resource from android */
    private fun mockStrings() {
        `when`(getAppContext().getString(anyInt())).thenAnswer { mock ->
            (mock.arguments[0] as Int).toString()
        }
    }

    protected fun spyAdapter(adapter: CoreListAdapter): CoreListAdapter {
        val spyAdapter = spy(adapter)
        Mockito.doNothing().`when`(spyAdapter).notifyDataSetChanged()
        return spyAdapter
    }

    protected fun setResourceDisabled() {
        resourceEnabled = false
    }
}
