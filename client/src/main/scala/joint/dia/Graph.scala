package joint.dia

import joint.shapes.devs.Model

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.dia.Graph")
class Graph extends js.Object {
  def addCell[OP <: Options, C <: Cell[OP, C]](cell: C): Graph = js.native

  def getConnectedLinks(model: Model, boundProps: js.UndefOr[BoundProps] = js.undefined): js.Array[Model] = js.native

  def toJSON(): js.Object = js.native

  def fromJSON(json: Dynamic): Graph = js.native

  def on(event: String, fun: js.Function1[CellView, Unit]): js.Any = js.native
}
