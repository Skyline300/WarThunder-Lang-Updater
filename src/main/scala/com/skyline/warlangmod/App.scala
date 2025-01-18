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

  def runOnline(inputFileName: String, filePath: String, fileType:Option[TranslationFileType]):Unit = {
    val file = fileType.get
    val parsingService = ParsingService.instance
    val translationOverwrite = TranslationOverwriteService.instance
    val outputService = Output.instance
    Try {
      FetchService.fetchFile(filePath)
    }

    val moddedTranslations = parsingService.translations(inputFileName)
  }
}
