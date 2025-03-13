package com.skyline.warlangmod

/** For writing the translations to a file
  */
trait Output:
  def write(file: Translations, outputFileName: String): Unit

object Output:
  lazy val instance: Output = new Output:
    import java.io.FileWriter
    override def write(file: Translations, outputFileName: String): Unit =
      val updatedFile = new FileWriter(outputFileName)
      updatedFile.write(file.render())
      updatedFile.close()
