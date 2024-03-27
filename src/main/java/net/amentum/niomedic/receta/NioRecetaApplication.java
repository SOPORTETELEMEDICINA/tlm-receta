package net.amentum.niomedic.receta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import net.amentum.common.RequestFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class NioRecetaApplication extends RequestFilter {

	public static void main(String[] args) {
		SpringApplication.run(NioRecetaApplication.class, args);
	}

   @Bean
   public Docket docket(){
      return new Docket(DocumentationType.SWAGGER_2)
         .apiInfo(apiInfo())
         .select()
         .apis(RequestHandlerSelectors.basePackage("net.amentum.niomedic.receta"))
         .paths(PathSelectors.any())
         .build();
   }

   public ApiInfo apiInfo(){
      return new ApiInfoBuilder()
         .title("Microservicio de recetas")
         .description("Alta, Baja, Edicion de recetas")
         .contact("Amentum IT Services")
         .licenseUrl("http://www.amentum.net")
         .build();
   }
}
