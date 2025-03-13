package com.skyline.warlangmod

import scala.io.BufferedSource
import scala.util.Try

/** A mapping between a warthunder object and its corresponding translation in a
  * language
  *
  * @param objName
  *   the object which is translated
  * @param translation
  *   the representation of the object in some language
  */
final case class TranslationMapping(objName: String, translation: String)
    extends Renderable:
  override def render(): String =
    s"$objName;$translation"

  override def toString: String =
    s"Language(objName=$objName, translation=$translation)"

object TranslationMapping:
// parses the csv files
  def parse(languageFile: BufferedSource): Translations =
    val iter = languageFile.getLines()
    val languages = iter.flatMap: str =>
      // removes the quotation marks of every line
      val sanitizedString = str.replace("\"", "")
      // splits the object name and it's localized translation names
      val splitString = sanitizedString.split(";")
      Try:
        // gets the object name
        val objName = splitString(0)
        if objName.isEmpty then throw new RuntimeException("This is gay")
        // gets the translation names
        val (_, translation) = splitString.splitAt(1)
        // translation(0) filters it only to english
        TranslationMapping(objName, translation(0))
      .toOption
    Translations(languages.toVector)
