package com.zpatricklee.content_calendar.service

import com.zpatricklee.content_calendar.model.Content
import com.zpatricklee.content_calendar.model.Status
import com.zpatricklee.content_calendar.repository.ContentRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ContentService(
    private val contentRepository: ContentRepository
) {

    private val logger = LoggerFactory.getLogger(ContentService::class.java)

    fun filter(titleContains: String?, status: Status?, type: String?): List<Content> {

        logger.info("Filtering content with titleContains='{}', status='{}', type='{}'",
            titleContains, status, type
        )

        return when {
            !titleContains.isNullOrEmpty() && status != null && !type.isNullOrEmpty() ->
                contentRepository.findAllByTitleContainsAndStatusAndContentType(titleContains, status, type)
                    .also { logger.debug("Found {} results (titleContains+status+type)", it.size) }

            !titleContains.isNullOrEmpty() && status != null ->
                contentRepository.findAllByTitleContainsAndStatus(titleContains, status)
                    .also { logger.debug("Found {} results (titleContains+status)", it.size) }

            !titleContains.isNullOrEmpty() && !type.isNullOrEmpty() ->
                contentRepository.findAllByTitleContainsAndContentType(titleContains, type)
                    .also { logger.debug("Found {} results (titleContains+type)", it.size) }

            status != null && !type.isNullOrEmpty() ->
                contentRepository.findAllByStatusAndContentType(status, type)
                    .also { logger.debug("Found {} results (status+type)", it.size) }

            !titleContains.isNullOrEmpty() ->
                contentRepository.findAllByTitleContains(titleContains)
                    .also { logger.debug("Found {} results (titleContains only)", it.size) }

            status != null ->
                contentRepository.findAllByStatus(status)
                    .also { logger.debug("Found {} results (status only)", it.size) }

            !type.isNullOrEmpty() ->
                contentRepository.findAllByContentType(type)
                    .also { logger.debug("Found {} results (type only)", it.size) }

            else -> {
                logger.warn("Filter called with no parameters — returning empty list")
                emptyList()
            }
        }
    }
}
