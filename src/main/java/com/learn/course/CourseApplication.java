package com.learn.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@OpenAPIDefinition(info = @Info(title = "Course API", description = "API for Course", version = "1.0.0", contact = @Contact(name = "Nguyen Di Dan", url = "https://iam.didan.id.vn", email = "didannguyen17@gmail.com"), license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), summary = "Course API"))
@SecuritySchemes({
  @SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "Bearer"
  ),
  @SecurityScheme(
    name = "Basic Authentication",
    type = SecuritySchemeType.HTTP,
    scheme = "basic"
  )
})
@SpringBootApplication
public class CourseApplication {

  public static void main(String[] args) {
    SpringApplication.run(CourseApplication.class, args);
  }

}
