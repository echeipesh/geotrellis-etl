# Sample GeoTrellis ETL Project

You will need to build and assembly jar using 'sbt assembly' and then use 'spark-submit' like so:

```
!/bin/sh
export JAR="target/scala-2.10/geotrellis-etl-assembly-0.1-SNAPSHOT.jar"

spark-submit \
--class GeotrellisEtl \
--master local[*] \
--driver-memory=8G \
$JAR \
--ingest hadoop --format geotiff --cache NONE -I path=file:///Users/eugene/tmp/nlcd/subset \
--output s3 -O bucket=com.azavea.datahub key=catalog \
--layer nlcd-tms-subset --pyramid --crs EPSG:3857
```
