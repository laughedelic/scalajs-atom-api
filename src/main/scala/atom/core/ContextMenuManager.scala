package laughedelic.atom

import org.scalajs.dom.raw.Event
import scala.scalajs.js

sealed trait AnyContextMenuItem extends js.Object

object ContextMenuSeparator extends AnyContextMenuItem {
  final val `type`: String = "separator"
}

class ContextMenuItem (
  /* The menu item's label. */
  val label: js.UndefOr[String] = js.undefined,
  
  /*
   *  The command to invoke on the target of the right click that invoked the
   *  context menu.
   */
  val command: js.UndefOr[String] = js.undefined,
  
  /*
   *  Whether the menu item should be clickable. Disabled menu items typically
   *  appear grayed out. Defaults to true.
   */
  val enabled: js.UndefOr[Boolean] = js.undefined,
  
  /* An array of additional items. */
  val submenu: js.UndefOr[js.Array[AnyContextMenuItem]] = js.undefined,
  
  /* Whether the menu item should appear in the menu. Defaults to true. */
  val visible: js.UndefOr[Boolean] = js.undefined,
  
  /*
   *  A function that is called on the item each time a context menu is created
   *  via a right click.
   */
  val created: js.UndefOr[js.Function1[Event, Unit]] = js.undefined,
  
  /*
   *  A function that is called to determine whether to display this item on a
   *  given context menu deployment.
   */
  val shouldDisplay: js.UndefOr[js.Function1[Event, Boolean]] = js.undefined
) extends AnyContextMenuItem


/* Provides a registry for commands that you'd like to appear in the context menu. */
@js.native
trait ContextMenuManager extends js.Object {
  def add(itemsBySelector: js.Dictionary[js.Array[AnyContextMenuItem]]): Disposable = js.native
}
