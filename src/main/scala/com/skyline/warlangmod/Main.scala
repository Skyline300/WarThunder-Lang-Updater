package com.skyline.warlangmod

import java.io.FileWriter

object Main {
  def main(args: Array[String]): Unit = {

    if(args.length < 2)
      System.exit(1) // No input file

    val parsingService                = ParsingService()
    val translationOverwriter         = TranslationOverwriteService()

    val moddedTranslations            = parsingService.translations(args(0))
    val originalUpdatedTranslations   = parsingService.translations(args(1))

    val uptodateModdedTranslationFile = translationOverwriter.overwrite(originalUpdatedTranslations, moddedTranslations)

    // TODO Move it out of here to its own service
    // Writes all of the updates
    val updatedFile = new FileWriter("unitsMod.csv")
    updatedFile.write(uptodateModdedTranslationFile.render())
    updatedFile.close()
  }
}
