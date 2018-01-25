package laughedelic.atom

import scala.scalajs.js, js.annotation._

@js.native
trait Model extends js.Object{
  def destroy(): Unit = js.native
  def isDestroyed(): Boolean = js.native
}


@js.native
trait TextEditor extends Model {
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


@js.native
trait Disposable extends js.Object {
  def dispose(): Unit = js.native
}
  
@js.native
trait ConfigChange extends js.Object {
  val newValue: js.Any = js.native
  val oldValue: js.UndefOr[js.Any] = js.native
}

@js.native
trait Config extends js.Object {
  
  def observe(keyPath: String, callback: ConfigChange => Unit): Disposable = js.native
  def onDidChange(callback: ConfigChange => Unit): Disposable = js.native
  def onDidChange(keyPath: String, callback: ConfigChange => Unit): Disposable = js.native
  
  def get(key: String): js.Any = js.native
  def set(key: String, value: js.UndefOr[js.Any] = js.undefined): Boolean = js.native
  def unset(key: String): Boolean = js.native
}

@js.native
trait Notification extends js.Object {
  // Methods
  def getType(): String = js.native
  def getMessage(): String = js.native
  def getOptions(): js.Object = js.native
  def dismiss(): Unit = js.native
}

@JSExportAll
case class NotificationOptions(
  detail: js.UndefOr[String] = js.undefined,
  dismissable: js.UndefOr[Boolean] = js.undefined,
  description: js.UndefOr[String] = js.undefined,
  icon: js.UndefOr[String] = js.undefined
)

@js.native
trait NotificationManager extends js.Object {
  
  // Adding Notifications
  def addInfo(msg: String, options: js.UndefOr[NotificationOptions] = js.undefined): Notification = js.native
  def addError(msg: String, options: js.UndefOr[NotificationOptions] = js.undefined): Notification = js.native
  def addFatalError(msg: String, options: js.UndefOr[NotificationOptions] = js.undefined): Notification = js.native
  def addSuccess(msg: String, options: js.UndefOr[NotificationOptions] = js.undefined): Notification = js.native
  def addWarning(msg: String, options: js.UndefOr[NotificationOptions] = js.undefined): Notification = js.native
  
  // Getting Notifications
  def getNotifications(): js.Array[Notification] = js.native
}

@js.native
@JSGlobal("atom")
object Atom extends js.Object {
  val config: Config = js.native
  val notifications: NotificationManager = js.native
  
  def inDevMode(): Boolean = js.native
  def inSafeMode(): Boolean = js.native
  def inSpecMode(): Boolean = js.native
  def getVersion(): String = js.native
}
