package vn.example.email_scheduler.application.web.spring

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule


@Configuration
class ObjectMapperConfiguration {
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(ParameterNamesModule())
            .registerModule(Jdk8Module())
            .registerModule(JavaTimeModule())
    }
}

