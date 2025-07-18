package dsm.hackaton._8.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dsm.hackaton._8.infrastructure.feign.FeignClientErrorDecoder;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

@EnableFeignClients(basePackages = "dsm.hackaton._8")
@Configuration
@Import(FeignClientErrorDecoder.class)
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public FeignClientErrorDecoder commonFeignErrorDecoder() {
        return new FeignClientErrorDecoder();
    }

    @Bean
    public Decoder feignDecoder() {
        ObjectMapper objectMapper = new XmlMapper(); // XML 전용 ObjectMapper
        objectMapper.registerModule(new JavaTimeModule());
        HttpMessageConverter<?> converter = new MappingJackson2XmlHttpMessageConverter(objectMapper);
        return new SpringDecoder(() -> new HttpMessageConverters(converter));
    }
}
