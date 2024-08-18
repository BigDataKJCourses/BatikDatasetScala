ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

val sparkVersion = "3.5.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.bytedeco" % "javacv-platform" % "1.5.10"
)

lazy val root = (project in file("."))
  .settings(
    name := "BatikDatasetScala"
  )

// Ustawienie niestandardowej nazwy dla pliku JAR
artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  s"${name.value}.jar"
}
