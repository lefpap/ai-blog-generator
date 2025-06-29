package io.github.lefpap.provider.newsapi;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;

public record NewsApiArticle(
    String title,
    String author,
    String description,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    ZonedDateTime publishedAt
) {
}
