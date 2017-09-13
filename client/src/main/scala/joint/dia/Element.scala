package joint.dia

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}

@js.native
@JSGlobal("joint.dia.Element")
class Element() extends js.Object {
  def getAttribute(name: String): String = js.native
}

@ScalaJSDefined
trait BoundProps extends js.Object {
  var outbound: js.UndefOr[Boolean] = js.undefined
  var inbound: js.UndefOr[Boolean] = js.undefined
}