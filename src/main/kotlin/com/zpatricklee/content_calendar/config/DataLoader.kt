package com.zpatricklee.content_calendar.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.zpatricklee.content_calendar.model.Content
import com.zpatricklee.content_calendar.repository.ContentRepository
import org.springframework.boot.CommandLineRunner

import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val repository: ContentRepository,
    private val objectMapper: ObjectMapper
) : CommandLineRunner {


    override fun run(vararg args: String?) {
        val inputStream = javaClass.getResourceAsStream("/data/content.json")
            ?: throw IllegalStateException("Could not find /data/content.json on classpath")
        val items: List<Content> = objectMapper.readValue(inputStream)
        repository.saveAll(items)
    }
}