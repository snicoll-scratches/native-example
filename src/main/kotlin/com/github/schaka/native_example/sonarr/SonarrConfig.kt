package com.github.schaka.native_example.sonarr

import com.github.schaka.native_example.ServarrService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Only required for native image
 */
@Configuration(proxyBeanMethods = false)
class SonarrConfig(
    val radarrNoOpService: SonarrNoOpService
) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    @Bean
    @Sonarr
    fun sonarrService(
        sonarrProperties: SonarrProperties,
    ): ServarrService {

        if (sonarrProperties.enabled) {
            val service = SonarrRestService(sonarrProperties)
            log.info("Manually created SonarrService {}", service)
            return service
        }

        return radarrNoOpService
    }
}