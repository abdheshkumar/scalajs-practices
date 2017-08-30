package tutorial.webapp

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}


@js.native
trait JRSClient extends js.Object {
  /**
    * Perform authentication with provided auth object
    *
    * @param authType {object} - auth properties
    */
  def login(authType: AuthType): Unit = js.native

  /**
    * Destroy current auth session
    */
  def logout(): Unit = js.native

  /**
    * Create and run report component with provided properties
    *
    * @param props {object} - report properties
    * @return {Report} report - instance of Report
    */
  def report(props: ReportProps): Report = js.native

  /**
    * CSS selector for container to render report to.
    *
    * @param container : String
    * @return JRSClient
    */
  def apply(container: String): JRSClient = js.native
}

/**
  *
  * @param server : String
  * @param auth   : AuthType
  */
@ScalaJSDefined
class JRSProps private(server: String, auth: AuthType) extends js.Object

object JRSProps {
  def apply(server0: String,
            authType0: AuthType): JRSProps =
    new JRSProps(server0, authType0)
}


@ScalaJSDefined
trait ReportProps extends js.Object {
  val server: js.UndefOr[String]
  val resource: js.UndefOr[String]
  val container: js.UndefOr[String] = js.undefined

  var success: js.UndefOr[js.Function0[Unit]] = js.undefined
}

object ReportProps {
  @inline
  def apply(server: js.UndefOr[String],
            resource: js.UndefOr[String],
            container: js.UndefOr[String] = js.undefined,
            success: js.UndefOr[js.Function0[Unit]] = js.undefined): ReportProps = {
    val result = js.Dynamic.literal()
    server.foreach(result.server = _)
    resource.foreach(result.resource = _)
    container.foreach(result.container = _)
    success.foreach(result.success = _)
    result.asInstanceOf[ReportProps]
    //new ReportProps(server, resource, container)
  }
}

@js.native
trait Report extends js.Object {
  def params(newParams: js.Object): Report = js.native

  def params(): js.Object = js.native

  //def run() = js.native
}

@js.native
@JSGlobal("visualize")
object visualize extends js.Object {
  def apply(props: JRSProps,
            callback: js.UndefOr[js.Function1[JRSClient, Unit]] = js.undefined,
            errorBack: js.UndefOr[js.Function1[JRSClient, Unit]] = js.undefined,
            always: js.UndefOr[js.Function1[JRSClient, Unit]] = js.undefined): Unit = js.native
}