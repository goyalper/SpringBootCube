package com.cubemasterpro.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cubemasterpro.model.CubeFace;
import com.cubemasterpro.model.FacePosition;
import com.cubemasterpro.service.CubeScannerService;

@RestController
@RequestMapping("/api/cube")
public class CubeController {
    
    @Autowired
    private CubeScannerService cubeScannerService;
    
    @PostMapping("/scan")
    public ResponseEntity<CubeFace> scanFace(
            @RequestParam("image") MultipartFile image,
            @RequestParam("position") FacePosition position) throws IOException {
        CubeFace face = cubeScannerService.scanFaceFromImage(image, position);
        return ResponseEntity.ok(face);
    }
    
    @GetMapping("/capture/{position}")
    public ResponseEntity<CubeFace> captureFace(@PathVariable FacePosition position) throws IOException {
        CubeFace face = cubeScannerService.captureFaceFromWebcam(position);
        return ResponseEntity.ok(face);
    }
    
    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> testService() throws IOException {
        Map<String, Object> result = new HashMap<>();
        
        // Test generating random faces
        CubeFace frontFace = cubeScannerService.captureFaceFromWebcam(FacePosition.FRONT);
        CubeFace backFace = cubeScannerService.captureFaceFromWebcam(FacePosition.BACK);
        
        // Test validation
        CubeFace[] faces = new CubeFace[FacePosition.values().length];
        for (int i = 0; i < FacePosition.values().length; i++) {
            faces[i] = cubeScannerService.captureFaceFromWebcam(FacePosition.values()[i]);
        }
        boolean isValid = cubeScannerService.validateCubeConfiguration(faces);
        
        result.put("frontFace", frontFace);
        result.put("backFace", backFace);
        result.put("isValidConfiguration", isValid);
        result.put("totalFaces", faces.length);
        result.put("message", "CubeScannerService is working correctly!");
        
        return ResponseEntity.ok(result);
    }
}
