package laughedelic.atom.ide.scala

import scala.scalajs.js, js.annotation._, js.Dynamic.global, js.|

sealed trait AnyOptionType extends js.Object {
  type ValType
  val valType: String
}

sealed trait AnyPrimitiveOptionType extends AnyOptionType
sealed abstract class PrimitiveOptionType[T](val valType: String)
  extends AnyPrimitiveOptionType { type ValType = T }

object string  extends PrimitiveOptionType[String]("string")
object integer extends PrimitiveOptionType[Int]("integer")
object number  extends PrimitiveOptionType[Double]("number")
object boolean extends PrimitiveOptionType[Boolean]("boolean")
// TODO: Color option (returns Color type from Atom core API)

class array[IT <: AnyPrimitiveOptionType](val itemsType: IT) extends AnyOptionType {
  type ValType = js.Array[IT#ValType]
  val valType = "array"
}

class AllowedValue[V](
  val value: V,
  val description: js.UndefOr[String] = js.undefined
) extends js.Object

abstract class AnyConfigOption extends js.Object {
  type Tpe <: AnyOptionType
  // val  tpe: Tpe
  // final val `type`: String = tpe.valType

  val title: String
  val default: Tpe#ValType

  val description: js.UndefOr[String]
  val order: js.UndefOr[Int]
}

class Opt[T <: AnyOptionType](
  tpe: T,
  val title: String,
  val default: T#ValType,
  val description: js.UndefOr[String] = js.undefined,
  val order: js.UndefOr[Int] = js.undefined,
  val enum: js.UndefOr[js.Array[AllowedValue[T#ValType]]] = js.undefined,
  val minimum: js.UndefOr[T#ValType] = js.undefined,
  val maximum: js.UndefOr[T#ValType] = js.undefined,
) extends js.Object {
  type Tpe = T
  final val `type`: String = tpe.valType
}

class ArrayOpt[T <: AnyPrimitiveOptionType](
  tpe: T,
  val title: String,
  val default: js.Array[T#ValType],
  val description: js.UndefOr[String] = js.undefined,
  val order: js.UndefOr[Int] = js.undefined,
  val minimum: js.UndefOr[T#ValType] = js.undefined,
  val maximum: js.UndefOr[T#ValType] = js.undefined,
) extends AnyConfigOption {
  type Tpe = array[T]
  // final val tpe = new array(tpe)
  final val `type`: String = "array"

  final val items = js.Dynamic.literal(
    `type` = tpe.valType,
    minimum = minimum.asInstanceOf[js.Any],
    maximum = maximum.asInstanceOf[js.Any],
  )
}

object ServerTypes {
  val Scalameta = new AllowedValue[String](
    value = "scalameta",
    description = "Scalameta"
  )

  val Ensime = new AllowedValue[String](
    value = "ensime",
    description = "ENSIME (experimental)"
  )
}

object Config extends js.Object {

  val serverType = new Opt(string,
    title = "Language Server Type",
    description = "Don't change this option unless you know what you're doing",
    default = ServerTypes.Scalameta.value,
    order = 1,
    enum = js.Array(
      ServerTypes.Scalameta,
      ServerTypes.Ensime
    ),
  )

  val serverVersion = new Opt(string,
    title = "Language Server Version",
    default = "5ddb92a9",
    order = 2,
  )

  val javaOpts = new ArrayOpt(string,
    title = "Extra JVM options",
    default = js.Array()
  )

  val javaHome = new Opt(string,
    title = "Java Home",
    description = "Plugin will try to guess your Java Home path, but if you have a very specific setup you can use this option to set it explicitly",
    default = "",
  )

}
