import java.io.FileWriter
import scala.collection.mutable.ArrayBuffer
import scala.io.{BufferedSource, Source}
@main
def main(): Unit = {

//  if(args.length < 2)
//    System.exit(1) // No input file


  // adds the file data to the "myLangFileBuffer" array
  val myLangFile: BufferedSource = Source.fromFile("src/units.csv")
  val myLangFileBuffer = myLangFile.getLines().toArray.toBuffer
  myLangFile.close()

  // adds the new file data into the "updatedLangFileBuffer" array
  val updatedLangFile: BufferedSource = Source.fromFile("src/unitsN.csv")
  val updatedLangFileBuffer = updatedLangFile.getLines().toArray.toBuffer
  updatedLangFile.close()

  // makes sure the myLangFileBuffer file is updated, and removes the double quotation marks
  updatedLangFileBuffer.zipWithIndex.foreach { case (line, index) =>
    val unescapedNewLine = line.replace("\"", "")
    val unescapedOldLine = myLangFileBuffer(index).replace("\"", "")
    val firstColumnInUnitN = unescapedNewLine.split(";")(0)
    val firstColumnInUnit = unescapedOldLine.split(";")(0)
    if (firstColumnInUnitN != firstColumnInUnit) myLangFileBuffer.insert(index, unescapedNewLine);
  }

  var i = 0
  while (i < myLangFileBuffer.size) {
    var cur = myLangFileBuffer(i).replace("\"", "")
    val start = cur.indexOf(";") + 1
    val sec = cur.indexOf(";",start);

    cur match
      case x if x.contains("_shop;") => {
        if sec != -1 then cur = cur.substring(0, sec)
        myLangFileBuffer(i) = cur
        i += 1
      }
      case x if x.contains("_0;") => {
        if sec != -1 then cur = cur.substring(0,sec)
        myLangFileBuffer(i) = cur
        i += 1
      }
      case x if x.contains("_1;") || x.contains("_2;") => {
        if sec != -1 then cur = cur.substring(0, sec)
        myLangFileBuffer(i) = cur
        i += 1
      }
      case x if x.startsWith(";") => {
        myLangFileBuffer.remove(i)
      }
      case _ => {
        if (sec != -1) cur = cur.substring(0,sec)
        myLangFileBuffer(i) = cur
        i += 1
      };
  }

  // removes unnecessary languages from the file
//  myLangFileBuffer.foreach(line =>
//    val index = myLangFileBuffer.indexOf(line)
//    var cur = myLangFileBuffer(index).replace("\"", "")
//    val start = cur.indexOf(";") + 1
//    val sec = cur.indexOf(";",start);
//
//    cur match
//      case x if x.contains("_shop;") => {
//        if sec != -1 then cur = cur.substring(0, sec)
//        myLangFileBuffer(index) = cur
//      }
//      case x if x.contains("_0;") => {
//        if sec != -1 then cur = cur.substring(0,sec)
//        myLangFileBuffer(index) = cur
//      }
//      case x if x.contains("_1;") || x.contains("_2;") => {
//        if sec != -1 then cur = cur.substring(0, sec)
//        myLangFileBuffer(index) = cur
//      }
//      case x if x.startsWith(";") => {
//        myLangFileBuffer.remove(index)
//      }
//      case _ => {
//        if (sec != -1) cur = cur.substring(0,sec)
//        myLangFileBuffer(index) = cur
//      };
//
//  )
  val w = new FileWriter("src/unitsMod.csv")
  for (i <- myLangFileBuffer.indices) {
    w.write(myLangFileBuffer(i) + "\n")
  }
  w.close()

}