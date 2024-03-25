package com.github.schaka.native_example.sonarr

import com.github.schaka.native_example.ServarrService
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable

open class SonarrRestService(

    val sonarrProperties: SonarrProperties,
    var counter: Int = 0

) : ServarrService {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)

        const val CACHE_NAME = "sonarr-cache"
    }

    @PostConstruct
    fun doSomething() {
        log.info("PostConstruct called from {}", this)
    }

    @Cacheable(CACHE_NAME)
    override fun getEntries(): List<Int> {
        log.info("SonarrService called {}", this)
        counter++
        return listOf(counter)
    }

    override fun removeEntries(items: List<Int>) {

    }

}