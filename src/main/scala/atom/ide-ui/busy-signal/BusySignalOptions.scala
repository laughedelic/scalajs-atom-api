package laughedelic.atom.ide.ui.busysignal

import scala.scalajs.js, js.annotation._

/**
  * @param onlyForFile
  *   Can say that a busy signal will only appear when a given file is open.
  *   Default = null, meaning the busy signal applies to all files.
  * @param waitingFor
  *   Is user waiting for computer to finish a task? (traditional busy spinner)
  *   or is the computer waiting for user to finish a task? (action required)
  *   Default = spinner.
  * @param debounce
  *   Debounce it? default = true for busy-signal, and false for action-required.
  * @param onDidClick
  *   If onClick is set, then the tooltip will be clickable. Default = null.
  * @param revealTooltip
  *   If set to true, the busy signal tooltip will be immediately revealed
  *   when it first becomes visible (without explicit mouse interaction).
  * @see https://github.com/facebook-atom/atom-ide-ui/blob/master/modules/atom-ide-ui/pkg/atom-ide-busy-signal/lib/types.js
  */
class BusySignalOptions(
  onlyForFile: js.UndefOr[String] = js.undefined, // NuclideUri
  waitingFor: js.UndefOr[String] = js.undefined, // 'computer' | 'user'
  debounce: js.UndefOr[Boolean] = js.undefined,
  onDidClick: js.UndefOr[js.Function1[Unit, Unit]] = js.undefined,
  revealTooltip: js.UndefOr[Boolean] = js.undefined,
) extends js.Object
