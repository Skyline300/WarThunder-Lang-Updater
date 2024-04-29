package com.skyline.warlangmod

import scala.io.BufferedSource
import scala.util.Try


case class Language(objName: String, translation: String) extends Renderable {
  override def render(): String =
    s"$objName;$translation"

  override def toString: String =
    s"Language(objName=$objName, translation=$translation)"
}
case class Translations(languages: Vector[Language]) extends Renderable {
  override def render(): String =
    languages.map(_.render()).mkString("\n")

  override def toString: String =
    s"Translations(languages=[${languages.mkString(",")}])"
}

object Language {
  def parse(languageFile: BufferedSource): Translations = {
    val iter = languageFile.getLines()
    val languages = iter.flatMap { str =>
      val sanitizedString = str.replace("\"", "")
      val splitString = sanitizedString.split(";")
      Try {
        val objName = splitString(0)
        if(objName.isEmpty) {
          throw new RuntimeException("This is gay")
        }
        val (_, translation) = splitString.splitAt(1)
        Language(objName, translation(0))
      }.toOption
    }
    Translations(languages.toVector)
  }
}
