package laughedelic.atom.ide.scala

import scala.scalajs.js, js.annotation._, js.Dynamic.global, js.|

class OptionType[T](
  val name: String,
  val itemsType: js.UndefOr[String] = js.undefined,
)

object OptionType {
  implicit val string:  OptionType[String]  = new OptionType[String]("string")
  implicit val integer: OptionType[Int]     = new OptionType[Int]("integer")
  implicit val number:  OptionType[Double]  = new OptionType[Double]("number")
  implicit val boolean: OptionType[Boolean] = new OptionType[Boolean]("boolean")
  // TODO: Color option (returns Color type from Atom core API)

  implicit def array[T](
    implicit tpe: OptionType[T]
  ): OptionType[js.Array[T]] = new OptionType[js.Array[T]](
    name = "array",
    itemsType = tpe.name,
  )
}

class AllowedValue[V](
  val value: V,
  val description: js.UndefOr[String] = js.undefined
) extends js.Object

class Opt[T](
  val title: String,
  val default: T,
  val description: js.UndefOr[String] = js.undefined,
  val order: js.UndefOr[Int] = js.undefined,
  val enum: js.UndefOr[js.Array[AllowedValue[T]]] = js.undefined,
  val minimum: js.UndefOr[T] = js.undefined,
  val maximum: js.UndefOr[T] = js.undefined,
)(implicit tpe: OptionType[T]) extends js.Object {
  final val `type`: String = tpe.name

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
}
