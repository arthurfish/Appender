package io.github.arthurfish.appender.edgeservice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class EdgeController(
  @Autowired private val streamBridge: StreamBridge
) {

  @PostMapping("/publish")
  fun publishMessage(@RequestBody payload: Map<String, Any>): ResponseEntity<String> {
    // 从请求体中提取 topic
    val topic = payload["topic"]?.toString()
      ?: return ResponseEntity("Missing 'topic' field in request body", HttpStatus.BAD_REQUEST)

    // 将消息发送到 RabbitMQ
    val headers = mapOf("topic" to topic) // 将 topic 放入消息头
    streamBridge.send("output", payload, headers)

    // 立即返回 OK 响应
    return ResponseEntity.ok("Message published successfully")
  }
}