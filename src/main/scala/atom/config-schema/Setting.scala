package laughedelic.atom.config

import scala.scalajs.js, js.|
import laughedelic.atom.{ Atom, Disposable, ConfigChange }

class AllowedValue[V](
  val value: V,
  val description: js.UndefOr[String] = js.undefined
) extends js.Object

class Setting[T](
  val default: T,
  val title:       js.UndefOr[String] = js.undefined,
  val description: js.UndefOr[String] = js.undefined,
  val order:       js.UndefOr[Int] = js.undefined,
  val enum:        js.UndefOr[js.Array[T] | js.Array[AllowedValue[T]]] = js.undefined,
  val minimum:     js.UndefOr[T] = js.undefined,
  val maximum:     js.UndefOr[T] = js.undefined,
)(implicit tpe: SettingType[T]) extends js.Object {

  final val `type`: String = tpe.name

  // final val label: js.UndefOr[String] = js.undefined

  final val items = tpe.itemsType.map { it =>
    js.Dynamic.literal(
      `type` = it,
      minimum = minimum.asInstanceOf[js.Any],
      maximum = maximum.asInstanceOf[js.Any],
    )
  }
}

object Setting {

  implicit class SettingOps[T](setting: Setting[T]) {

    def label: String =
      setting.asInstanceOf[js.Dynamic].label.asInstanceOf[String]

    def get: T =
      Atom.config.get(label).asInstanceOf[T]

    def set(value: T): Boolean =
      Atom.config.set(label, value.asInstanceOf[js.Any])

    def unset(): Boolean =
      Atom.config.unset(label)

    def update(upd: T => T): Boolean =
      set( upd(get) )

    def observe(callback: js.Any => Unit): Disposable =
      Atom.config.observe(label, callback)

    def onDidChange(callback: ConfigChange => Unit): Disposable =
      Atom.config.onDidChange(label, callback)
  }

}
