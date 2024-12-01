package io.github.arthurfish.appender.edgeservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EdgeServiceApplication

fun main(args: Array<String>) {
  runApplication<EdgeServiceApplication>(*args)
}
