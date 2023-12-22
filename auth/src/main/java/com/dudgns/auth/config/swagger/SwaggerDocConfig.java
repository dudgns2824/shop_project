package com.dudgns.auth.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
/**
 * @author ash
 * /서비스명/swagger-ui/index.html 로 접속
 * 서비스목록 : cover, flex,
 */
@Configuration
@Profile({"local","dev","test","staging"})
public class SwaggerDocConfig {
    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Value("${server.name}")
    private String serverName;
    
    @Value("${springdoc.server.url}")
    private String serverUrl;
    
    @Bean
    public OpenAPI openAPI() {
        
        Server server = new Server();
        server.setUrl(serverUrl);
        
        Info info = new Info()
                .title("dudgns API document - "+ serverName)
                .version(null)
                .license(new License().name("백엔드").url("dudgns.com"))
                .description("["+serverName+"] "+"백엔드 api 문서 - "+ activeProfile);
        return new OpenAPI().components(new Components()).info(info).servers(List.of(server));
    }
    
}
