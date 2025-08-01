package com.cubemasterpro.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cubemasterpro.model.CubeColor;
import com.cubemasterpro.model.CubeFace;
import com.cubemasterpro.model.FacePosition;
import com.cubemasterpro.service.CubeScannerService;

/**
 * REST controller for AJAX requests.
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private CubeScannerService scannerService;
    
    /**
     * Capture a cube face from the webcam.
     * @param position The position of the face on the cube.
     * @return The captured face.
     */
    @PostMapping("/capture")
    public ResponseEntity<?> captureFace(@RequestParam("position") FacePosition position) {
        try {
            CubeFace face = scannerService.captureFaceFromWebcam(position);
            
            // Convert the face to a map for JSON response
            Map<String, Object> response = new HashMap<>();
            response.put("position", position);
            
            // Convert stickers to a 2D array of color names
            String[][] colors = new String[CubeFace.FACE_SIZE][CubeFace.FACE_SIZE];
            
            for (int i = 0; i < CubeFace.FACE_SIZE; i++) {
                for (int j = 0; j < CubeFace.FACE_SIZE; j++) {
                    colors[i][j] = face.getSticker(i, j).getName();
                }
            }
            
            response.put("stickers", colors);
            
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to capture face: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * Get the available colors for the cube.
     * @return The list of available colors.
     */
    @GetMapping("/colors")
    public ResponseEntity<?> getColors() {
        CubeColor[] colors = new CubeColor[]{
            CubeColor.WHITE, CubeColor.YELLOW, CubeColor.GREEN, 
            CubeColor.BLUE, CubeColor.RED, CubeColor.ORANGE
        };
        
        // Convert colors to a list of maps for JSON response
        Map<String, Object>[] colorMaps = new Map[colors.length];
        
        for (int i = 0; i < colors.length; i++) {
            Map<String, Object> colorMap = new HashMap<>();
            colorMap.put("name", colors[i].getName());
            colorMap.put("hexCode", colors[i].getHexCode());
            colorMaps[i] = colorMap;
        }
        
        return ResponseEntity.ok(colorMaps);
    }
}
