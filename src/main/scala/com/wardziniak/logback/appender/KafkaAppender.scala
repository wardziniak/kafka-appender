package com.wardziniak.logback.appender

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.{LoggerContext, PatternLayout}
import ch.qos.logback.core.UnsynchronizedAppenderBase
import com.wardziniak.logback.appender.kafka.ProducerConfigurator
import com.wardziniak.logback.appender.kafka.partitioner.{AppenderPartitioner, DefaultAppenderPartitioner}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

/**
  * Created by wardziniak on 13.11.2016.
  */
class KafkaAppender() extends UnsynchronizedAppenderBase[ILoggingEvent] with ProducerConfigurator {

  var partitioner: AppenderPartitioner = new DefaultAppenderPartitioner

  var topic: String = "test"

  def setTopic(m: String): Unit = topic = m

  def setPartitioner(partitioner: AppenderPartitioner): Unit = this.partitioner = partitioner

  lazy val producer: KafkaProducer[String, String]  = new KafkaProducer[String, String](getProducerConfiguration)

  var layout: PatternLayout = new PatternLayout
  layout.setPattern("%message")
  layout.setContext(new LoggerContext)
  layout.start()

  override def append(eventObject: ILoggingEvent): Unit = {
    val message = layout.doLayout(eventObject)
    val record: ProducerRecord[String, String] =
      new ProducerRecord[String, String](topic, partitioner.generateKey(eventObject), message)
    producer.send(record).get()
  }

  override def stop(): Unit = {
    producer.close()
    super.stop()
  }
}