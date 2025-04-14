package net.srook.common.apiclient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApiClientFactoryImpl implements ApiClientFactory {
    private final ApiClient parentsDomainApi;
    private final ApiClient puddingDomainApi;
    private final ApiClient purunetDomainAPi;
    private final ApiClient buddyDomainApi;
    private final ApiClient onlineContractDomainApi;
    private final ApiClient misDomainApi;
    private final ApiClient misSalesDomainApi;
    private final ApiClient parentsBusinessApi;
    private final ApiClient teachersBusinessApi;
    private final ApiClient commonBusinessApi;
    private final ApiClient commonDomainApi;
    private final ApiClient onlineContractBusinessApi;
    private final ApiClient ksBookDomainApi;
    private final ApiClient teacherReferenceBusinessApi;
    private final ApiClient kampingDomainApi;
    private final ApiClient ischoolDomainApi;
    private final ApiClient kampingMemberApi;
    private final ApiClient authServerApi;
    private final ApiClient kspayApi;
    private final WebClient webClient;

    public ApiClientFactoryImpl(final ApiClient parentsDomainApi, final ApiClient puddingDomainApi,
                                final ApiClient purunetDomainAPi, final ApiClient buddyDomainApi,
                                final ApiClient onlineContractDomainApi, final ApiClient misDomainApi,
                                final ApiClient misSalesDomainApi, final ApiClient parentsBusinessApi,
                                final ApiClient teachersBusinessApi, final ApiClient commonBusinessApi,
                                final ApiClient commonDomainApi, final ApiClient onlineContractBusinessApi,
                                final ApiClient ksBookDomainApi, final ApiClient teacherReferenceBusinessApi,
                                final ApiClient kampingDomainApi, final ApiClient ischoolDomainApi,
                                final ApiClient kampingMemberApi, final ApiClient authServerApi,
                                final ApiClient kspayApi, final WebClient webClient) {
        this.parentsDomainApi = parentsDomainApi;
        this.puddingDomainApi = puddingDomainApi;
        this.purunetDomainAPi = purunetDomainAPi;
        this.buddyDomainApi = buddyDomainApi;
        this.onlineContractDomainApi = onlineContractDomainApi;
        this.misDomainApi = misDomainApi;
        this.misSalesDomainApi = misSalesDomainApi;
        this.parentsBusinessApi = parentsBusinessApi;
        this.teachersBusinessApi = teachersBusinessApi;
        this.commonBusinessApi = commonBusinessApi;
        this.commonDomainApi = commonDomainApi;
        this.onlineContractBusinessApi = onlineContractBusinessApi;
        this.ksBookDomainApi = ksBookDomainApi;
        this.teacherReferenceBusinessApi = teacherReferenceBusinessApi;
        this.kampingDomainApi = kampingDomainApi;
        this.ischoolDomainApi = ischoolDomainApi;
        this.kampingMemberApi = kampingMemberApi;
        this.authServerApi = authServerApi;
        this.kspayApi = kspayApi;
        this.webClient = webClient;
    }

    @Override
    public ApiClient parentsDomain() {
        return this.parentsDomainApi;
    }

    @Override
    public ApiClient puddingDomain() {
        return this.puddingDomainApi;
    }

    @Override
    public ApiClient purunetDomain() {
        return this.purunetDomainAPi;
    }

    @Override
    public ApiClient buddyDomain() {
        return this.buddyDomainApi;
    }

    @Override
    public ApiClient onlineContractDomain() {
        return this.onlineContractDomainApi;
    }

    @Override
    public ApiClient ksBookDomain() {
        return this.ksBookDomainApi;
    }

    @Override
    public ApiClient misDomain() {
        return this.misDomainApi;
    }

    @Override
    public ApiClient misSalesDomain() {
        return this.misSalesDomainApi;
    }

    @Override
    public ApiClient parentsBusiness() {
        return this.parentsBusinessApi;
    }

    @Override
    public ApiClient teachersBusiness() {
        return this.teachersBusinessApi;
    }

    @Override
    public ApiClient commonBusiness() {
        return this.commonBusinessApi;
    }

    @Override
    public ApiClient commonDomain() {
        return this.commonDomainApi;
    }

    @Override
    public ApiClient onlineContractBusiness() {
        return this.onlineContractBusinessApi;
    }

    @Override
    public ApiClient teacherReferenceBusiness() {
        return this.teacherReferenceBusinessApi;
    }

    @Override
    public ApiClient kampingDomain() {
        return this.kampingDomainApi;
    }

    @Override
    public ApiClient ischoolDomain() {
        return this.ischoolDomainApi;
    }

    @Override
    public ApiClient kampingMemberBusinessApi() {
        return this.kampingMemberApi;
    }

    @Override
    public ApiClient authServerApi() {
        return this.authServerApi;
    }

    @Override
    public ApiClient kspayApi() {
        return this.kspayApi;
    }

    @Override
    public WebClient defaultWebClient() {
        return this.webClient;
    }
}
