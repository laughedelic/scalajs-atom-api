package laughedelic.atom

import scala.scalajs.js


trait DisposableLike extends js.Object {
  def dispose(): Unit
}

/**
  * A handle to a resource that can be disposed. For example, Emitter::on
  * returns disposables representing subscriptions.
  */
@js.native
trait Disposable extends DisposableLike {
  /**
    * Perform the disposal action, indicating that the resource associated with
    * this disposable is no longer needed.
    * You can call this method more than once, but the disposal action will only
    * be performed the first time.
    */
  def dispose(): Unit = js.native
}
