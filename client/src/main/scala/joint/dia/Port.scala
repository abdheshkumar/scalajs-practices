package joint.dia

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
trait PortProps extends js.Object {
  val groups: js.UndefOr[Group] = js.undefined
}

object PortProps {
  @inline
  def apply(groups0: js.UndefOr[Group]): PortProps = new PortProps {
    override val groups: js.UndefOr[Group] = groups0
  }
}

@ScalaJSDefined
trait AttributesStyle extends js.Object {
  val attrs: js.Dynamic
}

object AttributesStyle {
  def apply(attrs0: js.Dynamic): AttributesStyle = new AttributesStyle {
    override val attrs: js.Dynamic = attrs0
  }
}

@ScalaJSDefined
trait Group extends js.Object {
  var in: js.UndefOr[AttributesStyle] = js.undefined
  var out: js.UndefOr[AttributesStyle] = js.undefined
}

object Group {
  def apply(in0: js.UndefOr[AttributesStyle] = js.undefined,
            out0: js.UndefOr[AttributesStyle] = js.undefined): Group = new Group {
    in = in0
    out = out0
  }
}

@ScalaJSDefined
class RectProps(fill: String) extends js.Object

