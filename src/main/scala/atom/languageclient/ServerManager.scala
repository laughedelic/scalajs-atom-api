package laughedelic.atom.languageclient

import io.scalajs.nodejs.{ child_process => cp }
import laughedelic.atom.CompositeDisposable
import scala.scalajs.js

// The necessary elements for a server that has started or is starting.
@js.native
trait ActiveServer extends js.Object {

  val disposable: CompositeDisposable = js.native
  val projectPath: String = js.native
  val process: cp.ChildProcess = js.native
  val connection: LanguageClientConnection = js.native

}