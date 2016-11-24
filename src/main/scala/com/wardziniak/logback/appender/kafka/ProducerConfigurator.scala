package com.wardziniak.logback.appender.kafka

import java.util

/**
  * Created by wardziniak on 20.11.2016.
  */
trait ProducerConfigurator {

  private val producerConfig = new util.HashMap[String, Object]()

  def getProducerConfiguration = producerConfig

  def addProperty(key: String, value: Object) = producerConfig.put(key, value)

  def setProperty(key: String, value: Object) = addProperty(key, value)

  def addProducerConfig(property: String) = {
    val divided = property.split("=")
    addProperty(divided(0), divided(1))
  }
}
