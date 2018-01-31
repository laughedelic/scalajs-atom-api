package laughedelic.atom

import scala.scalajs.js

@js.native
trait TextEditor extends js.Object {
  val id: Long = js.native

  val firstVisibleScreenRow: Long = js.native
  val rowsPerPage: Long = js.native

  // File Details
  def getTitle(): String = js.native
  def getLongTitle(): String = js.native


  def getPath(): js.UndefOr[String] = js.native
  def getURI(): js.UndefOr[String] = js.native
  def insertNewline(): Unit = js.native
  def isModified(): Boolean = js.native
  def isEmpty(): Boolean = js.native
  def getEncoding(): String = js.native
  def setEncoding(encoding: String): Unit = js.native
  def getTabLength(): Long = js.native

  // File Operations
  def save(): Unit = js.native
  def saveAs(filePath: String): Unit = js.native

  // Reading Text
  def getText(): String = js.native
  def getLineCount(): Long = js.native
}
