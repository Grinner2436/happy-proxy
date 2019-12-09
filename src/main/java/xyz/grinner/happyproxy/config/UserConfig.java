package xyz.grinner.happyproxy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.grinner.happyproxy.pool.PearlString;

@Data
@Configuration
@ConfigurationProperties(prefix = "user")
public class UserConfig {
    private String liheToken;

    @Bean
    public PearlString<String,String> pool(){
        return new PearlString<String,String>();
    }
}
