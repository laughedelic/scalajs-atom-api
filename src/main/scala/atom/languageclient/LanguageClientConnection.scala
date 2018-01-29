package laughedelic.atom.languageclient

import io.scalajs.nodejs.events.EventEmitter
import scala.scalajs.js

// Public: Parameters to send with a workspace/executeCommand request.
class ExecuteCommandParams (
  // The identifier of the actual command handler.
  val command: String,
  // Arguments that the command should be invoked with.
  val arguments: js.UndefOr[js.Array[js.Any]] = js.undefined
) extends js.Object

@js.native 
trait LanguageClientConnection extends EventEmitter {

  // Public: Send a `workspace/executeCommand` request.
  //
  // * `params` The {ExecuteCommandParams} specifying the command and arguments
  // the language server should execute (these commands are usually from {CodeLens} or {CodeAction}
  // responses).
  // Returns a {Promise} containing anything.
  def executeCommand(params: ExecuteCommandParams): js.Promise[js.Any] = js.native
}