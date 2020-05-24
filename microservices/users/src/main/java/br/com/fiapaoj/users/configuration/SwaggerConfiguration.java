package br.com.fiapaoj.users.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.base.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private static final String API_BASE_PACKAGE = "br.com.fiapaoj.users";

	@Bean
	public Docket remotelyInspectionAPI() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.select() //
				.apis(getApiBasePackage()) //
				.build() //
				.useDefaultResponseMessages(false);
	}

	private Predicate<RequestHandler> getApiBasePackage() {
		return RequestHandlerSelectors.basePackage(API_BASE_PACKAGE);
	}

}
