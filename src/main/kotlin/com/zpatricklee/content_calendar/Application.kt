package com.zpatricklee.content_calendar

import com.zpatricklee.content_calendar.config.ContentCalendarProperties
import com.zpatricklee.content_calendar.model.Content
import com.zpatricklee.content_calendar.model.Status
import com.zpatricklee.content_calendar.model.Type
import com.zpatricklee.content_calendar.repository.ContentRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import java.time.LocalDateTime

@ConfigurationPropertiesScan
@SpringBootApplication
class Application
	fun main(args: Array<String>) {
        runApplication<Application>(*args)
	}