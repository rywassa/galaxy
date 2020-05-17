package br.com.ywassa.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.ywassa.galaxy.controller.Resources;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
public class GalaxyApplication {

	@Configuration
	@EnableSwagger2
	protected static class SwaggerUiConfiguration {

		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.ywassa.galaxy.controller"))
					.paths(PathSelectors.any())
					.build()
					.apiInfo(metaData());
		}

		private ApiInfo metaData() {
			return new ApiInfoBuilder()
					.title("REST API")
					.description("\"Galaxy REST API\"")
					.version(Resources.TOKEN_VERSION)
					.build();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(GalaxyApplication.class, args);
	}

}
