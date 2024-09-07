package com.main.janBnb.config;

import com.twilio.Twilio;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfig {

    private String accountSid;
    private String authToken;
    private String phoneNumber;
    private String whatsappSandboxNumber;


    @Bean
    public void initializeTwilio() {
        Twilio.init(accountSid, authToken);
    }

}
