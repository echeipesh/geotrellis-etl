#!/bin/sh
export JAR="geotrellis-etl-assembly-0.1-SNAPSHOT.jar"

export MESOS_NATIVE_LIBRARY=/usr/local/lib/libmesos.so

spark-submit \
--class NlcdCanopy \
--master mesos://zk://zookeeper.service.geotrellis-spark.internal:2181/mesos \
--conf spark.mesos.coarse=false \
--conf spark.executor.memory=2g \
--conf spark.executorEnv.SPARK_LOCAL_DIRS="/media/ephemeral0,/media/ephemeral1" \
--driver-library-path /usr/local/lib \
$JAR \
--ingest s3 --format geotiff -I bucket=com.azavea.datahub key=raw \
--output s3 -O bucket=com.azavea.datahub key=catalog \
--layer nlcd-tms-canopy --pyramid --crs EPSG:3857