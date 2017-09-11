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
class AttributesStyle(attrs: js.Dynamic) extends js.Object

object AttributesStyle {
  def apply(attrs: js.Dynamic): AttributesStyle = new AttributesStyle(attrs)
}

@ScalaJSDefined
class Group(in: AttributesStyle, out: AttributesStyle) extends js.Object

object Group {
  def apply(in0: AttributesStyle, out0: AttributesStyle): Group = new Group(in0, out0)
}

@ScalaJSDefined
class RectProps(fill: String) extends js.Object

