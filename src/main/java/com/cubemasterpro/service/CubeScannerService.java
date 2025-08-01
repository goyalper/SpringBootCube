package com.cubemasterpro.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.cubemasterpro.model.CubeFace;
import com.cubemasterpro.model.FacePosition;

/**
 * Service interface for scanning Rubik's cube faces.
 */
public interface CubeScannerService {
    
    /**
     * Scans a cube face from an uploaded image file.
     * @param imageFile The image file containing the cube face.
     * @param position The position of the face on the cube.
     * @return The scanned cube face.
     * @throws IOException If there is an error processing the image.
     */
    CubeFace scanFaceFromImage(MultipartFile imageFile, FacePosition position) throws IOException;
    
    /**
     * Captures a cube face from webcam.
     * @param position The position of the face on the cube.
     * @return The captured cube face.
     * @throws IOException If there is an error capturing from the webcam.
     */
    CubeFace captureFaceFromWebcam(FacePosition position) throws IOException;
    
    /**
     * Validates if the cube configuration is valid.
     * @param faces Array of faces to validate.
     * @return True if the configuration is valid, false otherwise.
     */
    boolean validateCubeConfiguration(CubeFace[] faces);
}
