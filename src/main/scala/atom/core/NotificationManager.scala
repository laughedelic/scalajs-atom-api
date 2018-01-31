package laughedelic.atom

import scala.scalajs.js

class NotificationButton(
  val className: js.UndefOr[String] = js.undefined,
  val onDidClick: js.UndefOr[js.Function1[Unit, Unit]] = js.undefined,
  val text: js.UndefOr[String] = js.undefined
) extends js.Object

class NotificationOptions(
  val detail: js.UndefOr[String] = js.undefined,
  val dismissable: js.UndefOr[Boolean] = js.undefined,
  val description: js.UndefOr[String] = js.undefined,
  val icon: js.UndefOr[String] = js.undefined,
  val buttons: js.UndefOr[js.Array[NotificationButton]] = js.undefined
) extends js.Object

@js.native
trait NotificationManager extends js.Object {

  def onDidAddNotification(callback: js.Function1[Notification, Unit]): Disposable = js.native

  // Adding Notifications
  def addInfo(msg: String, options: NotificationOptions = js.native): Notification = js.native
  def addError(msg: String, options: NotificationOptions = js.native): Notification = js.native
  def addFatalError(msg: String, options: NotificationOptions = js.native): Notification = js.native
  def addSuccess(msg: String, options: NotificationOptions = js.native): Notification = js.native
  def addWarning(msg: String, options: NotificationOptions = js.native): Notification = js.native

  // Getting Notifications
  def getNotifications(): js.Array[Notification] = js.native
}
