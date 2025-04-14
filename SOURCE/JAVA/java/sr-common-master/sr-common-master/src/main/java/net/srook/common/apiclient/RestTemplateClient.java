package net.srook.common.apiclient;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestTemplateClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public RestTemplateClient(final String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
    }

    public <T> ResponseEntity<T> getOne(final String url, final Class<T> responseType) throws RestClientException {
        return this.restTemplate.getForEntity(this.baseUrl + url, responseType);
    }

    public <T> ResponseEntity<T> getForMany(final String url) throws RestClientException {
        return this.restTemplate.exchange(this.baseUrl + url, GET, null, new ParameterizedTypeReference<>(){}, new Object());
    }

    public <T> ResponseEntity<T> post(final String url, final Object request, final Class<T> responseType) throws RestClientException {
        return this.restTemplate.postForEntity(this.baseUrl + url, request, responseType);
    }

    public void put(final String url, final Object request) throws RestClientException {
        this.restTemplate.put(this.baseUrl + url, request);
    }
}
