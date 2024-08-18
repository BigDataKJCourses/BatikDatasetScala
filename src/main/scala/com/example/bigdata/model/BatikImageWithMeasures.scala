package com.example.bigdata.model

case class BatikImageWithMeasures(
                                  path: String,
                                  modificationTime: java.sql.Timestamp,
                                  length: Long,
                                  height: Int,
                                  width: Int,
                                  contrast: Double,
                                  mean_intensity: Double,
                                  blur: Double,
                                  edge_ratio: Double,
                                  batik_type: String
                                )
