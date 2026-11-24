package com.hmdp.mcp.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.server.transport.HttpServletStatelessServerTransport;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpServerConfig {

    public static final String MCP_ENDPOINT = "/mcp";

    @Bean
    public HttpServletStatelessServerTransport httpServletStatelessServerTransport() {
        ObjectMapper mcpObjectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return HttpServletStatelessServerTransport.builder()
                .objectMapper(mcpObjectMapper)
                .messageEndpoint(MCP_ENDPOINT)
                .build();
    }

    @Bean
    public ServletRegistrationBean<HttpServletStatelessServerTransport> mcpServletRegistration(
            HttpServletStatelessServerTransport transport) {
        ServletRegistrationBean<HttpServletStatelessServerTransport> bean = new ServletRegistrationBean<>(transport);
        bean.addUrlMappings(MCP_ENDPOINT, MCP_ENDPOINT + "/");
        return bean;
    }
}
