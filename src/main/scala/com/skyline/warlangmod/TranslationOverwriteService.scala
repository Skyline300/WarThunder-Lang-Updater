package com.skyline.warlangmod

trait TranslationOverwriteService {
  def overwrite(original: Translations, modded: Translations): Translations
}

object TranslationOverwriteService {
  lazy val instance: TranslationOverwriteService = new TranslationOverwriteService {

    private type ObjectName = String

    override def overwrite(original: Translations, modded: Translations): Translations = {
      val moddedMap  = toMap(modded)
      val updated = original.languages.map { language =>
        // headOption because we only need the first translation which is english
        val overwrittenLanguageTranslation = moddedMap.getOrElse(language.objName, language.translation)
        language.copy(translation = overwrittenLanguageTranslation)
      }
      
      Translations(updated)
    }

    private def toMap(translations: Translations): Map[ObjectName, String] =
      translations.languages.map { language =>
        language.objName -> language.translation
      }.toMap
  }
}
