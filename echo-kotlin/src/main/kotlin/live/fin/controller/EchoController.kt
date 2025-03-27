package live.fin.controller

import live.fin.service.MetricsService
import live.fin.service.MetricsService.Companion.METRIC_NAME
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class EchoController(
    private val metricsService: MetricsService
) {

    companion object {
        private val log = LoggerFactory.getLogger(EchoController::class.java)
    }

    @GetMapping
    fun echoHeaders(@RequestHeader headers: Map<String, String>): ResponseEntity<Map<String, String>> {
        log.info("process echoHeaders request")
        return ResponseEntity.ok(headers)
    }

    @GetMapping("/test")
    fun echoHeadersTest(@RequestHeader headers: Map<String, String>): ResponseEntity<Map<String, String>> {
        log.info("process echoHeaders request")
        return ResponseEntity.ok(headers)
    }


    @GetMapping("/exception")
    fun exception(): ResponseEntity<String> {
        log.info("process exception request")
        log.warn("internalServerError will be returned")
        metricsService.count(METRIC_NAME)
        log.error("exception occurs")
        return ResponseEntity.internalServerError().contentType(MediaType.TEXT_PLAIN).body("Internal server error")
    }
}
