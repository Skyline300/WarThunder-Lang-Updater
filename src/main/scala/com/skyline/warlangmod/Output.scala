package com.skyline.warlangmod

trait Output {
    def write(file: Translations): Unit
}

object Output {
  def apply(): Output = new Output {
    import java.io.FileWriter
    override def write(file: Translations): Unit = {
      val updatedFile = new FileWriter("UnitsMod.csv")
      updatedFile.write(file.render())
      updatedFile.close()
    }
  }
}
