# Scala.js facades for _some_ Atom-related APIs

[![](https://travis-ci.org/laughedelic/scalajs-atom-api.svg?branch=master)](https://travis-ci.org/laughedelic/scalajs-atom-api)
[![](http://img.shields.io/github/release/laughedelic/scalajs-atom-api/all.svg)](https://github.com/laughedelic/scalajs-atom-api/releases/latest)
[![](https://img.shields.io/badge/license-MPL--2.0-blue.svg)](https://www.tldrlegal.com/l/mpl-2.0)
[![](https://img.shields.io/badge/contact-gitter_chat-dd1054.svg)](https://gitter.im/laughedelic/scalajs-atom-api)

This project contains a few manually written facades for some Atom-related APIs. Currently it exists solely for the needs of the [atom-ide-scala](https://github.com/laughedelic/atom-ide-scala) project and will be extended on demand.

## Facades

* [Core Atom API](https://atom.io/docs/api)
    Check [`src/.../atom/core/`](src/main/scala/atom/core/) to find what's covered.

* [atom-languageclient](https://github.com/atom/atom-languageclient)
    Complete `AutoLanguageClient` and some [other relevant parts](src/main/scala/atom/languageclient/)

* [atom-ide-ui](https://github.com/facebook-atom/atom-ide-ui)
    + [atom-ide-busy-signal](https://github.com/facebook-atom/atom-ide-ui/tree/master/modules/atom-ide-ui/pkg/atom-ide-busy-signal)

* [atom-package-deps](https://github.com/steelbrain/package-deps)

## Usage

```scala
resolvers += Resolver.jcenterRepo
libraryDependencies += "laughedelic" %%% "scalajs-atom-api" % "<version>"
```

(see the latest release version on the badge above)
