package com.zpatricklee.content_calendar.repository

import com.zpatricklee.content_calendar.model.Content
import com.zpatricklee.content_calendar.model.Status
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.query.Param

interface ContentRepository : ListCrudRepository<Content, Int>{
    fun findAllByTitleContains(titleContains: String): List<Content>

    @Query(
        """
            SELECT * FROM content
            WHERE status = :status
        """
    )
    fun findAllByStatus(@Param("status") status: Status): List<Content>
    fun findAllByTitleContainsAndStatusAndContentType(titleContains: String, status: Status, contentType: String): List<Content>
    fun findAllByTitleContainsAndStatus(titleContains: String, status: Status): List<Content>
    fun findAllByTitleContainsAndContentType(titleContains: String, contentType: String): List<Content>
    fun findAllByStatusAndContentType(status: Status, contentType: String): List<Content>
    fun findAllByContentType(contentType: String): List<Content>
}