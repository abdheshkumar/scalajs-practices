package joint.shapes.basic

import joint.dia.{Cell, Options}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Generic")
abstract class Generic[OP <: Options, C <: Cell[OP, C]] extends Cell[OP, C] {
  self: Cell[OP, C] =>
}
