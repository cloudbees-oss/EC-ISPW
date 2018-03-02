#!/usr/bin/env bash


PLUGIN_NAME=EC-ISPW
PLUGIN_VERSION=2.0.0.8
DIR=$(dirname $0)
chmod +x $DIR/../bin/darwin_x86_64/ecpluginbuilder
$DIR/../bin/darwin_x86_64/ecpluginbuilder --plugin-version $PLUGIN_VERSION

ectool installPlugin $DIR/../build/$PLUGIN_NAME.zip
ectool promotePlugin $PLUGIN_NAME-$PLUGIN_VERSION
ectool setProperty /projects/$PLUGIN_NAME-$PLUGIN_VERSION/debugLevel 10


