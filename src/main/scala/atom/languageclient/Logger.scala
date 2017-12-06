package laughedelic.atom.languageclient

import scala.scalajs.js

// https://github.com/atom/atom-languageclient/blob/master/lib/logger.js
@js.native
trait Logger extends js.Object {
  def warn(args: js.Any*): Unit
  def error(args: js.Any*): Unit
  def info(args: js.Any*): Unit
  def log(args: js.Any*): Unit
  def debug(args: js.Any*): Unit
}
