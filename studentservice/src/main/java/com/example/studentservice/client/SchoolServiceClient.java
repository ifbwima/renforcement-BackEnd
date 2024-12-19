package com.example.studentservice.client;

import com.example.studentservice.model.School;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SchoolServiceClient {
    private final WebClient webClient;
    private final DiscoveryClient discoveryClient;

    public SchoolServiceClient(WebClient.Builder webClientBuilder, DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.webClient = webClientBuilder.build();
    }

    public Mono<School> getSchoolById(Long schoolId) {
        return discoveryClient.getInstances("school-service")
                .stream()
                .findFirst()
                .map(ServiceInstance::getUri)
                .map(uri -> {
                    String schoolServiceUrl = uri.toString() + "/schools/" + schoolId;
                    return webClient.get()
                            .uri(schoolServiceUrl)
                            .retrieve()
                            .bodyToMono(School.class);
                })
                .orElseGet(() -> Mono.error(new RuntimeException("School service not found")));
    }
}