package com.skyline.warlangmod


final case class Translations(languages: Vector[TranslationMapping]) extends Renderable:
  /** Renders the translation by rendering all the languages and combining them
    * via a `newline`
    */
  override def render(): String =
    languages.map(_.render()).mkString("\n")

  override def toString: String =
    s"Translations(languages=[${languages.mkString(",")}])"
