package com.wardziniak.logback.appender

import java.util
import java.util.Properties

import ch.qos.logback.classic.LoggerContext
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.scalatest._
import org.slf4j.LoggerFactory

import collection.mutable.Stack


/**
  * Created by wardziniak on 19.11.2016.
  */
class KafkaAppenderTest extends FlatSpec {

  private def setProducerConfiguration(kafkaAppender: KafkaAppender) = {
    kafkaAppender.addProducerConfig("bootstrap.servers=localhost:9092")
    kafkaAppender.addProducerConfig("acks=all")
    kafkaAppender.addProducerConfig("retries=0")
    kafkaAppender.addProducerConfig("batch.size=16384")
    kafkaAppender.addProducerConfig("linger.ms=1")
    kafkaAppender.addProducerConfig("buffer.memory=33554432")
    kafkaAppender.addProducerConfig("key.serializer=org.apache.kafka.common.serialization.StringSerializer")
    kafkaAppender.addProducerConfig("value.serializer=org.apache.kafka.common.serialization.StringSerializer")
  }

  "Kafka producer" should " send message" in {
    val loggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    val kafkaAppender = new KafkaAppender
    kafkaAppender.setTopic("test4")
    kafkaAppender.setContext(loggerContext)
    setProducerConfiguration(kafkaAppender)
    kafkaAppender.start()
    val logger = loggerContext.getLogger("Main")
    logger.addAppender(kafkaAppender)

    logger.error("Error message test")
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new Stack[String]
    assertThrows[NoSuchElementException] {
      emptyStack.pop()
    }
  }
}
