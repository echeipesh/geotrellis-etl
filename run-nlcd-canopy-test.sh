#!/bin/sh
export JAR="target/scala-2.10/geotrellis-etl-assembly-0.1-SNAPSHOT.jar"

export MESOS_NATIVE_LIBRARY=/usr/local/lib/libmesos.so

spark-submit \
--class NlcdCanopy \
--master local[*] \
--conf spark.executor.memory=8G \
$JAR \
--ingest hadoop --format geotiff --cache NONE -I path=file:///Users/eugene/tmp/nlcd/subset \
--output s3 -O bucket=com.azavea.datahub key=catalog \
--layer nlcd-tms-canopy-subset --pyramid --crs EPSG:3857
