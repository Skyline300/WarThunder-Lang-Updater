package com.skyline.warlangmod

trait Output {
    def write(filename: String): Unit
}

object Output {
  def apply(): Output = new Output {
    import java.io.FileWriter
    override def write(filename: String): Unit = {
      val updatedFile = new FileWriter(filename)
      updatedFile.write(uptodateModdedTranslationFile.render())
      updatedFile.close()
    }
  }
}
