package laughedelic.atom.config

import scala.scalajs.js
import laughedelic.atom.{ Atom, Disposable, ConfigChange }

class ConfigSchema extends js.Object { conf: Singleton =>

  def init(prefix: String): ConfigSchema = {
    conf.asInstanceOf[js.Dictionary[js.Dynamic]]
      .foreach { case (key, value) =>
        val newPrefix = s"${prefix}.${key}"
        if (value.isInstanceOf[Setting[_]])
          value.updateDynamic("label")(newPrefix)
        else if (value.isInstanceOf[SettingsGroup[_]])
          value.asInstanceOf[SettingsGroup[ConfigSchema]]
            .properties.init(newPrefix)
      }
    conf
  }

  def onDidChange(callback: ConfigChange => Unit): Disposable =
    Atom.config.onDidChange(callback)
}
