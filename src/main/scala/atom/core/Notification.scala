package laughedelic.atom

import scala.scalajs.js

@js.native
trait Notification extends js.Object {
  // Methods
  def getType(): String = js.native
  def getMessage(): String = js.native
  def dismiss(): Unit = js.native
}
