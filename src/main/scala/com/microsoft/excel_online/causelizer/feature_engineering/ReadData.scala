package com.microsoft.excel_online.causelizer.feature_engineering;

import org.apache.spark.sql.SparkSession;

object ReadData {
  def runSpark(spark: SparkSession): Unit = {
    System.out.println("Elior is here");
  }

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Feature Engineering")
      .getOrCreate();

    runSpark(spark);
  }
}


