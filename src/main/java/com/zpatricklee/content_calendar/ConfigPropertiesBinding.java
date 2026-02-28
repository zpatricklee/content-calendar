package com.zpatricklee.content_calendar;

import com.zpatricklee.content_calendar.config.ContentCalendarProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ContentCalendarProperties.class)
public class ConfigPropertiesBinding {
}