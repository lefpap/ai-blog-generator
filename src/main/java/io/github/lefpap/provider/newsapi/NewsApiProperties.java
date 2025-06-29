package io.github.lefpap.provider.newsapi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Period;
import java.util.Optional;

@Validated
@ConfigurationProperties("provider.newsapi")
public record NewsApiProperties(
    @NotBlank
    String baseUrl,

    @NotBlank
    String apiKey,

    @NotNull
    Options options
) {

    @Validated
    @ConfigurationProperties("provider.newsapi.options")
    public record Options(
        @NotBlank
        String topic,

        @NotNull
        Period range,

        @Positive
        Integer limit,

        @NotBlank
        String sortBy
    ) {

        public Options {
            range = Optional.ofNullable(range).orElse(Period.ofDays(7));
            limit = Optional.ofNullable(limit).orElse(100);
            sortBy = Optional.ofNullable(sortBy).orElse(SortBy.POPULARITY);
        }
    }

    public static final class SortBy {

        public static final String POPULARITY = "popularity";
        public static final String RELEVANCY = "relevancy";
        public static final String PUBLISHED_AT = "publishedAt";

        private SortBy() {
            // Prevent instantiation
        }
    }
}
