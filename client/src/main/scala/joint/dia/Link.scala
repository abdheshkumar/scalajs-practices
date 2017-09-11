package joint.dia

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}


@ScalaJSDefined
trait LinkProps extends js.Object {
  val attrs: js.UndefOr[Dynamic]
}

object LinkProps {
  @inline
  def apply(attrs0: js.UndefOr[Dynamic]): LinkProps = new LinkProps {
    override val attrs: UndefOr[Dynamic] = attrs0
  }
}


@js.native
@JSGlobal("joint.dia.Link")
class Link(props: LinkProps) extends js.Object