package com.github.schaka.native_example.sonarr

import com.github.schaka.native_example.ServarrService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SonarrNoOpService : ServarrService {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)
    }

    override fun getEntries(): List<Int> {
        log.info("Sonarr is disabled, not getting any movies")
        return listOf()
    }

    override fun removeEntries(items: List<Int>) {
        log.info("Sonarr is disabled, not deleting any movies")
    }

}