package com.skyline.warlangmod

trait TranslationOverwriteService:
  def overwrite(original: Translations, modded: Translations): Translations

object TranslationOverwriteService:
  lazy val instance: TranslationOverwriteService =
    new TranslationOverwriteService:

      private type ObjectName = String

      extension (translation: Translations)
        def toMap: Map[ObjectName, String] =
          translation.languages
            .map: language =>
              language.objName -> language.translation
            .toMap

      override def overwrite(
          original: Translations,
          modded: Translations
      ): Translations =
        val moddedMap = modded.toMap
        val updated = original.languages.map: language =>
          // returns the value associated to the key: objName
          // if there is no key that matches in the moddedMap, return original translation name
          val overwrittenLanguageTranslation =
            moddedMap.getOrElse(language.objName, language.translation)
          // updates the original with the modded translations for each name
          language.copy(translation = overwrittenLanguageTranslation)
        Translations(updated)
