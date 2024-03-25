package com.github.schaka.native_example.sonarr

import com.github.schaka.native_example.ServarrService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.cache.annotation.Cacheable

open class SonarrRestService(

    val sonarrProperties: SonarrProperties,
    var counter: Int = 0

) : ServarrService, InitializingBean {

    companion object {
      private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)

        const val CACHE_NAME = "sonarr-cache"
    }

    override fun afterPropertiesSet() {
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