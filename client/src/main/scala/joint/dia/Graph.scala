package joint.dia

import joint.shapes.devs.Model

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal


/*@js.native
@JSGlobal("joint.dia.Graph")
trait Graph extends js.Object {
  def addCell(model: Model): Unit = js.native
}*/

@js.native
@JSGlobal("joint.dia.Graph")
class Graph() extends js.Object  {
  def addCell(model: Model): Unit = js.native
}
