package com.github.schaka.native_example.radarr

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "clients.radarr")
data class RadarrProperties(
        val enabled: Boolean,
        val url: String,
        val apiKey: String
)