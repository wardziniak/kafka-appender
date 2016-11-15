package com.wardziniak.logback.appender

import java.util.Properties

import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.{Layout, UnsynchronizedAppenderBase}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

/**
  * Created by wardziniak on 13.11.2016.
  */
class KafkaAppender extends UnsynchronizedAppenderBase[ILoggingEvent] {

  var props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("acks", "all")
  props.put("retries", "0")
  props.put("batch.size", "16384")
  props.put("linger.ms", "1")
  props.put("buffer.memory", "33554432")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  var producer: KafkaProducer[String, String] = new KafkaProducer[String, String](props)

  var layout: PatternLayout = new PatternLayout

  override def append(eventObject: ILoggingEvent): Unit = {
    layout.setPattern("%msg")
    layout.start()
    val message = layout.doLayout(eventObject) + "test" + layout.isStarted + ":"
    val record: ProducerRecord[String, String] = new ProducerRecord[String, String]("test1", message)
    producer.send(record).get()
  }


}
