package joint.dia

import org.scalajs.jquery._

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}

@ScalaJSDefined
trait PaperOptions extends js.Object {
  type VCFunction = js.Function6[CellView, Element, CellView, Element, String, LinkView, js.Any]
  var el: js.UndefOr[JQuery] = js.undefined
  var width: js.UndefOr[Int] = js.undefined
  var height: js.UndefOr[Int] = js.undefined
  var gridSize: js.UndefOr[Int] = js.undefined
  var model: js.UndefOr[Graph] = js.undefined
  var markAvailable: js.UndefOr[Boolean] = js.undefined
  var defaultLink: js.UndefOr[Link] = js.undefined
  var linkPinning: js.UndefOr[Boolean] = js.undefined
  var validateConnection: js.UndefOr[VCFunction] = js.undefined
}

@ScalaJSDefined
trait A extends js.Object{
  val selector: js.UndefOr[String]
  //val el: js.UndefOr[JQuery] = js.undefined
}

@js.native
@JSGlobal("joint.dia.Paper")
class Paper(props: PaperOptions) extends js.Object
