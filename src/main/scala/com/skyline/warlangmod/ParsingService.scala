package com.skyline.warlangmod

trait ParsingService {
  def translations(filename: String): Translations
}

object ParsingService {

  def apply(): ParsingService = new ParsingService {
    import scala.io.Source
    override def translations(filename: String): Translations = {
      val langFile     = Source.fromFile(filename)
      val translations = Language.parse(langFile)
      langFile.close()
      translations
    }

  }

}
