package laughedelic.atom

import org.scalajs.dom._
import scala.scalajs.js, js.|

class CommandListener(
  val displayName: js.UndefOr[String] = js.undefined,
  val description: js.UndefOr[String] = js.undefined,
  val hiddenInCommandPalette: js.UndefOr[Boolean] = js.undefined
)(val didDispatch: js.Function1[js.Any, Unit]) extends js.Object

@js.native
trait CommandRegistry extends js.Object {
  def add(
    target: String | Element,
    commandName: String,
    listener: CommandListener | js.Function1[js.Any, Unit]
  ): Disposable = js.native

  def add(
    target: String | Element,
    commands: js.Dictionary[js.Function1[js.Any, Unit]]
  ): Disposable = js.native

  def dispatch(target: Element, commandName: String): Unit = js.native
}
