package com.example.bigdata

object ImageAnalysis {
  def main(args: Array[String]): Unit = {
    if (args.length == 0) {
      println("Usage: ImageAnalysis <dataPath> <variant>")
      System.exit(1)
    }
    // Pobranie ścieżki z argumentu
    val dataPath = args(0)
    //TODO: Utwórz poprawny kontekst dla Spark SQL


    if (args.length == 1 || args(1) != "dataset") {
      // Tu wstaw implementację dla DataFrame API


    } else {
      // Tu wstaw implementację dla Dataset API


    }
  }

}
