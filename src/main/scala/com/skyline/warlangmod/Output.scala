package com.skyline.warlangmod

import java.io.FileWriter

/**
 *  For writing the translations to a file
 */
trait Output {
    def write(file: Translations, outputFileName: String): Unit
}

object Output {
  // I keep forgetting how this shit works but it does
  lazy val instance: Output = (file: Translations, outputFileName: String) => {
    val updatedFile = new FileWriter(outputFileName)
    updatedFile.write(file.render())
    updatedFile.close()
  }
}
