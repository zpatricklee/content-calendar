package com.zpatricklee.content_calendar.repository

import com.zpatricklee.content_calendar.model.Content
import com.zpatricklee.content_calendar.model.Status
import com.zpatricklee.content_calendar.model.Type
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class ContentCollectionRepository() {
    private val contentList = mutableListOf<Content>()

    fun findAll(): List<Content> {
        return contentList
    }

    fun findById(id: Int): Content? {
        return contentList.firstOrNull { it.id == id }
    }

    fun save(content: Content) {
        contentList.removeIf { it.id == content.id }
        contentList.add(content)
    }

    fun existsById(id: Int): Boolean {
        return contentList.any { it.id == id }
    }

    fun delete(id: Int) {
        contentList.removeIf { it.id == id }
    }

    //    @PostConstruct
    init {
        val c = Content(
            id = 1,
            title = "My First Blog Post",
            description = "My first blog post",
            status = Status.IDEA,
            contentType = Type.ARTICLE,
            dateCreated = LocalDateTime.now(),
            dateUpdated = null,
            url = "",
        )

        contentList.add(c)
    }
}