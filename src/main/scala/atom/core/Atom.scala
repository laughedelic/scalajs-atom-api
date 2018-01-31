package laughedelic.atom

import scala.scalajs.js, js.annotation._

@js.native @JSGlobal("atom")
object Atom extends js.Object {

  val commands: CommandRegistry = js.native
  val config: Config = js.native
  val menu: MenuManager = js.native
  val notifications: NotificationManager = js.native
  val workspace: Workspace = js.native

  def inDevMode(): Boolean = js.native
  def inSafeMode(): Boolean = js.native
  def inSpecMode(): Boolean = js.native
  def getVersion(): String = js.native

}
