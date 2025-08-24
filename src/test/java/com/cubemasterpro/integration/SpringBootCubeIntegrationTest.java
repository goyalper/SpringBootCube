package com.cubemasterpro.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootCubeIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHomePage() {
        String url = "http://localhost:" + port + "/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("CubeMaster Pro"));
        assertTrue(response.getBody().contains("Rubik's Cube Solver"));
    }

    @Test
    public void testApiEndpoints() {
        // Test the cube test endpoint
        String testUrl = "http://localhost:" + port + "/api/cube/test";
        ResponseEntity<String> testResponse = restTemplate.getForEntity(testUrl, String.class);
        
        assertEquals(HttpStatus.OK, testResponse.getStatusCode());
        assertTrue(testResponse.getBody().contains("CubeScannerService is working correctly!"));
        
        // Test the sample solve endpoint
        String sampleUrl = "http://localhost:" + port + "/api/solve/sample";
        ResponseEntity<String> sampleResponse = restTemplate.getForEntity(sampleUrl, String.class);
        
        assertEquals(HttpStatus.OK, sampleResponse.getStatusCode());
        assertTrue(sampleResponse.getBody().contains("cube"));
        assertTrue(sampleResponse.getBody().contains("solution"));
    }

    @Test
    public void testWebPages() {
        String baseUrl = "http://localhost:" + port;
        
        // Test scan page
        ResponseEntity<String> scanResponse = restTemplate.getForEntity(baseUrl + "/scan", String.class);
        assertEquals(HttpStatus.OK, scanResponse.getStatusCode());
        assertTrue(scanResponse.getBody().contains("Scan Your Cube"));
        
        // Test manual page
        ResponseEntity<String> manualResponse = restTemplate.getForEntity(baseUrl + "/manual", String.class);
        assertEquals(HttpStatus.OK, manualResponse.getStatusCode());
        assertTrue(manualResponse.getBody().contains("Manual Cube Entry"));
        
        // Test about page
        ResponseEntity<String> aboutResponse = restTemplate.getForEntity(baseUrl + "/about", String.class);
        assertEquals(HttpStatus.OK, aboutResponse.getStatusCode());
        assertTrue(aboutResponse.getBody().contains("About"));
    }
}