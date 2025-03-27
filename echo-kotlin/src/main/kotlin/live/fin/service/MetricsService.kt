package live.fin.service

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
@EnableScheduling
class MetricsService(
    private val meterRegistry: MeterRegistry
) {

    fun count(name: String) {
        Counter.builder("${ECHO_SERVICE_NAME}.$name")
            .register(meterRegistry)
            .increment()
    }

    companion object {
        private const val ECHO_SERVICE_NAME = "echo-kotlin"
        const val METRIC_NAME = "exception_count"
    }
}