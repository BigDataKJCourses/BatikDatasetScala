package com.example.bigdata.tools

import org.bytedeco.opencv.global.opencv_core._
import org.bytedeco.opencv.global.opencv_imgcodecs._
import org.bytedeco.opencv.global.opencv_imgproc.{Canny, Laplacian}
import org.bytedeco.opencv.opencv_core.Mat

import java.nio.file.{Files, Paths}

object ImageProcessingUtils {

  def imageMeasurements(content: Array[Byte]): (Int, Int, Double, Double, Double, Double) = {
    try {
      // Konwersja Array[Byte] do Mat
      val matOfByte = new Mat(content: _*)
      val mat = imdecode(matOfByte, IMREAD_GRAYSCALE)

      // Sprawdzenie, czy obraz został poprawnie załadowany
      if (mat.empty()) {
        System.err.println("Warning: Failed to decode image")
        return (0, 0, Double.NaN, Double.NaN, Double.NaN, Double.NaN)
      }

      // Pobierz wymiary obrazu
      val height = mat.rows()
      val width = mat.cols()

      // Oblicz kontrast i średnią jasność
      val mean = new Mat()
      val stdDev = new Mat()
      meanStdDev(mat, mean, stdDev)
      val contrast = stdDev.ptr().getDouble(0)
      val meanIntensity = mean.ptr().getDouble(0)
      val blur = computeBlur(mat)
      val edgeRatio = computeEdgeRatio(mat)

      (height, width, contrast, meanIntensity, blur, edgeRatio)
    }
  }

  // Stosunek liczby pikseli krawędzi do całkowitej liczby pikseli w obrazie.
  def computeEdgeRatio(mat: Mat): Double = {
    val edges = new Mat()
    Canny(mat, edges, 100, 200)
    val edgePixels = countNonZero(edges)
    val totalPixels = mat.rows() * mat.cols()
    edgePixels.toDouble / totalPixels
  }

  def computeBlur(mat: Mat): Double = {
    val laplacian = new Mat()
    Laplacian(mat, laplacian, CV_64F)
    val stddev = new Mat()
    meanStdDev(laplacian, new Mat(), stddev)
    stddev.ptr().getDouble(0)
  }

  def main(args: Array[String]): Unit = {
    val filePath = "C:\\Users\\kjankiewicz\\Downloads\\archive_batik\\batik-tambal\\1.jpg" // Podaj pełną ścieżkę do pliku z obrazem

    try {
      // Odczyt pliku jako Array[Byte]
      val imageData = Files.readAllBytes(Paths.get(filePath))

      // Wywołanie funkcji imageMeasurements
      val (height, width, contrast, meanIntensity, entropy, edgeRatio) = ImageProcessingUtils.imageMeasurements(imageData)

      // Wyświetlenie wyników
      println(s"Height: $height")
      println(s"Width: $width")
      println(s"Contrast: $contrast")
      println(s"Mean Intensity: $meanIntensity")
      println(s"Entropy: $entropy")
      println(s"Edge Ratio: $edgeRatio")

    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}
