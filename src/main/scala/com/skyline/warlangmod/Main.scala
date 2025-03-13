package com.skyline.warlangmod

import com.monovore.decline.*

import cats.implicits.*

object Main
    extends CommandApp(
      name = "WarThunder Translation Updater",
      header = "A tool to update the translation files for WarThunder ",
      main =
        val defaultInFile = "units.csv"
        val defaultOriginalFile = "unitsN.csv"
        val defaultOutFile = "unitsMod.csv"
        val originalFile = Opts
          .option[String](
            long = "original",
            short = "r",
            help =
              s"The original file to be updated, defaults to $defaultOriginalFile when not provided"
          )
          .withDefault(defaultOriginalFile)
        val inFile = Opts
          .option[String](
            long = "input",
            short = "i",
            help =
              s"The input file to be updated, defaults to $defaultInFile when not provided"
          )
          .withDefault(defaultInFile)
        val outFile = Opts
          .option[String](
            long = "output",
            short = "o",
            help =
              s"The output file to be written to, defaults $defaultOutFile when not provided"
          )
          .withDefault(defaultOutFile)

        (inFile, originalFile, outFile).mapN:
          (inputFileName, originalFileName, outputFileName) =>
            App.run(inputFileName, originalFileName, outputFileName)
    )
