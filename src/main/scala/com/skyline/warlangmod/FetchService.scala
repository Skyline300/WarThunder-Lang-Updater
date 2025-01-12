package com.skyline.warlangmod

import scala.sys.process._
import java.net.http._
import java.net.http.HttpResponse.{BodyHandler, BodyHandlers}
import java.time.Duration
import java.net.{ProxySelector, URI}
import java.io.FileWriter


object FetchService {
      def fetchFile(url: String): Unit = {
        val client = HttpClient
          .newBuilder()
          .proxy(ProxySelector.getDefault)
          .build()

        val httpReq = HttpRequest.newBuilder()
          .uri(new URI(url))
          .timeout(Duration.ofSeconds(20))
          .GET()
          .build()

        val response = client.send(httpReq,BodyHandlers.ofString())
        val body = response.body()

        val fileWriter = new FileWriter("src/test.csv")
        fileWriter.write(body)
        fileWriter.close()
      }


}
