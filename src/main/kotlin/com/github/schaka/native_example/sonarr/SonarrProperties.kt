package com.github.schaka.native_example.sonarr

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "clients.sonarr")
data class SonarrProperties(
        val enabled: Boolean,
        val url: String,
        val apiKey: String
)