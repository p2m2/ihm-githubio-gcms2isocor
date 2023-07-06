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