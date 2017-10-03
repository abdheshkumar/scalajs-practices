package joint.shapes.chs.nodes

import joint.dia.{PrototypeProperties, StaticProperties}
import joint.shapes.devs.ModelOptions

import scala.scalajs.js

@js.native
trait Extender extends js.Object {
  def define(`type`: String, props: js.UndefOr[ModelOptions] = js.undefined,
             prototypeProperties: js.UndefOr[PrototypeProperties] = js.undefined,
             staticProperties: js.UndefOr[StaticProperties] = js.undefined): js.Dynamic = js.native
}

trait ExtenderOps {
  val extender: Extender
}