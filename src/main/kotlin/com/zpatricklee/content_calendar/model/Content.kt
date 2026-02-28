package com.zpatricklee.content_calendar.model

import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime

data class Content(
    @Id
    val id: Int?,
    @field:NotBlank val title: String,
    val description: String,
    val status: Status,
    val contentType: Type,
    val dateCreated: LocalDateTime,
    val dateUpdated: LocalDateTime?,
    val url: String?
) {


}
