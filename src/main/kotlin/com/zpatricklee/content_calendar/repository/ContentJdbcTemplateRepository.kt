package com.zpatricklee.content_calendar.repository

import com.zpatricklee.content_calendar.model.Content
import com.zpatricklee.content_calendar.model.Status
import com.zpatricklee.content_calendar.model.Type
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.LocalDateTime

//@Repository
class ContentJdbcTemplateRepository(
    val jdbcTemplate: JdbcTemplate
) {

    fun findAll(): List<Content> {
        val sql = "SELECT * FROM Content"
        val contents = jdbcTemplate.query(sql) { rs, rowNum -> mapRow(rs, rowNum)}
        return contents
    }

    private fun mapRow(rs: ResultSet, rowNum: Int): Content {
        return Content(
            id = rs.getInt("id"),
            title = rs.getString("title"),
            description = rs.getString("description") ?: "",
            status = Status.valueOf(rs.getString("status")),
            contentType = Type.valueOf(rs.getString("content_type")),
            dateCreated = rs.getObject("date_created", LocalDateTime::class.java),
            dateUpdated = rs.getObject("date_updated", LocalDateTime::class.java),
            url = rs.getString("url") ?: "",
        )
    }

    fun findById(id: Int): Content? {
        val sql = "SELECT * FROM Content WHERE id=?"
        return try {
            jdbcTemplate.queryForObject(sql, this::mapRow, id)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
    fun save(content: Content) {
        val sql = "INSERT INTO Content (title, description, status, content_type, date_created, URL) VALUES (?,?,?,?,NOW(),?)".trimIndent()
        jdbcTemplate.update(sql, content.title, content.description, content.status.name, content.contentType.name, content.url)
    }

    fun existsById(id: Int): Boolean {
        val sql = "SELECT 1 FROM Content WHERE id=?"

        return jdbcTemplate.query(sql, { rs, _ -> rs.getInt(1) }, id).isNotEmpty()

    }
    fun delete(id: Int) {
        val sql = "DELETE FROM Content WHERE id=?"
        jdbcTemplate.update(sql, id)
    }
}