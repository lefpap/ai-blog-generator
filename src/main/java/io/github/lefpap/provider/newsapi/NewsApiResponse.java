package io.github.lefpap.provider.newsapi;

import java.util.List;
import java.util.Optional;

public record NewsApiResponse(
    String status,
    int totalResults,
    List<NewsApiArticle> articles
) {
    public NewsApiResponse {
        articles = Optional.ofNullable(articles).map(List::copyOf).orElse(List.of());
    }
}
