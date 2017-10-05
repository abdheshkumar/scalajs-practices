package joint

import joint.dia.Element

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait V extends js.Object {
  def attr(name: String, value: js.UndefOr[String] = js.undefined): String = js.native

  def apply(magnet: js.UndefOr[Element] = js.undefined): V = js.native
}

@js.native
@JSGlobal("V")
object V extends V