import sbt._
import sbt.Keys._

object GeotrellisEtlBuild extends Build {
  lazy val geotrellisEtl = Project(
    id = "geotrellis-etl",
    base = file("."),
    settings = Project.defaultSettings ++ net.virtualvoid.sbt.graph.Plugin.graphSettings ++ Seq(
      name := "geotrellis-etl",
      organization := "geo",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.4",
      libraryDependencies ++= Seq(
        "com.azavea.geotrellis" %% "geotrellis-spark" % "0.10.0-SNAPSHOT",
        "com.azavea.geotrellis" %% "geotrellis-spark-etl" % "0.10.0-SNAPSHOT",
        "org.apache.spark" %% "spark-core" % "1.3.1" % "provided"
      )
    )
  )
}
