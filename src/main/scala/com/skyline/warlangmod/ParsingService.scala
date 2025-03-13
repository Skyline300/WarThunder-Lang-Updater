package com.skyline.warlangmod

import scala.io.Source

/** For parsing language/translation files of WarThunder
  */

trait ParsingService:
  def translations(filename: String): Translations

object ParsingService:

  lazy val instance: ParsingService = filename =>
    val langFile = Source.fromFile(filename)
    val translations = TranslationMapping.parse(langFile)
    langFile.close()
    translations
