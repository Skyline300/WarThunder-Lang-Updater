package com.skyline.warlangmod

import java.io.FileWriter
import java.nio.file.Files

class ParsingServiceInstanceSpec extends munit.FunSuite {
  test("ParsingServiceInstance should parse a WarThunder Translation file format") {
    val translations = Translations(
      languages = Vector(
        Language("something", "good")
      )
    )
    val tmpFile = Files.createTempFile("ParsingServiceInstanceTestFile", null)
    val writer = new FileWriter(tmpFile.toFile)
    writer.write(translations.render())
    writer.close()
    val parsedTranslation = ParsingService.instance.translations(tmpFile.toAbsolutePath.toString)
    assertEquals(parsedTranslation, translations)
  }
}
