package net.srook.common.apiclient;

import static net.srook.common.utils.ApiUtils.createHttpUri;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ApiClientConfig {
    private final String parentsDomainAppHost;
    private final String purunetDomainAppHost;
    private final String parentsDomainAppPort;
    private final String purunetDomainAppPort;
    private final String parentsBusinessAppHost;
    private final String misDomainAppPort;
    private final String misDomainAppHost;
    private final String misSalesDomainAppPort;
    private final String misSalesDomainAppHost;
    private final String parentsBusinessAppPort;
    private final String teachersBusinessAppHost;
    private final String teachersBusinessAppPort;
    private final String onlineContractBusinessAppPort;
    private final String onlineContractBusinessAppHost;
    private final String cmmBusinessAppHost;
    private final String cmmBusinessAppPort;
    private final String buddyDomainAppHost;
    private final String buddyDomainAppPort;
    private final String teacherReferenceBusinessAppHost;
    private final String teacherReferenceBusinessAppPort;
    private final String ksBookDomainAppHost;
    private final String ksBookDomainAppPort;

    public ApiClientConfig(@Value("${app.parents-domain.host}") final String parentsDomainAppHost,
                           @Value("${app.purunet-domain.host}") final String purunetDomainAppHost,
                           @Value("${app.parents-domain.port}") final String parentsDomainAppPort,
                           @Value("${app.purunet-domain.port}") final String purunetDomainAppPort,
                           @Value("${app.mis-domain.host}") final String misDomainAppHost,
                           @Value("${app.mis-domain.port}") final String misDomainAppPort,
                           @Value("${app.mis-sales-domain.host}") final String misSalesDomainAppHost,
                           @Value("${app.mis-sales-domain.port}") final String misSalesDomainAppPort,
                           @Value("${app.parents-business.host}") final String parentsBusinessAppHost,
                           @Value("${app.parents-business.port}") final String parentsBusinessAppPort,
                           @Value("${app.teachers-business.host}") final String teachersBusinessAppHost,
                           @Value("${app.teachers-business.port}") final String teachersBusinessAppPort,
                           @Value("${app.online-contract-business.host}") final String onlineContractBusinessAppHost,
                           @Value("${app.online-contract-business.port}") final String onlineContractBusinessAppPort,
                           @Value("${app.cmm-business.host}") final String cmmBusinessAppHost,
                           @Value("${app.cmm-business.port}") final String cmmBusinessAppPort,
                           @Value("${app.buddy-domain.host}") final String buddyDomainAppHost,
                           @Value("${app.buddy-domain.port}") final String buddyDomainAppPort,
                           @Value("${app.teacher-reference-business.host}") final String teacherReferenceBusinessAppHost,
                           @Value("${app.teacher-reference-business.port}") final String teacherReferenceBusinessAppPort,
                           @Value("${app.ks-book-domain.host}") final String ksBookDomainAppHost,
                           @Value("${app.ks-book-domain.port}") final String ksBookDomainAppPort) {
        this.parentsDomainAppHost = parentsDomainAppHost;
        this.purunetDomainAppHost = purunetDomainAppHost;
        this.parentsDomainAppPort = parentsDomainAppPort;
        this.purunetDomainAppPort = purunetDomainAppPort;
        this.misDomainAppHost = misDomainAppHost;
        this.misDomainAppPort = misDomainAppPort;
        this.misSalesDomainAppHost = misSalesDomainAppHost;
        this.misSalesDomainAppPort = misSalesDomainAppPort;
        this.parentsBusinessAppHost = parentsBusinessAppHost;
        this.parentsBusinessAppPort = parentsBusinessAppPort;
        this.teachersBusinessAppHost = teachersBusinessAppHost;
        this.teachersBusinessAppPort = teachersBusinessAppPort;
        this.onlineContractBusinessAppHost = onlineContractBusinessAppHost;
        this.onlineContractBusinessAppPort = onlineContractBusinessAppPort;
        this.cmmBusinessAppHost = cmmBusinessAppHost;
        this.cmmBusinessAppPort = cmmBusinessAppPort;
        this.buddyDomainAppHost = buddyDomainAppHost;
        this.buddyDomainAppPort = buddyDomainAppPort;
        this.teacherReferenceBusinessAppHost = teacherReferenceBusinessAppHost;
        this.teacherReferenceBusinessAppPort = teacherReferenceBusinessAppPort;
        this.ksBookDomainAppHost = ksBookDomainAppHost;
        this.ksBookDomainAppPort = ksBookDomainAppPort;
    }

    @Bean(name = "webClient")
    public WebClient getDefaultWebClient(final WebClient.Builder directWebClientBuilder) {
        return directWebClientBuilder.build();
    }

    @Bean("parentsDomainApi")
    public ApiClient parentsDomainApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.parentsDomainAppHost, this.parentsDomainAppPort));
    }

    @Bean("purunetDomainAPi")
    public ApiClient purunetDomainAPi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.purunetDomainAppHost, this.purunetDomainAppPort));
    }

    @Bean("buddyDomainApi")
    public ApiClient buddyDomainApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.buddyDomainAppHost, this.buddyDomainAppPort));
    }

    @Bean("onlineContractDomainApi")
    public ApiClient onlineContractDomainApi(final WebClient.Builder webClientBuilder,
                                             @Value("${app.online-contract-domain.host}") final String onlineContractDomainAppHost,
                                             @Value("${app.online-contract-domain.port}") final String onlineContractDomainAppPort) {
        return makeApiClient(webClientBuilder,
                makeBaseUrl(onlineContractDomainAppHost, onlineContractDomainAppPort));
    }

    @Bean("ksBookDomainApi")
    public ApiClient ksBookDomainApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.ksBookDomainAppHost, this.ksBookDomainAppPort));
    }

    @Bean("misDomainApi")
    public ApiClient misDomainApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.misDomainAppHost, this.misDomainAppPort));
    }

    @Bean("misSalesDomainApi")
    public ApiClient misSalesDomainApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.misSalesDomainAppHost, this.misSalesDomainAppPort));
    }

    @Bean("parentsBusinessApi")
    public ApiClient parentsBusinessApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.parentsBusinessAppHost, this.parentsBusinessAppPort));
    }

    @Bean("teachersBusinessApi")
    public ApiClient teachersBusinessApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.teachersBusinessAppHost, this.teachersBusinessAppPort));
    }

    @Bean("commonBusinessApi")
    public ApiClient commonBusinessApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder, makeBaseUrl(this.cmmBusinessAppHost, this.cmmBusinessAppPort));
    }

    @Bean("commonDomainApi")
    public ApiClient commonDomainApi(final WebClient.Builder webClientBuilder,
                                     @Value("${app.cmm-domain.host}") final String commonDomainApiHost,
                                     @Value("${app.cmm-domain.port}") final String commonDomainApiPort) {
        return makeApiClient(webClientBuilder, makeBaseUrl(commonDomainApiHost, commonDomainApiPort));
    }

    @Bean("onlineContractBusinessApi")
    public ApiClient onlineContractBusinessApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder,
                makeBaseUrl(this.onlineContractBusinessAppHost, this.onlineContractBusinessAppPort));
    }

    @Bean("teacherReferenceBusinessApi")
    public ApiClient teacherReferenceBusinessApi(final WebClient.Builder webClientBuilder) {
        return makeApiClient(webClientBuilder,
                makeBaseUrl(this.teacherReferenceBusinessAppHost, this.teacherReferenceBusinessAppPort));
    }

    @Bean("kampingMemberApi")
    public ApiClient kampingMemberApi(final WebClient.Builder webClientBuilder,
                                      @Value("${app.kamping-member-api.host}") final String kampingMemberApiHost,
                                      @Value("${app.kamping-member-api.port}") final String kampingMemberApiPort) {
        return makeApiClient(webClientBuilder, makeBaseUrl(kampingMemberApiHost, kampingMemberApiPort));
    }

    @Bean("kampingDomainApi")
    public ApiClient kampingDomainApi(final WebClient.Builder webClientBuilder,
                                      @Value("${app.kamping-domain.host}") final String kampingDomainAppHost,
                                      @Value("${app.kamping-domain.port}") final String kampingDomainAppPort) {
        return makeApiClient(webClientBuilder, makeBaseUrl(kampingDomainAppHost, kampingDomainAppPort));
    }

    @Bean("ischoolDomainApi")
    public ApiClient ischoolDomainApi(final WebClient.Builder webClientBuilder,
                                      @Value("${app.ischool-domain.host}") final String ischoolDomainAppHost,
                                      @Value("${app.ischool-domain.port}") final String ischoolDomainAppPort) {
        return makeApiClient(webClientBuilder, makeBaseUrl(ischoolDomainAppHost, ischoolDomainAppPort));
    }

    @Bean("puddingDomainApi")
    public ApiClient puddingDomainApi(final WebClient.Builder webClientBuilder,
                                      @Value("${app.pudding-domain.host}") final String apiHost,
                                      @Value("${app.pudding-domain.port}") final String apiPort) {
        return makeApiClient(webClientBuilder, makeBaseUrl(apiHost, apiPort));
    }

    @Bean("authServerApi")
    public ApiClient authServerApi(final WebClient.Builder webClientBuilder,
                                   @Value("${app.auth-server.host}") final String authServerApiHost,
                                   @Value("${app.auth-server.port}") final String authServerApiPort) {
        return makeApiClient(webClientBuilder, makeBaseUrl(authServerApiHost, authServerApiPort));
    }

    @Bean("kspayApi")
    public ApiClient kspayApi(final WebClient.Builder webClientBuilder,
                              @Value("${app.cmm-kspay-api.host}") final String kspayApiHost,
                              @Value("${app.cmm-kspay-api.port}") final String kspayApiPort) {
        return makeApiClient(webClientBuilder, makeBaseUrl(kspayApiHost, kspayApiPort));
    }

    private ApiClientImpl makeApiClient(final WebClient.Builder webClientBuilder, final String baseUrl) {
        return new ApiClientImpl(webClientBuilder.baseUrl(baseUrl).build());
    }

    @Profile({"!local"})
    @Bean(name = "webClientBuilder")
//    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return makeWebClientBuilder();
    }

    @Profile({"!local"})
    @Bean(name = "directWebClientBuilder")
    public WebClient.Builder directWebClientBuilder() {
        return makeWebClientBuilder();
    }

    @Profile({"local"})
    @Bean("webClientBuilder")
    public WebClient.Builder localWebClientBuilder() {
        return makeWebClientBuilder();
    }

    private WebClient.Builder makeWebClientBuilder() {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
                .build();
        return WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper(new ObjectMapper()
                .getFactory()
                .configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true))
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private String makeBaseUrl(final String appHost, final String appPort) {
        return createHttpUri().host(appHost).port(appPort).toUriString();
    }
}
