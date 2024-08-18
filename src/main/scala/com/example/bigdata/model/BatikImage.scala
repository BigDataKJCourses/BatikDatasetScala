package com.example.bigdata.model

case class BatikImage(
                      path: String,
                      modificationTime: java.sql.Timestamp,
                      length: Long,
                      content: Array[Byte]
                    )
