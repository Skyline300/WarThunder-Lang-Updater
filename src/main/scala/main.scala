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

  // makes sure the unit file is updated, and removes the "\"
  unitN.foreach(x =>
    val u = unitN(unitN.indexOf(x)).replace("\"", "")
    val uPrev = unit(unitN.indexOf(x)).replace("\"", "")
    if (!(u.substring(0,u.indexOf(";")) == uPrev.substring(0,uPrev.indexOf(";")))) unit.insert(unitN.indexOf(x),u);
  )

  for (elem <- unit) {}
  println(unit(5733))
}