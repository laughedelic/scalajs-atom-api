package laughedelic.atom

import scala.scalajs.js

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

  def observe(keyPath: String, callback: js.Function1[js.Any, Unit]): Disposable = js.native
  def onDidChange(callback: js.Function1[ConfigChange, Unit]): Disposable = js.native
  def onDidChange(keyPath: String, callback: js.Function1[ConfigChange, Unit]): Disposable = js.native

  def get(key: String, options: ConfigOptions = js.native): js.Any = js.native
  def set(key: String, value: js.Any = js.native, options: ConfigOptions = js.native): Boolean = js.native
  def unset(key: String, options: ConfigOptions = js.native): Boolean = js.native
}
