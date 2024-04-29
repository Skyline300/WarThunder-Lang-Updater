# WarThunder-Lang-Updater
An updater for lang files for War Thunder, written in Scala

## Usage

You will need your existing Units.csv file and the updated version file, renamed to UnitsN.csv, something you can get from Gszabi99's repository [here](https://github.com/gszabi99/War-Thunder-Datamine).
<br>
Help file
```shell
$ java -jar WarThunder-Lang-Updater-0.1.0-SNAPSHOT.jar --help

Usage: WarThunder Translation Updater [--input <string>] [--original <string>] [--output <string>]

A tool to update the translation files for WarThunder

Options and flags:
    --help
        Display this help text.
    --input <string>, -i <string>
        The input file to be updated, defaults to units.csv when not provided
    --original <string>, -r <string>
        The original file to be updated, defaults to unitsN.csv when not provided
    --output <string>, -o <string>
        The output file to be written to, defaults unitsMod.csv when not provided
```

Run the JAR file with the following command:
```shell
$ java -jar WarThunder-Lang-Updater-0.1.0-SNAPSHOT.jar --input <file path 1> --original <file path 2> --output <file path 3>
```

Where `input` file is your old translations file and `original` is the translation file from the most recent WarThunder Client, it can be your absolute or relative path

The `output` file is the file that will be written to, it can be absolute or relative path


## Can you use this for any other file other than Units.csv?
Some of the localization files currently do not work with this program. So far I've confirmed it to work with units and units_weaponry.
