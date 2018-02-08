package laughedelic.atom

import scala.scalajs.js, js.annotation._, js.|

/**
 * A simple color class returned from `Config::get` when the value at the key
 * path is of type 'color'.
 */
@js.native
trait Color extends js.Object {

  /** Returns a String in the form '#abcdef' */
  def toHexString(): String = js.native

  /** Returns a String in the form 'rgba(25, 50, 75, .9)' */
  def toRGBAString(): String = js.native
}

@js.native @JSGlobal
object Color extends js.Object {
  /**
   * Parse a `String` or `Object` into a `Color`.
   * @param value A `String` such as 'white', `#ff00ff`, or
   *   'rgba(255, 15, 60, .75)' or an `Object` with `red`, `green`, `blue`,
   *   and `alpha` properties.
   * @return a `Color` or `null` if it cannot be parsed.
   */
  def parse(value: String | js.Object): Color = js.native
}
