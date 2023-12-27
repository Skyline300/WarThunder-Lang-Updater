package com.skyline.warlangmod

import java.io.FileWriter
import scala.collection.mutable.ArrayBuffer
import scala.io.{BufferedSource, Source}
object Main {
  def main(args: Array[String]): Unit = {

    if(args.length < 2) {
      println("not enough args")
      System.exit(1) // No input file
    }

    // Parses modded file
    val myLangFile: BufferedSource = Source.fromFile(args(0))
    val parsedModdedLangFile = Language.parse(myLangFile, true)
    myLangFile.close()

    // Parses new datamined file or whatnot 
    val updatedLangFile: BufferedSource = Source.fromFile(args(1))
    val parsedUpdateLangFile = Language.parse(updatedLangFile, false)
    updatedLangFile.close()

    // Inserts the new object from the WarThunder updated language file if there's something
    parsedUpdateLangFile.languages.zipWithIndex.foreach { case (language, index) =>
      val moddedTranslationAtIndex = parsedModdedLangFile.languages(index)
      if(language.objName != moddedTranslationAtIndex.objName && moddedTranslationAtIndex.modded) {
                                                                  // headOption because USA USA
        val fuckOtherLanguagesUSAUSA = Language(language.objName, language.translations.headOption.toList, false)
        parsedModdedLangFile.languages.insert(index, fuckOtherLanguagesUSAUSA)
      }
    }

    // Remove stupid duplicates
    val output = Translations(parsedModdedLangFile.languages.distinctBy(_.objName))

    // Writes all of the updates
    val w = new FileWriter("src/unitsMod.csv")
    w.write(output.toString)
    w.close()
  }
}