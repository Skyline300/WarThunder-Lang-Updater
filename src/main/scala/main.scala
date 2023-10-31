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

  // makes sure the unit file is updated, and removes the """
  unitN.foreach(x =>
    val u = unitN(unitN.indexOf(x)).replace("\"", "")
    val uPrev = unit(unitN.indexOf(x)).replace("\"", "")
    if (!(u.substring(0,u.indexOf(";")) == uPrev.substring(0,uPrev.indexOf(";")))) unit.insert(unitN.indexOf(x),u);
  )
  var rep = ""
  unit.foreach(line =>
    val index = unit.indexOf(line)
    val cur = unit(index).replace("\"", "");
    val sec = cur.indexOf(";",cur.indexOf(";")+1);
    val from = cur.indexOf(";") + 1;
    val to = cur.indexOf(";", cur.indexOf(";") + 1);
    cur match
      case x if x.contains("_shop;") => {
        if (sec != -1) {
          rep = cur.substring(cur.indexOf(";") + 1, cur.indexOf(";", cur.indexOf(";") + 1));
          cur = cur.substring(0, cur.indexOf(";", cur.indexOf(";") + 1))
        }
        else rep = cur.substring(cur.indexOf(";") + 1)
        unit.set(i, cur)
        println("yer mom")
      }
      case x if x.contains("_0;") => println("yer dad")
      case x if x.contains("_1;") || x.contains("_2;") => println("sex")
      case _ => println("cock")
  )
}