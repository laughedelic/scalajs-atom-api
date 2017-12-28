package laughedelic.atom.ide.scala

import scala.scalajs.js

sealed trait AnyOptionType {
  type ValType
  val valType: String
}
sealed trait AnyPrimitiveOptionType extends AnyOptionType
sealed abstract class PrimitiveOptionType[T](
  val valType: String
) extends AnyPrimitiveOptionType { type ValType = T }

case object StringType  extends PrimitiveOptionType[String]("string")
case object IntegerType extends PrimitiveOptionType[Int]("integer")
case object DoubleType  extends PrimitiveOptionType[Double]("number")
case object BooleanType extends PrimitiveOptionType[Boolean]("boolean")
// TODO: Color option (returns Color type from Atom core API)
case class ArrayType[IT <: AnyPrimitiveOptionType](val itemsType: IT) extends AnyOptionType {
  type ValType = js.Array[IT#ValType]
  val valType = "array"
}

case class AllowedValue[T <: AnyOptionType](
  val value: T#ValType,
  val description: js.UndefOr[String] = js.undefined
)

trait AnyConfigOption {
  type Tpe <: AnyOptionType
  val tpe: Tpe
  val default: Tpe#ValType

  val title: String

  val description: js.UndefOr[String] = js.undefined
  val order: js.UndefOr[Int] = js.undefined
  val enum: js.UndefOr[js.Array[Tpe#ValType]] = js.undefined
}

abstract class ConfigOption[T <: AnyOptionType](val tpe: T) extends AnyConfigOption { type Tpe = T }

trait NumberOption extends AnyConfigOption {
  val minimum: js.UndefOr[Tpe] = js.undefined
  val maximum: js.UndefOr[Tpe] = js.undefined
}


case class StringOption(
  val title: String,
  val default: String
) extends ConfigOption(StringType)

case class IntegerOption(
  val title: String,
  val default: Int
) extends ConfigOption(IntegerType) with NumberOption

case class DoubleOption(
  val title: String,
  val default: Double
) extends ConfigOption(DoubleType) with NumberOption

case class BooleanOption(
  val title: String,
  val default: Boolean
) extends ConfigOption(BooleanType)

case class ArrayOption[IT <: AnyPrimitiveOptionType](
  val itemsType: IT
)(val title: String,
  val default: js.Array[IT#ValType]
) extends ConfigOption(ArrayType[IT](itemsType)) {
  val items = js.Dynamic.literal(
    "type" -> itemsType.valType
  )
}

case object Config {
}
