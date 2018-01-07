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

  // def properties() =
  //   this.asInstanceOf[js.Dictionary[js.Any]]
  //     .filter { case (_, value) => value.isInstanceOf[js.Object] }

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

class jvmOpts extends js.Object {
  val javaOpts = new Opt[js.Array[String]](
    title = "Extra JVM options",
    default = js.Array()
  )

  val javaHome = new Opt[String](
    title = "Java Home",
    description = "Plugin will try to guess your Java Home path, but if you have a very specific setup you can use this option to set it explicitly",
    default = "",
  )
}

object Config extends js.Object {

  val serverType = new Opt[String](
    title = "Language Server Type",
    description = "Don't change this option unless you know what you're doing",
    default = ServerTypes.Scalameta.value,
    order = 1,
    enum = js.Array(
      ServerTypes.Scalameta,
      ServerTypes.Ensime
    ),
  )

  val serverVersion = new Opt[String](
    title = "Language Server Version",
    default = "5ddb92a9",
    order = 2,
  )

  val jvm = new Group(
    title = "Java-related settings",
    order = 3,
    properties = new jvmOpts
  )

}
