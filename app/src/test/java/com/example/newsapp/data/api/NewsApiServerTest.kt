package com.example.newsapp.data.api

import com.example.newsapp.newsapiclient.data.api.NewsApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class NewsApiServerTest {

    private lateinit var service: NewsApi
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }


    private fun enqueueMockResponse(fileName: String) {
        var inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        var source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopNewsHeadLines_sentRequest_RecivecorrectResponse() {
        runBlocking {
            enqueueMockResponse("newsresponsejson.json")
            var response = service.getTopNewsHeadLines("us", 1).body()
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=6866e0e96a8a41149e22")
        }
    }

    @Test
    fun getTopNewsHeadLines_sentRequest_RecivecorrectResponseSizePage() {
        runBlocking {
            enqueueMockResponse("newsresponsejson.json")
            var response = service.getTopNewsHeadLines("us", 1).body()
            var articList=response?.articles
            assertThat(articList?.size).isEqualTo(20)

        }
    }

    @Test
    fun getTopNewsHeadLines_sentRequest_RecivecorrectResponseContent() {
        runBlocking {
            enqueueMockResponse("newsresponsejson.json")
            var response = service.getTopNewsHeadLines("us", 1).body()
            var articList=response!!.articles
            var articl=articList[0]
            assertThat(articl.author).isEqualTo("Jim Sciutto, Chief National Security Correspondent")
            assertThat(articl.url).isEqualTo("https://www.cnn.com/2022/03/29/politics/us-intelligence-russia-ukraine-kyiv-strategy/index.html")

        }
    }
}