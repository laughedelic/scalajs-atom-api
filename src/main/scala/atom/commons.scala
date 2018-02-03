package laughedelic.atom

import scala.scalajs.js

private[atom]
trait DisposableLike extends js.Object {
  def dispose(): Unit
}
