package net.srook.common.mail;

import static software.amazon.awssdk.regions.Region.AP_NORTHEAST_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.sesv2.SesV2Client;

@Configuration
public class SesV2MailConfig {
    @Bean
    public SesV2Client getSesV2Client() {
        return SesV2Client.builder()
                .region(AP_NORTHEAST_2)
                .build();
    }
}
