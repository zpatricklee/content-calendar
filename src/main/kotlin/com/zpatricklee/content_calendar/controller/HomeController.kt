package com.zpatricklee.content_calendar.controller

import com.zpatricklee.content_calendar.config.ContentCalendarProperties
import com.zpatricklee.content_calendar.model.Content
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
    val properties: ContentCalendarProperties
) {

    @GetMapping("/")
    fun home(): ContentCalendarProperties {
        return properties
    }
}