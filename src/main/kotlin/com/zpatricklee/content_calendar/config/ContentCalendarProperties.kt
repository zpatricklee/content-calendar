package com.zpatricklee.content_calendar.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("cc")
open class ContentCalendarProperties(
    val welcomeMessage: String,
    val about: String
)