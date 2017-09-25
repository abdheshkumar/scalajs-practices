package joint.dia

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait PortOptions extends js.Object {
  var groups: js.UndefOr[GroupOptions] = js.undefined
}

@ScalaJSDefined
trait GroupOptions extends js.Object {
  var in: js.UndefOr[Options] = js.undefined
  var out: js.UndefOr[Options] = js.undefined
}
