package com.microsoft.excel_online.causelizer.feature_engineering

import org.apache.spark.sql.{SparkSession, Dataset, Encoders}
import com.microsoft.excel_online.causelizer.types.ModelEntry
import org.apache.log4j.LogManager
import scopt.OParser


case class ReadDataConfig( inputDir: String = null);

object FeatureSetReader {
  private val log = LogManager.getLogger("FeatureSetReader")

  private def readFeatures(spark: SparkSession, config: ReadDataConfig): Unit = {
    import spark.implicits._

    val featuresSchema = Encoders.product[ModelEntry].schema
    val dfFeatures = spark.read
      .schema(featuresSchema)
      .json(config.inputDir)
      .as[ModelEntry]
    log.info(s"Successfully read ${dfFeatures.count} entries")
  }

  private def parseArgs(args: Array[String]): Option[ReadDataConfig] = {
    val builder = OParser.builder[ReadDataConfig]
    val cmdlineParser = {
      import builder._

      OParser.sequence(
        programName("FeatureSetReader"),
        head("Feature set reader"),
        opt[String]('i', "inputdir")
          .action((inputDir, config) => config.copy(inputDir=inputDir))
          .text("Input directory of the feature data set")
          .required())
    }
    OParser.parse(cmdlineParser, args, ReadDataConfig())
  }

  def main(args: Array[String]): Unit = {
    val parsedArgs = parseArgs(args)
    if (parsedArgs.isEmpty) {
      log.error("Commandline parsing error")
      return
    }

    val spark = SparkSession.builder()
      .appName("Feature Engineering")
      .getOrCreate()
    readFeatures(spark, parsedArgs.get)
  }

}


