package com.zpatricklee.content_calendar;

import com.zpatricklee.content_calendar.config.ContentCalendarProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cc")
public class ConfigPropertiesTrigger {
    private final ContentCalendarProperties props;

    public ConfigPropertiesTrigger(ContentCalendarProperties props) {
        this.props = props;
    }
}
