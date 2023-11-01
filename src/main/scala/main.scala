import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters
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
    val u = unitN(unitN.indexOf(x)).replace("\"", "");
    val uPrev = unit(unitN.indexOf(x)).replace("\"", "");
    if (!(u.substring(0,u.indexOf(";")) == uPrev.substring(0,uPrev.indexOf(";")))) unit.insert(unitN.indexOf(x),u);
  )
  var rep = "";
  //println(unit.length)

  // removes unnecessary languages from the file
  unit.foreach(line =>
    val index = unit.indexOf(line);
    var cur = unit(index).replace("\"", "");
    val start = cur.indexOf(";") + 1;
    val sec = cur.indexOf(";",start);
    //val to = cur.indexOf(";", start);

    cur match
      case x if x.contains("_shop;") => {
        if sec != -1 then
          rep = cur.substring(start, sec);
          cur = cur.substring(0, sec);
        else
          rep = cur.substring(start);
        unit(index) = cur
      }
      case x if x.contains("_0;") => {
        if rep == "" && sec != -1 then
          rep = cur.substring(start,sec)
        else
          rep = cur.substring(start)
       // println("yer dad")
        if sec != -1 then cur = cur.substring(0,sec)
        unit(index) = cur
      }
      case x if x.contains("_1;") || x.contains("_2;") => {
        cur = cur.substring(0,start) + rep;
        if cur.contains("_2;") then rep = "";
        unit(index) = cur;
      }
      case _ => {
        println("I hate kids")
      }

      println(unit(index))
  )

}