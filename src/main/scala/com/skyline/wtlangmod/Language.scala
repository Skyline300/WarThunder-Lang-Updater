package com.skyline.wtlangmod

import scala.collection.mutable.ArrayBuffer
import scala.io.BufferedSource
import scala.util.Try


case class Language(objName: String, translations: List[String], modded: Boolean){
  override def toString: String =
    s"$objName;${translations.mkString(";")}"
}
case class Translations(languages: ArrayBuffer[Language]) {
  override def toString: String =
    languages.mkString("\n")
}

object Language {
  // sanitises the file and whatnot, making sure there's no empty lines
  def parse(languageFile: BufferedSource, modded: Boolean): Translations = {
    val iter = languageFile.getLines()
    val languages = iter.flatMap { str =>
      val sanitizedString = str.replace("\"", "")
      val splitString = sanitizedString.split(";")
      Try {
        val objName = splitString(0)
        if(objName.isEmpty) {
          throw new RuntimeException("This is gay")
        }
        // gets the all the translations for the languages and whatnot,and then we filter it to Eng only
        val (_, translations) = splitString.splitAt(1)
        Language(objName, translations.headOption.toList, modded)
      }.toOption
    }
    Translations(ArrayBuffer.from(languages).distinctBy(_.objName))
  }
}
