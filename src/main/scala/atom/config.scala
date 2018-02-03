package laughedelic.atom.ide.scala

import scala.scalajs.js, js.annotation._, js.Dynamic.global, js.|

class SettingType[T](
  val name: String,
  val itemsType: js.UndefOr[String] = js.undefined,
)

object SettingType {
  implicit val string:  SettingType[String]  = new SettingType[String]("string")
  implicit val integer: SettingType[Int]     = new SettingType[Int]("integer")
  implicit val number:  SettingType[Double]  = new SettingType[Double]("number")
  implicit val boolean: SettingType[Boolean] = new SettingType[Boolean]("boolean")
  // TODO: Color option (returns Color type from Atom core API)

  implicit def array[T](
    implicit tpe: SettingType[T]
  ): SettingType[js.Array[T]] = new SettingType[js.Array[T]](
    name = "array",
    itemsType = tpe.name,
  )
}

class AllowedValue[V](
  val value: V,
  val description: js.UndefOr[String] = js.undefined
) extends js.Object

class Setting[T](
  val title: String,
  val default: T,
  val description: js.UndefOr[String] = js.undefined,
  val order: js.UndefOr[Int] = js.undefined,
  val enum: js.UndefOr[js.Array[AllowedValue[T]] | js.Array[T]] = js.undefined,
  val minimum: js.UndefOr[T] = js.undefined,
  val maximum: js.UndefOr[T] = js.undefined,
)(implicit tpe: SettingType[T]) extends js.Object {
  final val `type`: String = tpe.name

  final val label: js.UndefOr[String] = js.undefined

  final val items = tpe.itemsType.map { it =>
    js.Dynamic.literal(
      `type` = it,
      minimum = minimum.asInstanceOf[js.Any],
      maximum = maximum.asInstanceOf[js.Any],
    )
  }
}

class Group[P <: js.Object](
  val title: String,
  val properties: P,
  val description: js.UndefOr[String] = js.undefined,
  val order: js.UndefOr[Int] = js.undefined,
) extends js.Object {
  final val `type`: String = "object"
}

object Group {
  implicit def groupProps[P <: js.Object](gr: Group[P]): P = gr.properties

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
}