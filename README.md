# Corrective method dedicated to Isocor for calculating carbon isotopologue distribution from GCMS runs

[![web](https://img.shields.io/badge/Web-Online-blue.svg)](https://p2m2.github.io/ihm-githubio-gcms2isocor/)
[![doi](https://img.shields.io/badge/doi.org/10.3389/fpls.2022.885051-blue.svg)](https://doi.org/10.3389/fpls.2022.885051)

### command line

simple interface "Corrective method dedicated to Isocor for calculating carbon isotopologue distribution from GCMS runs"

https://p2m2.github.io/ihm-githubio-gcms2isocor

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
