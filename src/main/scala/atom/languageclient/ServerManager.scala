package laughedelic.atom.languageclient

import io.scalajs.nodejs.{ child_process => cp }
import laughedelic.atom.CompositeDisposable
import scala.scalajs.js, js.|

// Completion options.
@js.native
trait CompletionOptions extends js.Object {
  // The server provides support to resolve additional information for a completion item.
  val resolveProvider: js.UndefOr[Boolean] = js.native
  // The characters that trigger completion automatically.
  val triggerCharacters: js.UndefOr[js.Array[String]] = js.native
}

// Signature Help options.
@js.native
trait SignatureHelpOptions extends js.Object {
  // The characters that trigger signature help automatically.
  val triggerCharacters: js.UndefOr[js.Array[String]] = js.native
}

// Code Lens options.
@js.native
trait CodeLensOptions extends js.Object {
  // Code lens has a resolve provider as well.
  val resolveProvider: js.UndefOr[Boolean] = js.native
}

// Document Link options
@js.native
trait DocumentLinkOptions extends js.Object {
  // Document links have a resolve provider as well.
  val resolveProvider: js.UndefOr[Boolean] = js.native
}

// Execute Command options.
@js.native
trait ExecuteCommandOptions extends js.Object {
  // The commands to be executed on the server
  val commands: js.UndefOr[js.Array[String]] = js.native
}

// Document save options.
@js.native
trait SaveOptions extends js.Object {
  // The client is supposed to include the content on save.
  val includeText: js.UndefOr[Boolean] = js.native
}

// Text document synchronization options.
@js.native
trait TextDocumentSyncOptions extends js.Object {
  // Open and close notifications are sent to the server.
  val openClose: js.UndefOr[Boolean] = js.native
  // Change notificatins are sent to the server.
  val change: js.UndefOr[Long] = js.native
  // Will save notifications are sent to the server.
  val willSave: js.UndefOr[Boolean] = js.native
  // Will save wait until requests are sent to the server.
  val willSaveWaitUntil: js.UndefOr[Boolean] = js.native
  // Save notifications are sent to the server.
  val save: js.UndefOr[SaveOptions] = js.native
}

// Format Document on type options
@js.native
trait DocumentOnTypeFormattingOptions extends js.Object {
  // A character on which formatting should be triggered, like `};`.
  val firstTriggerCharacter: String = js.native
  // More trigger characters.
  val moreTriggerCharacter: js.UndefOr[js.Array[String]] = js.native
};

// Defines capabilities that will be returned by the server.
@js.native
trait ServerCapabilities extends js.Object {
  // Defines how text documents are synced.
  val textDocumentSync: js.UndefOr[TextDocumentSyncOptions | Long] = js.native
  // The server provides hover support.
  val hoverProvider: js.UndefOr[Boolean] = js.native
  // The server provides completion support.
  val completionProvider: js.UndefOr[CompletionOptions] = js.native
  // The server provides signature help support.
  val signatureHelpProvider: js.UndefOr[SignatureHelpOptions] = js.native
  // The server provides goto definition support.
  val definitionProvider: js.UndefOr[Boolean] = js.native
  // The server provides find references support.
  val referencesProvider: js.UndefOr[Boolean] = js.native
  // The server provides document highlight support.
  val documentHighlightProvider: js.UndefOr[Boolean] = js.native
  // The server provides document symbol support.
  val documentSymbolProvider: js.UndefOr[Boolean] = js.native
  // The server provides workspace symbol support.
  val workspaceSymbolProvider: js.UndefOr[Boolean] = js.native
  // The server provides code actions.
  val codeActionProvider: js.UndefOr[Boolean] = js.native
  // The server provides code lens.
  val odeLensProvider: js.UndefOr[CodeLensOptions] = js.native
  // The server provides document formatting.
  val documentFormattingProvider: js.UndefOr[Boolean] = js.native
  // The server provides document range formatting.
  val documentRangeFormattingProvider: js.UndefOr[Boolean] = js.native
  // The server provides document formatting on typing.
  val documentOnTypeFormattingProvider: js.UndefOr[DocumentOnTypeFormattingOptions] = js.native
  // The server provides rename support.
  val renameProvider: js.UndefOr[Boolean] = js.native
  // The server provides document link support.
  val documentLinkProvider: js.UndefOr[DocumentLinkOptions] = js.native
  // The server provides execute command support.
  val executeCommandProvider: js.UndefOr[ExecuteCommandOptions] = js.native
  // Experimental server capabilities.
  val experimental: js.UndefOr[js.Any] = js.native
}

// The necessary elements for a server that has started or is starting.
@js.native
trait ActiveServer extends js.Object {

  val disposable: CompositeDisposable = js.native
  val projectPath: String = js.native
  val process: cp.ChildProcess = js.native
  val connection: LanguageClientConnection = js.native
  val capabilities: ServerCapabilities = js.native

}