package joint.dia

import joint.V
import org.scalajs.jquery.jQuery

import scala.scalajs.js

object DiagramUtility {
  def createPaperLayout(selector: String, graph: Graph): Paper = {
    new Paper(new PaperOptions {
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
      validateConnection = js.defined(validateConnections(graph))
      validateMagnet = js.defined(validateMagnets(graph))
      //elementView = js.Dynamic.global.chs.views.BaseNodeView//.asInstanceOf[ElementView]
      //cellViewNamespace = "chs.views"
    })
  }

  private def validateMagnets(graph: Graph)(cellView: CellView, magnet: Element) = {
    val portId = V(magnet).attr("port")
    val cell = cellView.model
    val links = graph.getConnectedLinks(cell).filter { link =>
      val source = link.get("source")
      val target = link.get("target")
      source.id == cell.id && source.port == portId ||
        target.id == cell.id && target.port == portId
    }.length
    magnet.getAttribute("magnet") != "passive" && links < 1
  }

  private def validateConnections(graph: Graph)
                                 (cellViewS: CellView,
                                  magnetS: Element,
                                  cellViewT: CellView,
                                  magnetT: Element,
                                  end: String,
                                  linkView: LinkView): Boolean = {
    val port = magnetS.getAttribute("port")
    val links = graph.getConnectedLinks(cellViewS.model, new BoundProps {
      outbound = true
    }).filter(_.get("source").port == port)

    if (!js.isUndefined(magnetS) && magnetS.getAttribute("port-group") == "in") false
    else if (links.length > 1) false
    else !js.isUndefined(magnetT) && magnetT.getAttribute("port-group") == "in"
  }

}
