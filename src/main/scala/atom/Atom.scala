package laughedelic.atom

import org.scalajs.dom._
import scala.scalajs.js, js.annotation._, js.|


@js.native
trait Disposable extends js.Object {
  def dispose(): Unit = js.native
}

class CommandListener(
  val displayName: js.UndefOr[String] = js.undefined,
  val description: js.UndefOr[String] = js.undefined,
  val hiddenInCommandPalette: js.UndefOr[Boolean] = js.undefined
)(callback: js.Any => Unit) extends js.Object {

  def didDispatch(event: js.Any): Unit = callback(event)
}

@js.native
trait CommandRegistry extends js.Object {
  def add(
    target: String | Element,
    commandName: String,
    listener: CommandListener | (js.Any => Unit)
  ): Disposable = js.native

  def add(
    target: String | Element,
    commands: js.Dictionary[js.Any => Unit]
  ): Disposable = js.native

  def dispatch(target: Element, commandName: String): Unit = js.native
}

@js.native
trait TextEditor extends js.Object {
  val id: Long = js.native
  
  val firstVisibleScreenRow: Long = js.native
  val rowsPerPage: Long = js.native

  // File Details
  def getTitle(): String = js.native
  def getLongTitle(): String = js.native


  def getPath(): js.UndefOr[String] = js.native
  def getURI(): js.UndefOr[String] = js.native
  def insertNewline(): Unit = js.native
  def isModified(): Boolean = js.native
  def isEmpty(): Boolean = js.native
  // def getEncoding(): Encoding = js.native
  def setEncoding(encoding: String): Unit = js.native
  def getTabLength(): Long = js.native

  // File Operations
  def save(): Unit = js.native
  def saveAs(filePath: String): Unit = js.native

  // Reading Text
  def getText(): String = js.native
  def getLineCount(): Long = js.native
}

class ConfigOptions (
  sources: js.UndefOr[js.Array[String]] = js.undefined,
  excludeSources: js.UndefOr[js.Array[String]] = js.undefined
) extends js.Object
  
@js.native
trait ConfigChange extends js.Object {
  val newValue: js.Any = js.native
  val oldValue: js.Any = js.native
}

@js.native
trait Config extends js.Object {
  
  def observe(keyPath: String, callback: js.Any => Unit): Disposable = js.native
  def onDidChange(callback: ConfigChange => Unit): Disposable = js.native
  def onDidChange(keyPath: String, callback: ConfigChange => Unit): Disposable = js.native
  
  def get(key: String, options: ConfigOptions = js.native): js.Any = js.native
  def set(key: String, value: js.Any = js.native, options: ConfigOptions = js.native): Boolean = js.native
  def unset(key: String, options: ConfigOptions = js.native): Boolean = js.native
}

@js.native
trait Notification extends js.Object {
  // Methods
  def getType(): String = js.native
  def getMessage(): String = js.native
  def dismiss(): Unit = js.native
}

class NotificationButton(
  className: js.UndefOr[String] = js.undefined,
  onDidClick: js.UndefOr[() => Unit] = js.undefined,
  text: js.UndefOr[String] = js.undefined
) extends js.Object

class NotificationOptions(
  detail: js.UndefOr[String] = js.undefined,
  dismissable: js.UndefOr[Boolean] = js.undefined,
  description: js.UndefOr[String] = js.undefined,
  icon: js.UndefOr[String] = js.undefined,
  buttons: js.UndefOr[js.Array[NotificationButton]] = js.undefined
) extends js.Object

@js.native
trait NotificationManager extends js.Object {

  def onDidAddNotification(callback: Notification => Unit): Disposable = js.native
  
  // Adding Notifications
  def addInfo(msg: String, options: NotificationOptions = js.native): Notification = js.native
  def addError(msg: String, options: NotificationOptions = js.native): Notification = js.native
  def addFatalError(msg: String, options: NotificationOptions = js.native): Notification = js.native
  def addSuccess(msg: String, options: NotificationOptions = js.native): Notification = js.native
  def addWarning(msg: String, options: NotificationOptions = js.native): Notification = js.native
  
  // Getting Notifications
  def getNotifications(): js.Array[Notification] = js.native
}

@js.native
@JSGlobal("atom")
object Atom extends js.Object {
  val commands: CommandRegistry = js.native
  val config: Config = js.native
  val notifications: NotificationManager = js.native
  
  def inDevMode(): Boolean = js.native
  def inSafeMode(): Boolean = js.native
  def inSpecMode(): Boolean = js.native
  def getVersion(): String = js.native
}
