package com.wardziniak.logback.appender

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

  "Kafka producer" should " send message" in {
    val loggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    val kafkaAppender = new KafkaAppender
    kafkaAppender.setContext(loggerContext)
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
