package laughedelic.atom.config

import scala.scalajs.js

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
