name := "kafka-appender"

version := "1.0"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-core" % "1.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.apache.kafka" % "kafka_2.11" % "0.10.1.0"
)
