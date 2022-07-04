package com.teraenergy.global.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "open-api") // 기본이 되는 뿌리 키값 여기서는 open-api이다.
@Getter
@Setter //setter가 없으면 Setter가 없다고 에러를 뱉어준다.
public class ApiKeyConfiguration {
    private String kosisKey;
    private String enaraKey;
    private String enaraId;
}