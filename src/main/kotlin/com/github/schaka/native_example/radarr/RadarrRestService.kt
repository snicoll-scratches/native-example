package com.github.schaka.native_example.radarr

import com.github.schaka.native_example.ServarrService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class RadarrRestService(

    val radarrProperties: RadarrProperties,
    var counter: Int = 0

) : ServarrService, InitializingBean {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java.enclosingClass)

        const val CACHE_NAME = "radarr-cache"
    }

    override fun afterPropertiesSet() {
        log.info("PostConstruct called from {}", this)
    }

    @Cacheable(CACHE_NAME)
    override fun getEntries(): List<Int> {
        log.info("RadarrService called {}", this)
        counter++
        return listOf(counter)
    }

    override fun removeEntries(items: List<Int>) {

    }

}