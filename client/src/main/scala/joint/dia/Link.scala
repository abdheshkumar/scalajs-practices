package joint.dia

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}


@ScalaJSDefined
trait LinkOptions extends js.Object {
  var attrs: js.UndefOr[js.Dictionary[Attrs]] = js.undefined
}

@js.native
@JSGlobal("joint.dia.Link")
class Link(props: LinkOptions) extends js.Object {
  val id: String = js.native
  val port: String = js.native
  val attributes: LinkOptions = js.native
}