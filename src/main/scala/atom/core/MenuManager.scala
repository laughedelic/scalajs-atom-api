package laughedelic.atom

import scala.scalajs.js

class MenuItem (
  val label: String,
  val submenu: js.UndefOr[js.Array[MenuItem]] = js.undefined,
  val command: js.UndefOr[String] = js.undefined
) extends js.Object

@js.native
trait MenuManager extends js.Object {
  /* Adds the given items to the application menu. */
  def add(items: js.Array[MenuItem]): Disposable = js.native

  /* Refreshes the currently visible menu. */
  def update(): Unit = js.native
}
