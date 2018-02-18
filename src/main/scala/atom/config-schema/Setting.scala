package laughedelic.atom.config

import scala.scalajs.js, js.|
import laughedelic.atom.{ Atom, Disposable, ConfigChange }

/** Used to enumerate allowed values of a setting with their descriptions */
class AllowedValue[V](
  val value: V,
  val description: js.UndefOr[String] = js.undefined
) extends js.Object

class Setting[T](
  val default: T,
  val title:       js.UndefOr[String] = js.undefined,
  val description: js.UndefOr[String] = js.undefined,
  val enum:        js.UndefOr[js.Array[T] | js.Array[AllowedValue[T]]] = js.undefined,
  val minimum:     js.UndefOr[T] = js.undefined,
  val maximum:     js.UndefOr[T] = js.undefined,
)(implicit tpe: SettingType[T]) extends js.Object {

  final val `type`: String = tpe.name
  final val order: js.UndefOr[Int] = js.undefined
  final val label: js.UndefOr[String] = js.undefined

  final val items = tpe.itemsType.map { it =>
    js.Dynamic.literal(
      `type` = it,
      minimum = minimum.asInstanceOf[js.Any],
      maximum = maximum.asInstanceOf[js.Any],
    )
  }
}

/** A typed and safe version of [[ConfigChange]] */
case class SettingChange[T](
  val oldValue: Option[T],
  val newValue: T,
)

object SettingChange {
  def apply[T](change: ConfigChange): SettingChange[T] =
    SettingChange[T](
      // NOTE: old value may be undefined
      js.defined(change.oldValue.asInstanceOf[T]).toOption,
      change.newValue.asInstanceOf[T]
    )
}

object Setting {

  implicit class SettingOps[T](setting: Setting[T]) {
    private def label: String = setting.label.get

    def get: T =
      Atom.config.get(label).asInstanceOf[T]

    def set(value: T): Boolean =
      Atom.config.set(label, value.asInstanceOf[js.Any])

    def unset(): Boolean =
      Atom.config.unset(label)

    def update(upd: T => T): Boolean =
      set( upd(get) )

    def observe(callback: T => Unit): Disposable =
      Atom.config.observe(label, { value: js.Any =>
        callback(value.asInstanceOf[T])
      })

    def onDidChange(callback: SettingChange[T] => Unit): Disposable =
      Atom.config.onDidChange(label, { change: ConfigChange =>
        callback(SettingChange[T](change))
      })
  }

}
