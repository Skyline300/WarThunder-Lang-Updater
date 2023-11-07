import java.io.FileWriter
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
@main
def main(): Unit = {

  // adds the file data to the "unit" array
  val File = Source.fromFile("src/units.csv")
  val unit = ArrayBuffer[String]()
  File.getLines().foreach(line => unit += line)

  // adds the new file data into the "unitN" array
  val Nfile = Source.fromFile("src/unitsN.csv")
  val unitN = ArrayBuffer[String]()
  Nfile.getLines().foreach(line => unitN += line)

  // makes sure the unit file is updated, and removes the double quotation marks
  unitN.foreach(x =>
    val u = unitN(unitN.indexOf(x)).replace("\"", "")
    val uPrev = unit(unitN.indexOf(x)).replace("\"", "")
    if (!(u.substring(0,u.indexOf(";")) == uPrev.substring(0,uPrev.indexOf(";")))) unit.insert(unitN.indexOf(x),u);
  )
  // removes unnecessary languages from the file
  unit.foreach(line =>
    val index = unit.indexOf(line)
    var cur = unit(index).replace("\"", "")
    val start = cur.indexOf(";") + 1
    val sec = cur.indexOf(";",start);

    cur match
      case x if x.contains("_shop;") => {
        if sec != -1 then
          cur = cur.substring(0, sec);
        unit(index) = cur
      }
      case x if x.contains("_0;") => {
        if sec != -1 then cur = cur.substring(0,sec)
        unit(index) = cur
      }
      case x if x.contains("_1;") || x.contains("_2;") => {
        if sec != -1 then cur = cur.substring(0, sec);
        unit(index) = cur
      }
      case _ => {
        if (sec != -1) cur = cur.substring(0,sec)
        unit(index) = cur
      };

  )
  val w = new FileWriter("src/unitsMod.csv")
  for (i <- unit.indices) {
    w.write(unit(i) + "\n")
  }
  w.close()

}