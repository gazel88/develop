package net.srook.common.apiclient;

import static java.util.Objects.isNull;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.srook.common.dto.KSPage;
import net.srook.common.exception.SRNotFoundException;
import net.srook.common.utils.ApiUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ApiClientImpl implements ApiClient {
    private final WebClient webClient;

    ApiClientImpl(final WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public <T> ResponseEntity<List<T>> findList(final String apiUrl, final Class<T> object) {
        return this.webClient.get()
                .uri(apiUrl)
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .toEntityList(object)
                .blockOptional()
                .orElse(ResponseEntity.ok(List.of()));
    }

    @Override
    public <T> ResponseEntity<T> findOne(final String apiUrl, final Class<T> object) {
        return this.webClient.get()
                .uri(apiUrl)
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .toEntity(object)
                .blockOptional()
                .orElseThrow(() -> new SRNotFoundException("조회 결과가 없습니다."));
    }

    @Override
    public <T> Flux<T> findListToFlux(final String apiUrl, final Class<T> object) {
        return this.webClient.get()
                .uri(apiUrl)
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .bodyToFlux(object);
    }

    @Override
    public <T> Mono<List<T>> findListToMono(final String apiUrl, final Class<T> object) {
        return findListToFlux(apiUrl, object)
                .collectList();
    }

    @Override
    public <T> KSPage<T> findPageable(final String apiUrl, final Class<T> object) {
        return Optional.ofNullable(this.webClient.get()
                        .uri(apiUrl)
                        .retrieve()
                        .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                        .toEntity(new ParameterizedTypeReference<KSPage<T>>() {})
                        .blockOptional()
                        .orElseThrow(() -> new SRNotFoundException("조회 결과가 없습니다."))
                        .getBody())
                .orElseThrow(() -> new SRNotFoundException("조회 결과가 없습니다."))
                .convertContent(new ObjectMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false), object);
    }

    @Override
    public <T> Mono<T> findOneMono(final String apiUrl, final Class<T> object) {
        return this.webClient.get()
                .uri(apiUrl)
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .bodyToMono(object);
    }

    @Override
    public <T> ResponseEntity<HttpStatus> put(final String apiUrl, final T requestObject) {
        return this.webClient
                .put()
                .uri(apiUrl)
                .body(fromValue(requestObject))
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .toEntity(HttpStatus.class)
                .block();
    }

    @Override
    public <T> Mono<HttpStatus> putOnMono(final String apiUrl, final T requestObject) {
        return this.webClient
                .put()
                .uri(apiUrl)
                .body(fromValue(requestObject))
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .bodyToMono(HttpStatus.class);
    }

    @Override
    public <V, T> ResponseEntity<T> patch(final String apiUrl, final V requestObject, final Class<T> object) {
        return this.webClient
                .put()
                .uri(apiUrl)
                .body(fromValue(requestObject))
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .toEntity(object)
                .block();
    }

    @Override
    public <T, V> ResponseEntity<V> post(final String apiUrl, final T requestObject, final Class<V> responseObject) {
        return this.webClient.post()
                .uri(apiUrl)
                .body(fromValue(requestObject))
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .toEntity(responseObject)
                .block();
    }

    @Override
    public <T, V> Mono<V> postMono(final String apiUrl, final T requestObject, final Class<V> responseObject) {
        return this.webClient.post()
                .uri(apiUrl)
                .body(fromValue(requestObject))
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .bodyToMono(responseObject);
    }

    @Override
    public <T> ResponseEntity<HttpStatus> post(final String apiUrl, final T requestObject) {
        return post(apiUrl, requestObject, HttpStatus.class);
    }

    @Override
    public void delete(final String apiUrl) {
        this.webClient.delete()
                .uri(apiUrl)
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .bodyToMono(HttpStatus.class)
                .block();
    }

    @Override
    public <T> Mono<T> deleteOnMono(final String apiUrl, final Class<T> object) {
        return this.webClient.delete()
                .uri(apiUrl)
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .bodyToMono(object);
    }

    @Override
    public <V, T> Mono<T> patchOnMono(final String apiUrl, final V requestObject, final Class<T> object) {
        if (isNull(requestObject)) {
            return this.webClient.patch()
                    .uri(apiUrl)
                    .retrieve()
                    .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                    .bodyToMono(object);
        }
        return this.webClient.patch()
                .uri(apiUrl)
                .body(fromValue(requestObject))
                .retrieve()
                .onStatus(HttpStatus::isError, ApiUtils::getThrowableMono)
                .bodyToMono(object);
    }
}
