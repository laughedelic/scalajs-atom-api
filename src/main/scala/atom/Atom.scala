package laughedelic.atom

import org.scalajs.dom._
import scala.scalajs.js, js.annotation._, js.|


@js.native
trait Disposable extends js.Object {
  def dispose(): Unit = js.native
}

/**
 *  An object that aggregates multiple Disposable instances together into a
 *  single disposable, so they can all be disposed as a group.
 */
@js.native
trait CompositeDisposable extends Disposable {
  /**
   *  Dispose all disposables added to this composite disposable.
   *  If this object has already been disposed, this method has no effect.
   */
  override def dispose(): Unit = js.native
  
  // Managing Disposables
  /**
   *  Add disposables to be disposed when the composite is disposed.
   *  If this object has already been disposed, this method has no effect.
   */
  def add(disposables: Disposable*): Unit = js.native
  
  /* Remove a previously added disposable. */
  def remove(disposable: Disposable): Unit = js.native
  
  /* Alias to CompositeDisposable::remove. */
  def delete(disposable: Disposable): Unit = js.native
  
  /*
   *  Clear all disposables. They will not be disposed by the next call to
   *  dispose.
   */
  def clear(): Unit = js.native
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

/** Represents the state of the user interface for the entire window. */
@js.native
trait Workspace extends js.Object {
  // Event Subscription
  /**
   *  Invoke the given callback with all current and future text editors in
   *  the workspace.
   */
  def observeTextEditors(callback: TextEditor => Unit): Disposable = js.native

  /**
   *  Invoke the given callback when a text editor becomes the active text editor and
   *  when there is no longer an active text editor.
   */
  def onDidChangeActiveTextEditor(callback: js.UndefOr[TextEditor] => Unit): Disposable = js.native

  /**
   *  Invoke the given callback with the current active text editor (if any), with all
   *  future active text editors, and when there is no longer an active text editor.
   */
  def observeActiveTextEditor(callback: js.UndefOr[TextEditor] => Unit): Disposable = js.native

  /** Get all text editors in the workspace. */
  def getTextEditors(): js.Array[TextEditor] = js.native

  /** Get the workspace center's active item if it is a TextEditor. */
  def getActiveTextEditor(): js.UndefOr[TextEditor] = js.native
}

class ConfigOptions (
  val sources: js.UndefOr[js.Array[String]] = js.undefined,
  val excludeSources: js.UndefOr[js.Array[String]] = js.undefined
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
  val className: js.UndefOr[String] = js.undefined,
  val onDidClick: js.UndefOr[() => Unit] = js.undefined,
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
  val workspace: Workspace = js.native

  def inDevMode(): Boolean = js.native
  def inSafeMode(): Boolean = js.native
  def inSpecMode(): Boolean = js.native
  def getVersion(): String = js.native
}
