package laughedelic.atom

import scala.scalajs.js

/** Represents the state of the user interface for the entire window. */
@js.native
trait Workspace extends js.Object {
  // Event Subscription
  /**
   *  Invoke the given callback with all current and future text editors in
   *  the workspace.
   */
  def observeTextEditors(callback: js.Function1[TextEditor, Unit]): Disposable = js.native

  /**
   *  Invoke the given callback when a text editor becomes the active text editor and
   *  when there is no longer an active text editor.
   */
  def onDidChangeActiveTextEditor(callback: js.Function1[js.UndefOr[TextEditor], Unit]): Disposable = js.native

  /**
   *  Invoke the given callback with the current active text editor (if any), with all
   *  future active text editors, and when there is no longer an active text editor.
   */
  def observeActiveTextEditor(callback: js.Function1[js.UndefOr[TextEditor], Unit]): Disposable = js.native

  /** Get all text editors in the workspace. */
  def getTextEditors(): js.Array[TextEditor] = js.native

  /** Get the workspace center's active item if it is a TextEditor. */
  def getActiveTextEditor(): js.UndefOr[TextEditor] = js.native
}
