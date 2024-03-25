package com.github.schaka.native_example.radarr

import com.github.schaka.native_example.ServarrService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Only required for native image
 */
@Configuration(proxyBeanMethods = false)
class RadarrConfig(
    val radarrNoOpService: RadarrNoOpService
) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    @Bean
    @Radarr
    fun radarrService(
        radarrProperties: RadarrProperties,
    ): ServarrService {

        if (radarrProperties.enabled) {
            val service = RadarrRestService(radarrProperties)
            log.info("Manually created RadarrService {}", service)
            return service
        }

        return radarrNoOpService
    }
}