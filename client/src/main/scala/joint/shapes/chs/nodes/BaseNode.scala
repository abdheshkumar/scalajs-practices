package joint.shapes.chs.nodes

import joint.dia._
import joint.shapes.devs.{Model, ModelOptions}

import scala.scalajs.js

@js.native
trait BaseNode extends Model

object BaseNode extends ExtenderOps {

  private val options = new ModelOptions {
    position = new Position {
      x = 50
      y = 150
    }
    size = new Size {
      width = 90
      height = 90
    }
    ports = new PortOptions {
      groups = new GroupOptions {
        in = new Options {
          attrs = new Attrs {
            portBody = new AttrStyle {
              fill = "#16A085"
              magnet = "passive"
            }
          }
        }
        out = new Options {
          attrs = new Attrs {
            portBody = new AttrStyle {
              fill = "#E74C3C"
            }
          }
        }
      }
    }
    attrs = new Attrs {

      rect = new AttrStyle {
        fill = "#2ECC71"
      }
    }
  }
  private val nodeClass = Model.extender.define("chs.Node", options)

  override val extender = nodeClass.asInstanceOf[Extender]

}