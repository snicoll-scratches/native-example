package com.github.schaka.native_example

import com.github.schaka.native_example.radarr.RadarrRestService
import com.github.schaka.native_example.sonarr.SonarrRestService
import org.springframework.aot.hint.ExecutableMode
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
import org.springframework.util.ReflectionUtils

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
            hints.reflection().registerMethod(
                ReflectionUtils.findMethod(RadarrRestService::class.java, "getEntries")!!, ExecutableMode.INVOKE)
            hints.reflection().registerMethod(
                ReflectionUtils.findMethod(SonarrRestService::class.java, "getEntries")!!, ExecutableMode.INVOKE)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<NativeExampleApplication>(*args)
}

