package laughedelic.atom.packagedeps

import scala.scalajs.js, js.annotation._

@js.native @JSImport("atom-package-deps", JSImport.Namespace)
object packageDeps extends js.Object {

  def install(
    packageName: String = js.native,
    showPrompt: Boolean = js.native
  ): js.Promise[Unit] = js.native

}
