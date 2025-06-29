package io.github.lefpap.provider.newsapi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Service
public class NewsApiClient {

    private static final String X_API_KEY = "X-Api-Key";

    private final RestClient client;
    private final NewsApiProperties.Options options;

    public NewsApiClient(RestClient.Builder restClientBuilder, NewsApiProperties properties) {
        this.options = properties.options();
        this.client = restClientBuilder
            .baseUrl(properties.baseUrl())
            .defaultHeader(X_API_KEY, properties.apiKey())
            .build();
    }

    public NewsApiResponse getEverything() {
        LocalDate today = LocalDate.now();
        return client.get()
            .uri(uriBuilder -> uriBuilder
                .path("/everything")
                .queryParam("q", options.topic())
                .queryParam("from", today.minus(options.range()))
                .queryParam("to", today)
                .queryParam("pageSize", options.limit())
                .queryParam("sortBy", options.sortBy())
                .build()
            )
            .retrieve()
            .body(NewsApiResponse.class);
    }
}
