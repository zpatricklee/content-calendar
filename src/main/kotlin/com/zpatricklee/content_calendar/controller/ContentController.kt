package com.zpatricklee.content_calendar.controller

import com.zpatricklee.content_calendar.model.Content
import com.zpatricklee.content_calendar.model.Status
import com.zpatricklee.content_calendar.repository.ContentRepository
import com.zpatricklee.content_calendar.service.ContentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.Optional

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = ["*"])
class ContentController(
    val contentRepository: ContentRepository,
    val contentService: ContentService
) {

    // make a request and find all the pieces of content in the system
    @GetMapping("")
    fun findAll(): List<Content> = contentRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Content = contentRepository.findById(id)
            .orElseThrow{
                ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found") 
            }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    fun create(@Valid @RequestBody content: Content) {
        contentRepository.save(content)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Int,
        @RequestBody content: Content
    ) {
        if (!contentRepository.existsById(id)) throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Content not found"
        ) else {
            val updatedContent = content.copy(
                dateUpdated = LocalDateTime.now()
            )
            contentRepository.save(updatedContent)
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Int
    ) {
        if (!contentRepository.existsById(id)) throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Content not found"
        ) else contentRepository.deleteById(id)
    }

    @GetMapping("/filter")
    fun findByFilter(
        @RequestParam(required = false) titleContains: String?,
        @RequestParam(required = false) status: Status?,
        @RequestParam(required = false) type: String?
    ): List<Content> = contentService.filter(titleContains, status, type)


}