package joint.dia

import org.scalajs.dom
import org.scalajs.jquery._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}

@ScalaJSDefined
trait PaperOptions extends js.Object {
  type VCFunction = js.Function6[CellView, Element, CellView, Element, String, LinkView, Boolean]
  var el: js.UndefOr[JQuery] = js.undefined
  var width: js.UndefOr[Int] = js.undefined
  var height: js.UndefOr[Int] = js.undefined
  var gridSize: js.UndefOr[Int] = js.undefined
  var model: js.UndefOr[Graph] = js.undefined
  var markAvailable: js.UndefOr[Boolean] = js.undefined
  var defaultLink: js.UndefOr[Link] = js.undefined
  var linkPinning: js.UndefOr[Boolean] = js.undefined
  var validateConnection: js.UndefOr[VCFunction] = js.undefined
  var cellViewNamespace: js.UndefOr[String] = js.undefined
  var elementView: js.UndefOr[Dynamic] = js.undefined
  var validateMagnet: js.UndefOr[js.Function2[CellView, Element, Boolean]] = js.undefined
}


@js.native
@JSGlobal("joint.dia.Paper")
class Paper(props: PaperOptions) extends js.Object {
  def on(evenName: String, fun: js.Function4[CellView, dom.Event, Int, Int, _]): js.Any = js.native
}
