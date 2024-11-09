package com.skyline.warlangmod

import com.monovore.decline._

import cats.implicits._

object Main extends CommandApp(
  name = "WarThunder Translation Updater",
  header = "A tool to update the translation files for WarThunder ",
  main = {
    val defaultUnitsLink = "https://raw.githubusercontent.com/gszabi99/War-Thunder-Datamine/1bc153b0d22ac06719f6ce1ba2c25b567285cc99/lang.vromfs.bin_u/lang/units.csv"
    val defaultWeaponryLink = "https://raw.githubusercontent.com/gszabi99/War-Thunder-Datamine/1bc153b0d22ac06719f6ce1ba2c25b567285cc99/lang.vromfs.bin_u/lang/units_weaponry.csv"
    val defaultInFile = "src/units.csv"
    val defaultOriginalFile = "src/unitsN.csv"
    val defaultOutFile = "src/output.csv"
    val originalFile = Opts.option[String](
      long = "original",
      short = "r",
      help = s"The original file to be updated, defaults to $defaultOriginalFile when not provided").withDefault(defaultOriginalFile)
    val inFile = Opts.option[String](
      long = "input",
      short = "i",
      help = s"The input file to be updated, defaults to $defaultInFile when not provided").withDefault(defaultInFile)
    val outFile = Opts.option[String](
      long = "output",
      short = "o",
      help = s"The output file to be written to, defaults $defaultOutFile when not provided").withDefault(defaultOutFile)

    (inFile, originalFile, outFile).mapN {
      (inputFileName, originalFileName, outputFileName) => App.runOffline(inputFileName, originalFileName, outputFileName)
    }
  }
)