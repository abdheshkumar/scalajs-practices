package joint.dia

import joint.shapes.devs.{Model, ModelOptions}
import org.scalajs.jquery.jQuery

import scala.scalajs.js

object DiagramUtility {
  def createPaperLayout(selector: String,graph: Graph): Paper = {
    val paper = new Paper(new PaperOptions {
      el = jQuery(selector)
      width = 800
      height = 600
      gridSize = 1
      model = graph
      defaultLink = new Link(new LinkOptions {
        attrs = js.Dictionary(".marker-target" -> new Attrs {
          d = "M 10 0 L 0 5 L 10 10 z"
        })
      })
      markAvailable = true
      linkPinning = false
      validateConnection = js.defined((cellViewS, magnetS, cellViewT, magnetT, end, linkView) => {
        val port = magnetS.getAttribute("port")
        val links = graph.getConnectedLinks(cellViewS.model, new BoundProps {
          outbound = true
        }).filter(_.get("source").port == port)

        if (!js.isUndefined(magnetS) && magnetS.getAttribute("port-group") == "in") false
        else if (links.length > 1) false
        else !js.isUndefined(magnetT) && magnetT.getAttribute("port-group") == "in"
      })
    })
    paper
  }

  def callNode(nodeType:String,inPorts0: js.Array[String],
               outPorts0: js.Array[String],
               nodeName: String): Model = {

    val attrs0 = new Attrs {
      label = new AttrStyle {
        text = nodeName
        `ref-x` = .5
        `ref-y` = .2
      }
      rect = new AttrStyle {
        fill = "#2ECC71"
      }
    }

    val in0 = new Attrs {
      portBody = new AttrStyle {
        fill = "#16A085"
        magnet = "passive"
      }
    }

    val out0 = new Attrs {
      portBody = new AttrStyle {
        fill = "#E74C3C"
      }
    }

    new Model(new ModelOptions(nodeType) {
      position = new Position {
        x = 50
        y = 150
      }
      size = new Size {
        width = 90
        height = 90
      }
      outPorts = outPorts0
      inPorts = inPorts0
      ports = new PortOptions {
        groups = new GroupOptions {
          in = new Options {
            attrs = in0
          }
          out = new Options {
            attrs = out0
          }
        }
      }
      attrs = attrs0
    })
  }
}
