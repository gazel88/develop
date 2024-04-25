package net.srook.common.config;

import static java.util.Collections.emptyList;
import static java.util.List.of;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.OAS_30;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

import com.fasterxml.classmate.TypeResolver;

import net.srook.common.dto.ErrorResponse;
import net.srook.common.security.jwt.annotation.AuthenticationPrincipal;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Profile({"!test"})
@Configuration
public class SwaggerConfig implements WebFluxConfigurer, WebMvcOpenApiTransformationFilter {
    private static final String BASE_PACKAGE = "kr.co.kumsung";
    private final String apiVersion;
    private final String apiTitle;
    private final String apiDescription;
    private final String apiTermsOfServiceUrl;
    private final String apiLicense;
    private final String apiLicenseUrl;
    private final String apiContactName;
    private final String apiContactUrl;
    private final String apiContactEmail;
    private final TypeResolver typeResolver;
    private final String swaggerServerTitle;
    private final String swaggerServerUrl;

    public SwaggerConfig(@Value("${api.common.version:}") final String apiVersion,
                         @Value("${api.common.title:}") final String apiTitle,
                         @Value("${api.common.description:}") final String apiDescription,
                         @Value("${api.common.termsOfServiceUrl:}") final String apiTermsOfServiceUrl,
                         @Value("${api.common.license:}") final String apiLicense,
                         @Value("${api.common.licenseUrl:}") final String apiLicenseUrl,
                         @Value("${api.common.contact.name:}") final String apiContactName,
                         @Value("${api.common.contact.url:}") final String apiContactUrl,
                         @Value("${api.common.contact.email:}") final String apiContactEmail,
                         @Value("${swagger.server.title:}") final String swaggerServerTitle,
                         @Value("${swagger.server.url:}") final String swaggerServerUrl,
                         final TypeResolver typeResolver) {
        this.apiVersion = apiVersion;
        this.apiTitle = apiTitle;
        this.apiDescription = apiDescription;
        this.apiTermsOfServiceUrl = apiTermsOfServiceUrl;
        this.apiLicense = apiLicense;
        this.apiLicenseUrl = apiLicenseUrl;
        this.apiContactName = apiContactName;
        this.apiContactUrl = apiContactUrl;
        this.apiContactEmail = apiContactEmail;
        this.typeResolver = typeResolver;
        this.swaggerServerTitle = swaggerServerTitle;
        this.swaggerServerUrl = swaggerServerUrl;
    }

    @Bean
    public Docket apiDocumentation() {
        return new Docket(OAS_30)
                .apiInfo(new ApiInfo(apiTitle, apiDescription, apiVersion, apiTermsOfServiceUrl,
                        new Contact(apiContactName, apiContactUrl, apiContactEmail), apiLicense, apiLicenseUrl,
                        emptyList()))
                .select()
                .apis(basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(Optional.class, Mono.class, Flux.class)
                .additionalModels(this.typeResolver.resolve(ErrorResponse.class))
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .useDefaultResponseMessages(false)
                .securitySchemes(of(authToken()))
                ;
    }

    private ApiKey authToken() {
        return new ApiKey("Authorization", "Bearer", "header");
    }

    @Override
    public void configureArgumentResolvers(final ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
    }

    @Override
    public boolean supports(final DocumentationType delimiter) {
        return delimiter.equals(OAS_30);
    }

    @Override
    public OpenAPI transform(final OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI openApi = context.getSpecification();
        Server prodServer = makeServer(this.swaggerServerTitle, this.swaggerServerUrl);
        openApi.setServers(List.of(prodServer));
        return openApi;
    }

    private Server makeServer(final String serverDescription, final String serverUrl) {
        return new Server()
                .url(serverUrl)
                .description(serverDescription);
    }
}
