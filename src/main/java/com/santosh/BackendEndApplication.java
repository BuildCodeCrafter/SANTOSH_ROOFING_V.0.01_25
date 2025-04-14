package com.santosh;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition(
        info = @Info(
                title = "SANTOSH-ROOFING project REST API Documentation",
                description = "SANTOSH-ROOFING application REST API Documentation",
                version="v1",
                contact = @Contact(
                        name = "Code-crafter",
                        email = "info@code-crafter.in",
                        url = "https://code-crafter.in"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "SANTOSH-ROOFING application REST API Documentation",
                url = "https://code-crafter.in"
        )
)
@SpringBootApplication
@RestController
@CrossOrigin("*")
public class BackendEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendEndApplication.class, args);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Application Running");
    }


}
