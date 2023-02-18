# Readme
Pistol Wallet is a sample desktop application built with [ldk-node-jvm](https://github.com/lightningdevkit/ldk-node) and [Compose for Desktop](https://www.jetbrains.com/lp/compose/).

<div align="center">
    <br/>
    <img src="screenshot.png" width="440px" />
</div>

## Run the wallet locally
To run the wallet locally, you'll need:
1. A running instance of a regtest bitcoin daemon
2. A running instance of an Esplora server connected to your bitcoin daemon
3. JDK 17 or later (check out [sdkman](https://sdkman.io/) for easy installation of multiple JDKs)
4. A published version of ldk-node-jvm to your local Maven repository

```shell
./gradlew run
# if you get an error, try instead:
# arch -arch arm64 ./gradlew run
```

## Build the wallet
You can build the dmg image for macOS using
```shell
./gradlew packageDmg
# the application will be in build/compose/binaries/main/dmg/
```
