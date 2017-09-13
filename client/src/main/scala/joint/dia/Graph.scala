package joint.dia

import joint.shapes.devs.Model

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.dia.Graph")
class Graph() extends js.Object {
  def addCell(model: Model): Unit = js.native

  def getConnectedLinks(model: Model, boundProps: BoundProps): js.Array[Model] = js.native
}
