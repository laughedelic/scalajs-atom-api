package laughedelic.atom.config

import scala.scalajs.js

class SettingsGroup[S <: ConfigSchema](
  val title: js.UndefOr[String] = js.undefined,
  val description: js.UndefOr[String] = js.undefined,
  val order: js.UndefOr[Int] = js.undefined,
  val collapsed: js.UndefOr[Boolean] = js.undefined,
  schema: S,
) extends js.Object {

  final val `type`: String = "object"

  final val properties = schema
}

object SettingsGroup {
  implicit def groupProps[S <: ConfigSchema](gr: SettingsGroup[S]): S = gr.properties
}
