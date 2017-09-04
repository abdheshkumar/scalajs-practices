package tutorial.webapp.router

import diode._
import diode.react.ReactConnector


object AppCircuit extends Circuit[RootModel] with ReactConnector[RootModel] {
  def initialModel = RootModel(Counter(0))
  override val actionHandler: HandlerFunction = composeHandlers(new AppHandler(zoomTo(_.counter)))
}

class AppHandler[M](modelRW: ModelRW[M, Counter]) extends ActionHandler(modelRW) {
  override def handle = {
    case Increase(a) => updated(value.copy(i = value.i + a))
    case Decrease(a) => updated(value.copy(i = value.i - a))
    case Reset => updated(value.copy(i = 0))
  }
}