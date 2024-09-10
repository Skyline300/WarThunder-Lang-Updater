package com.skyline.warlangmod

import scala.io.BufferedSource
import scala.util.Try

// the base case class that stores the data
case class Language(objName: String, translation: String) extends Renderable {
  override def render(): String = {
    // combines both back together in a single line
    s"$objName;$translation"
  }

  override def toString: String =
    s"Language(objName=$objName, translation=$translation)"
}

// the case class we will use to store the Languages case class
case class Translations(languages: Vector[Language]) extends Renderable {
  override def render(): String = {
    // calls the render function from Languages, and makes a new lines after every
    // Language class
    languages.map(_.render()).mkString("\n")
  }

  override def toString: String =
    s"Translations(languages=[${languages.mkString(",")}])"
}

object Language {
// parses the csv files
  def parse(languageFile: BufferedSource): Translations = {
    val iter = languageFile.getLines()
    val languages = iter.flatMap { str =>
      // removes the quotation marks of every line
      val sanitizedString = str.replace("\"", "")
      // splits the object name and it's localized translation names
      val splitString = sanitizedString.split(";")
      Try {
        // gets the object name
        val objName = splitString(0)
        if(objName.isEmpty) {
          throw new RuntimeException("This is gay")
        }
        // gets the translation names
        val (_, translation) = splitString.splitAt(1)
        // translation(0) filters it only to english
        Language(objName, translation(0))
      }.toOption
    }
    Translations(languages.toVector)
  }
}
