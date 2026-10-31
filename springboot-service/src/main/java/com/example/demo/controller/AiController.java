
// package com.example.demo.controller;

// import java.util.Map;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.RestClient;

// @RestController
// @RequestMapping("/api")
// public class AiController {

//     private final RestClient restClient;

//     public AiController(
//             @Value("${python.service.url:http://localhost:8000}")
//             String pythonServiceUrl) {

//         this.restClient = RestClient.builder()
//                 .baseUrl(pythonServiceUrl)
//                 .build();
//     }

//     @GetMapping("/health")
//     public Map<String, String> health() {
//         return Map.of(
//                 "status", "healthy",
//                 "service", "springboot-service"
//         );
//     }

//     @PostMapping(
//             value = "/ask",
//             consumes = MediaType.APPLICATION_JSON_VALUE,
//             produces = MediaType.APPLICATION_JSON_VALUE
//     )
//     public Map<?, ?> ask(@RequestBody Map<String, String> request) {
//         String question = request.get("question");

//         Map<String, String> pythonRequest =
//                 Map.of("question", question);

//         return restClient.post()
//                 .uri("/ask")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .body(pythonRequest)
//                 .retrieve()
//                 .body(Map.class);
//     }
// }

package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api")
public class AiController {

    private final RestClient restClient;

    public AiController(
            @Value("${python.service.url:http://localhost:8000}")
            String pythonServiceUrl) {

        this.restClient = RestClient.builder()
                .baseUrl(pythonServiceUrl)
                .build();
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "healthy",
                "service", "springboot-service"
        );
    }

    @PostMapping(
            value = "/ask",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Map<?, ?> ask(@RequestBody Map<String, String> request) {
        String question = request.get("question");

        Map<String, String> pythonRequest =
                Map.of("question", question);

        return restClient.post()
                .uri("/ask")
                .contentType(MediaType.APPLICATION_JSON)
                .body(pythonRequest)
                .retrieve()
                .body(Map.class);
    }
}
