package com.skyline.warlangmod

import java.io.FileWriter

object Main {
  def main(args: Array[String]): Unit = {

    if(args.length < 2)
      System.exit(1) // No input file

    val parsingService                = ParsingService()
    val translationOverwriter         = TranslationOverwriteService()
    val outputService                 = Output()

    val moddedTranslations            = parsingService.translations(args(0))
    val originalUpdatedTranslations   = parsingService.translations(args(1))

    val uptodateModdedTranslationFile = translationOverwriter.overwrite(originalUpdatedTranslations, moddedTranslations)

    outputService.write(uptodateModdedTranslationFile)
  }
}
