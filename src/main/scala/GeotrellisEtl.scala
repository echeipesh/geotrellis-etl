import geotrellis.raster._
import geotrellis.spark._
import geotrellis.spark.utils.SparkUtils
import geotrellis.spark.io.index.ZCurveKeyIndexMethod
import geotrellis.spark.SpatialKey
import geotrellis.spark.etl._
import org.apache.spark.SparkConf


object GeotrellisEtl extends App {
  val etl = Etl[SpatialKey](args)

  implicit val sc = SparkUtils.createSparkContext("GeoTrellis ETL", new SparkConf(true))
  val (id, rdd) = etl.load()
  // pass through ingested tiles to the catalog
  etl.save(id, rdd, ZCurveKeyIndexMethod)
  sc.stop()
}
