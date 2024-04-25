package net.srook.common.apiclient;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.srook.common.dto.ErrorResponse;
import net.srook.common.dto.KSPage;
import net.srook.common.exception.SRHttpStatusException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiClient {
    <T> ResponseEntity<T> findOne(final String apiUrl, final Class<T> object);

    <T> Mono<T> findOneMono(final String apiUrl, final Class<T> object);

    <T> ResponseEntity<List<T>> findList(final String apiUrl, final Class<T> object);

    <T> Flux<T> findListToFlux(final String apiUrl, final Class<T> object);

    <T> Mono<List<T>> findListToMono(final String apiUrl, final Class<T> object);

    <T> KSPage<T> findPageable(final String apiUrl, final Class<T> object);

    <T> ResponseEntity<HttpStatus> put(final String apiUrl, final T requestObject);

    <T> Mono<HttpStatus> putOnMono(final String apiUrl, final T requestObject);

    <V, T> ResponseEntity<T> patch(final String apiUrl, final V requestObject, final Class<T> object);

    <V, T> Mono<T> patchOnMono(final String apiUrl, final V requestObject, final Class<T> object);

    <T, V> ResponseEntity<V> post(final String apiUrl, final T requestObject, final Class<V> responseObject);

    <T, V> Mono<V> postMono(final String apiUrl, final T requestObject, final Class<V> responseObject);

    <T> ResponseEntity<HttpStatus> post(final String apiUrl, final T requestObject);

    void delete(final String apiUrl);

    <T> Mono<T> deleteOnMono(final String apiUrl, final Class<T> object);

    default String makePageableQuery(final int page, final int size) {
        return "?page=" + page + "&size=" + size;
    }

    default <T> void validateHttpStatusCodeError(final ResponseEntity<T> response) {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            Optional.ofNullable(response.getBody())
                    .ifPresent(body -> {
                        if (body instanceof ErrorResponse) {
                            final String errorMessage = "Domain API Server Error : " + ((ErrorResponse) body).getErrorMessage();
                            throw new SRHttpStatusException(errorMessage);
                        }
                    });
            throw new SRHttpStatusException("예기치 못한 오류 발생. 서버로그 확인.");
        }
    }
}
