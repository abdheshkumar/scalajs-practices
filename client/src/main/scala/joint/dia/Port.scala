package joint.dia

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait PortOptions extends js.Object {
  var groups: js.UndefOr[Group] = js.undefined
}

@ScalaJSDefined
trait AttributesStyle extends js.Object {
  var attrs: js.UndefOr[js.Dictionary[Attrs]] = js.undefined
  var position: js.UndefOr[js.Dynamic] = js.undefined
}


@ScalaJSDefined
trait Group extends js.Object {
  var in: js.UndefOr[AttributesStyle] = js.undefined
  var out: js.UndefOr[AttributesStyle] = js.undefined
}
