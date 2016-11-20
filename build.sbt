name := "kafka-appender"

version := "1.0"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "org.apache.kafka" % "kafka_2.11" % "0.10.1.0",
  "org.scalatest" % "scalatest_2.11" % "3.0.0" % "test",
  "org.apache.kafka" % "kafka_2.11" % "0.10.1.0" % "test"
)
