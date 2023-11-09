package br.com.almada.people.config.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigurations {

    @Bean
    public GroupedOpenApi publicApi() {

        return GroupedOpenApi.builder()
                .group("PeopleAPI")
                .pathsToMatch("/**")
                .build();

    }

    @Bean
    public OpenAPI publicAPISettings() {

        return new OpenAPI()
                .info(new Info().title("PeopleAPI")
                        .description("Projeto representando um CRUD básico de uma entidade Pessoa")
                        .version("v0.0.1")
                        .license(new License().name("Spring Doc").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation());

    }


}
