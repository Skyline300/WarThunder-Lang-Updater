package com.skyline.warlangmod

import com.skyline.warlangmod.cli.Params.TranslationFileType

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

  def runOnline(inputFileName: String, filePath: String, fileType:TranslationFileType):Unit = {
    val parsingService = ParsingService.instance
    val translationOverwrite = TranslationOverwriteService.instance
    val outputService = Output.instance
    fileType match {
      case TranslationFileType.Units => ???
      case TranslationFileType.Weaponry => ??? //What the fuck am i doing
    }
    Try {
      FetchService.fetchFile(filePath)
    }

    val moddedTranslations = parsingService.translations(inputFileName)
  }
}
