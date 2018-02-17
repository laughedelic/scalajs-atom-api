package laughedelic.atom.config

import scala.scalajs.js

class SettingsGroup[S <: ConfigSchema](
  schema: S,
  val title: js.UndefOr[String] = js.undefined,
  val collapsed: js.UndefOr[Boolean] = js.undefined,
) extends js.Object {

  final val `type`: String = "object"
  final val order: js.UndefOr[Int] = js.undefined

  final val properties: S = schema
}

object SettingsGroup {
  implicit def groupProps[S <: ConfigSchema](gr: SettingsGroup[S]): S = gr.properties
}
