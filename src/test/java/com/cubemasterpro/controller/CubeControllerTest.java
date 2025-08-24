package com.cubemasterpro.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CubeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testServiceEndpoint() throws Exception {
        String url = "http://localhost:" + port + "/api/cube/test";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("CubeScannerService is working correctly!"));
        assertTrue(response.getBody().contains("frontFace"));
        assertTrue(response.getBody().contains("backFace"));
        assertTrue(response.getBody().contains("totalFaces"));
    }
}