package com.skyline.warlangmod.cli

import cats.data.Validated
import com.monovore.decline.Argument

object Params {

  sealed trait TranslationFileType

  object TranslationFileType {
    case object Units extends TranslationFileType

    case object Weaponry extends TranslationFileType
  }

  implicit val translationFileTypeArgument: Argument[TranslationFileType] =
    Argument.from("Either 'units' or 'weaponry'"){
      case "units" => Validated.valid(TranslationFileType.Units)
      case "weaponry" => Validated.valid(TranslationFileType.Weaponry)
      case _ => Validated.invalidNel("Invalid translation file type")
    }

}
