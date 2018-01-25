package laughedelic.atom.languageclient

import scala.scalajs.js, js.annotation._, js.|
import io.scalajs.RawOptions
import io.scalajs.nodejs.{ child_process => cp }
import io.scalajs.nodejs.net
import laughedelic.atom.ide.ui.busysignal
import laughedelic.atom.TextEditor

// https://github.com/atom/atom-languageclient/blob/master/lib/auto-languageclient.js
@js.native @JSImport("atom-languageclient", "AutoLanguageClient")
class AutoLanguageClient extends js.Object {
  // ---------------------------------------------------------------------------
  // Methods that every Atom package must have
  def activate(): Unit = js.native
  def deactivate(): js.Promise[js.Any] = js.native

  // ---------------------------------------------------------------------------
  // Providers
  // NOTE: types here are not important, because you're not going to use these methods directly, but you may need to export them explicitly to wire the services
  def provideOutlines(): js.Any = js.native
  def provideDefinitions(): js.Any = js.native
  def provideCodeFormat(): js.Any = js.native
  def provideAutocomplete(): js.Any = js.native
  def provideFindReferences(): js.Any = js.native
  def provideCodeHighlight(): js.Any = js.native
  def provideCodeActions(): js.Any = js.native

  // ---------------------------------------------------------------------------
  // Consumers
  // NOTE: types here are not important, because you're not going to use these methods directly, but you may need to export them explicitly to wire the services
  def consumeDatatip(service: js.Any): Unit = js.native
  def consumeLinterV2(registerIndie: js.Any): Unit = js.native
  def consumeSignatureHelp(registry: js.Any): js.Any = js.native
  def consumeBusySignal(service: js.Any): js.Any = js.native

  // ---------------------------------------------------------------------------
  // Methods obligatory to override

  def getGrammarScopes(): js.Array[String] = js.native
  def getLanguageName(): String = js.native
  def getServerName(): String = js.native
  def startServerProcess(projectPath: String): cp.ChildProcess | js.Promise[cp.ChildProcess] = js.native

  // ---------------------------------------------------------------------------
  // Rest

  val processStdErr: String = js.native
  val logger: Logger = js.native
  val name: String = js.native
  val socket: net.Socket = js.native

  /** Available if consumeBusySignal is setup */
  val busySignalService: js.UndefOr[busysignal.BusySignalService] = js.native

  /** Determine whether we should start a server for a given editor if we don't have one yet */
  // TODO: editor: atom$TextEditor
  def shouldStartForEditor(editor: js.Any): Boolean = js.native

  /** Return the parameters used to initialize a client - you may want to extend capabilities */
  // TODO: return ls.InitializeParams
  def getInitializeParams(projectPath: String, process: cp.ChildProcess): js.Any = js.native

  /** Early wire-up of listeners before initialize method is sent */
  // TODO: connection: ls.LanguageClientConnection
  def preInitialization(connection: js.Any): Unit = js.native

  /** Late wire-up of listeners after initialize method has been sent */
  // TODO: server: ActiveServer
  def postInitialization(server: js.Any): Unit = js.native

  /** Determine whether to use ipc, stdio or socket to connect to the server */
  // TODO: return ConnectionType = 'stdio' | 'socket' | 'ipc'
  def getConnectionType(): String = js.native

  def exitCleanup(): Unit = js.native

  def spawnChildNode(args: js.Array[String], options: RawOptions = js.native): cp.ChildProcess = js.native

  /** By default LSP logging is switched off but you can switch it on via the core.debugLSP setting */
  def getLogger(): Logger = js.native

  // Starts the server by starting the process, then initializing the language server and starting adapters
  // TODO: return js.Promise[ActiveServer]
  def startServer(projectPath: String): js.Promise[js.Any] = js.native

  def captureServerErrors(childProcess: cp.ChildProcess): Unit = js.native
  def handleSpawnFailure(err: js.Any): Unit = js.native

  // TODO: editor: atom$TextEditor
  def shouldSyncForEditor(editor: TextEditor, projectPath: String): Boolean = js.native

  // TODO: editor: atom$TextEditor
  def isFileInProject(editor: TextEditor, projectPath: String): Boolean = js.native

  /** `didChangeWatchedFiles` message filtering, override for custom logic.
   * @param filePath path of a file that has changed in the project path
   * @return false => message will not be sent to the language server
   */
  def filterChangeWatchedFiles(filePath: String): Boolean = js.native
}
