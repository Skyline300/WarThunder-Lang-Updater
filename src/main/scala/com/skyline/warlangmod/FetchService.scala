package com.skyline.warlangmod

import scala.sys.process._
import java.net.http._
import java.net.http.HttpResponse.{BodyHandler, BodyHandlers}
import java.time.Duration
import java.net.{ProxySelector, URI}
import java.io.FileWriter


object FetchService {
      def fetchFile(): Unit = {
        val client = HttpClient
          .newBuilder()
          .proxy(ProxySelector.getDefault)
          .build()
        val httpReq = HttpRequest.newBuilder()
          .uri(new URI("https://raw.githubusercontent.com/gszabi99/War-Thunder-Datamine/1bc153b0d22ac06719f6ce1ba2c25b567285cc99/lang.vromfs.bin_u/lang/units.csv"))
          .timeout(Duration.ofSeconds(20))
          .GET()
          .build()
        val response = client.send(httpReq,BodyHandlers.ofString())
        val body = response.body()

        val fileWriter = new FileWriter("src/output.csv")
        fileWriter.write(body)
        fileWriter.close()
      }


}
