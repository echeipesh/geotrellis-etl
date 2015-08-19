import geotrellis.raster._
import geotrellis.spark._
import geotrellis.spark.utils.SparkUtils
import geotrellis.spark.io.index.ZCurveKeyIndexMethod
import geotrellis.spark.SpatialKey
import geotrellis.spark.etl._
import org.apache.spark.SparkConf


object NlcdCanopy extends App {
  val etl = Etl[SpatialKey](args)

  implicit val sc = SparkUtils.createSparkContext("GeoTrellis NLCD Canopy Mask", new SparkConf(true).setMaster("local[*]"))
  val (id, rdd) = etl.load()

  // map all the tiles using our rule
  rdd.mapTiles(_.convert(TypeBit).map( x => if (Array(41, 42, 43) contains x) 1 else 0))

  // tile type has changed, but raster metadata is not up to date, need to update it
  val result = new RasterRDD(rdd.tileRdd, rdd.metaData.copy(cellType = TypeBit))

  etl.save(id, result, ZCurveKeyIndexMethod)
  sc.stop()
}
