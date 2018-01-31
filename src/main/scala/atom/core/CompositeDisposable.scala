package laughedelic.atom

import scala.scalajs.js

/**
 *  An object that aggregates multiple Disposable instances together into a
 *  single disposable, so they can all be disposed as a group.
 */
@js.native
trait CompositeDisposable extends DisposableLike {
  /**
   *  Dispose all disposables added to this composite disposable.
   *  If this object has already been disposed, this method has no effect.
   */
  def dispose(): Unit = js.native

  // Managing Disposables
  /**
   *  Add disposables to be disposed when the composite is disposed.
   *  If this object has already been disposed, this method has no effect.
   */
  def add(disposables: Disposable*): Unit = js.native

  /* Remove a previously added disposable. */
  def remove(disposable: Disposable): Unit = js.native

  /* Alias to CompositeDisposable::remove. */
  def delete(disposable: Disposable): Unit = js.native

  /*
   *  Clear all disposables. They will not be disposed by the next call to
   *  dispose.
   */
  def clear(): Unit = js.native
}
