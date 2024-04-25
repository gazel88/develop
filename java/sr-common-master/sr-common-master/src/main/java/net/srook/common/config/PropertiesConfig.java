package net.srook.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

@Configuration
public class PropertiesConfig {
    public static final String PATTERN_SWAGGER_PROPERTIES = "classpath*:swagger/*.properties";
    public static final String PATTERN_SWAGGER_YML = "classpath*:swagger/*.yml";
    public static final String PATTERN_SWAGGER_YAML = "classpath*:swagger/*.yaml";
    private final ResourceLoader resourceLoader;

    public PropertiesConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean(name = "swaggerProperties")
    public PropertiesFactoryBean getPropertiesFactoryBean() throws IOException {
        final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setProperties(getAllProperties());
        return propertiesFactoryBean;
    }

    private Properties getAllProperties() throws IOException {
        final Properties properties = new Properties();
        properties.putAll(getPropertiesFromYaml());
        properties.putAll(getProperties());
        return properties;
    }

    private Properties getPropertiesFromYaml() throws IOException {
        Properties properties = new Properties();
        final List<Resource> resources = new ArrayList<>();
        resources.addAll(loadInputStreamResources(PATTERN_SWAGGER_YML));
        resources.addAll(loadInputStreamResources(PATTERN_SWAGGER_YAML));
        final YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        for (Resource resource : resources) {
            yamlPropertiesFactoryBean.setResources(resource);
            properties.putAll(yamlPropertiesFactoryBean.getObject());
        }
        return properties;
    }

    private Properties getProperties() throws IOException {
        final Properties properties = new Properties();
        for (InputStream inputStream : loadInputStreams(PATTERN_SWAGGER_PROPERTIES)) {
            properties.load(inputStream);
        }
        return properties;
    }

    private List<Resource> loadInputStreamResources(String pattern) throws IOException {
        final List<Resource> resources = getResources(pattern);
        return resources.stream()
                .map(this::toInputStreamResource)
                .collect(Collectors.toList());
    }

    private List<InputStream> loadInputStreams(String pattern) throws IOException {
        final List<Resource> resources = getResources(pattern);
        return resources.stream()
                .map(this::toInputStream)
                .collect(Collectors.toList());
    }

    private List<Resource> getResources(final String pattern) throws IOException {
        return List.of(ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(pattern));
    }

    private InputStreamResource toInputStreamResource(final Resource resource) {
        return new InputStreamResource(toInputStream(resource));
    }

    private InputStream toInputStream(final Resource resource) {
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
