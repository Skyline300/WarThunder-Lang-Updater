package com.skyline.warlangmod

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
        val (_, translations) = splitString.splitAt(1)
        Language(objName, translations.toList, modded)
      }.toOption
    }
    Translations(ArrayBuffer.from(languages).distinctBy(_.objName))
  }
}
