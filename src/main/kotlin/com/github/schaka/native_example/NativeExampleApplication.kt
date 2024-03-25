package com.github.schaka.native_example

import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ImportRuntimeHints
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableConfigurationProperties
@EnableAsync
@EnableCaching
@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication
@ImportRuntimeHints(NativeExampleApplication.Hints::class)
class NativeExampleApplication {

    class Hints : RuntimeHintsRegistrar {
        override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
            hints.proxies().registerJdkProxy(ServarrService::class.java)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<NativeExampleApplication>(*args)
}

