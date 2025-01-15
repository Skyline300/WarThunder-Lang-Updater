package com.skyline.warlangmod

import scala.util.Try

object App {
  def runOffline(inputFileName: String, originalFile: String, outputFileName: String): Unit = {

    val parsingService = ParsingService.instance
    val translationOverwrite = TranslationOverwriteService.instance
    val outputService = Output.instance


    val moddedTranslations = parsingService.translations(inputFileName)
    val originalUpdatedTranslations = parsingService.translations(originalFile)
    val updatedModdedFile = translationOverwrite.overwrite(originalUpdatedTranslations, moddedTranslations)


    outputService.write(updatedModdedFile, outputFileName)
  }

  def runOnline(inputFileName: String, filePath: String, fileType:String):Unit = {
    val parsingService = ParsingService.instance
    val translationOverwrite = TranslationOverwriteService.instance
    val outputService = Output.instance
    Try {
      FetchService.fetchFile(filePath)
    }

    val moddedTranslations = parsingService.translations(inputFileName)
  }
}
