package com.github.schaka.native_example

import com.github.schaka.native_example.radarr.Radarr
import com.github.schaka.native_example.sonarr.Sonarr
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SomeService(
    @Radarr
    val radarrService: ServarrService,
    @Sonarr
    val sonarrService: ServarrService
) {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    @Scheduled(fixedDelay =  10000)
    fun doSomething() {
        log.info("From {} Counter: {}", radarrService, radarrService.getEntries())
        log.info("From {} Counter: {}", radarrService, radarrService.getEntries())

        log.info("From {} Counter: {}", sonarrService, sonarrService.getEntries())
        log.info("From {} Counter: {}", sonarrService, sonarrService.getEntries())
    }
}