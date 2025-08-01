package com.cubemasterpro.service.impl;

import com.cubemasterpro.model.CubeColor;
import com.cubemasterpro.model.CubeFace;
import com.cubemasterpro.model.FacePosition;
import com.cubemasterpro.service.CubeScannerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Random;

/**
 * Simplified stub implementation of CubeScannerService.
 * Generates random cube faces for testing purposes.
 */
@Service
public class OpenCVCubeScannerService implements CubeScannerService {
    private static final Random RANDOM = new Random();

    @Override
    public CubeFace scanFaceFromImage(MultipartFile imageFile, FacePosition position) throws IOException {
        return createRandomFace(position);
    }

    @Override
    public CubeFace captureFaceFromWebcam(FacePosition position) throws IOException {
        return createRandomFace(position);
    }

    @Override
    public boolean validateCubeConfiguration(CubeFace[] faces) {
        return faces != null && faces.length == FacePosition.values().length;
    }

    private CubeFace createRandomFace(FacePosition position) {
        CubeFace face = new CubeFace();
        face.setPosition(position);
        CubeColor[] colors = CubeColor.values();
        CubeColor[][] stickers = new CubeColor[CubeFace.FACE_SIZE][CubeFace.FACE_SIZE];
        for (int i = 0; i < CubeFace.FACE_SIZE; i++) {
            for (int j = 0; j < CubeFace.FACE_SIZE; j++) {
                stickers[i][j] = colors[RANDOM.nextInt(colors.length)];
            }
        }
        face.setStickers(stickers);
        return face;
    }
}