### command line

#### run tests

```shell
sbt test
```

### Html

#### Development version

npm should be installed.

```shell 
export NODE_OPTIONS=--openssl-legacy-provider
sbt fastOptJS::webpack
# open html/index.html
```

#### Release

```shell 
sbt fullOptJS::webpack
cp target/scala-2.13/scalajs-bundler/main/ihm-githubio-gcms2isocor-opt-bundle.js docs/
# open docs/index.html
```
