package net.srook.common.apiclient;

import org.springframework.web.reactive.function.client.WebClient;

public interface ApiClientFactory {
    ApiClient parentsDomain();

    ApiClient puddingDomain();

    ApiClient purunetDomain();

    ApiClient buddyDomain();

    ApiClient onlineContractDomain();

    ApiClient ksBookDomain();

    ApiClient misDomain();

    ApiClient misSalesDomain();

    ApiClient parentsBusiness();

    ApiClient teachersBusiness();

    ApiClient commonBusiness();

    ApiClient commonDomain();

    ApiClient onlineContractBusiness();

    ApiClient teacherReferenceBusiness();

    ApiClient kampingDomain();

    ApiClient ischoolDomain();

    ApiClient kampingMemberBusinessApi();

    ApiClient authServerApi();

    ApiClient kspayApi();

    WebClient defaultWebClient();
}
