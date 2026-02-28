package com.zpatricklee.content_calendar

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service
class Message {
    fun getMessage(): String {
        return "Hello World!"
    }
}