package com.cengenes.kotlin.api.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Value("\${swagger.host.url}")
    private val host: String? = null

    @Value("\${swagger.host.path}")
    private val path: String? = null

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2).host(host).pathMapping(path)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cengenes.kotlin.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .protocols(HashSet(listOf("https", "http")))
    }

}
