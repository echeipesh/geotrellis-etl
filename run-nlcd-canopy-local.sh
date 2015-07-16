#!/bin/sh
export JAR="target/scala-2.10/geotrellis-etl-assembly-0.1-SNAPSHOT.jar"

export MESOS_NATIVE_LIBRARY=/usr/local/lib/libmesos.so

spark-submit \
--class NlcdCanopy \
--master local[4] \
--driver-memory 10G \
$JAR \
--ingest hadoop --format geotiff --cache NONE -I path=file:///Volumes/Pod/tiles/nlcd_wm_ext-tiled-proj4 \
--output s3 -O bucket=com.azavea.datahub key=catalog \
--layer nlcd-tms-canopy --pyramid --crs EPSG:3857
