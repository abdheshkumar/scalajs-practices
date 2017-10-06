package joint

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait util extends js.Object {
  def template(html: String): js.Function0[String] = js.native
}

@js.native
trait joint extends js.Object {
  val util: util = js.native
}

@js.native
@JSGlobal("joint")
object jointJs extends joint
