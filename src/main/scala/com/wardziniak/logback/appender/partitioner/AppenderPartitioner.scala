package com.wardziniak.logback.appender.partitioner

import ch.qos.logback.classic.spi.ILoggingEvent

/**
  * Created by wardziniak on 18.11.2016.
  */
trait AppenderPartitioner {
  def generateKey(event: ILoggingEvent): String
}

class DefaultAppenderPartitioner extends AppenderPartitioner {
  override def generateKey(event: ILoggingEvent): String = event.getMessage
}



