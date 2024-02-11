package com.example.security.springsecurityinmemory.resoure;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class InMemoryTestResource {

    @GetMapping("/inMemory")
    public String getMappingTest() {
        return "get mapping successful.";
    }

    @PostMapping("/inMemory")
    public String postMappingTest() {
        return "post mapping successful.";
    }

    @PutMapping("/inMemory")
    public String putMappingTest() {
        return "put mapping successful.";
    }

    @DeleteMapping("/inMemory")
    public String deleteMappingTest() {
        return "delete mapping successful.";
    }

}
