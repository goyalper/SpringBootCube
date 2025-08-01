package com.cubemasterpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a complete Rubik's Cube with six faces.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RubiksCube {
    
    private CubeFace upFace;
    private CubeFace downFace;
    private CubeFace frontFace;
    private CubeFace backFace;
    private CubeFace rightFace;
    private CubeFace leftFace;
    
    /**
     * Creates a solved Rubik's Cube with standard color orientation:
     * - UP: White
     * - DOWN: Yellow
     * - FRONT: Green
     * - BACK: Blue
     * - RIGHT: Red
     * - LEFT: Orange
     * 
     * @return A solved Rubik's Cube.
     */
    public static RubiksCube createSolvedCube() {
        RubiksCube cube = new RubiksCube();
        
        cube.setUpFace(CubeFace.createUniformFace(FacePosition.UP, CubeColor.WHITE));
        cube.setDownFace(CubeFace.createUniformFace(FacePosition.DOWN, CubeColor.YELLOW));
        cube.setFrontFace(CubeFace.createUniformFace(FacePosition.FRONT, CubeColor.GREEN));
        cube.setBackFace(CubeFace.createUniformFace(FacePosition.BACK, CubeColor.BLUE));
        cube.setRightFace(CubeFace.createUniformFace(FacePosition.RIGHT, CubeColor.RED));
        cube.setLeftFace(CubeFace.createUniformFace(FacePosition.LEFT, CubeColor.ORANGE));
        
        return cube;
    }
    
    /**
     * Get a face by its position.
     * @param position The position of the face to get.
     * @return The face at the given position.
     */
    public CubeFace getFaceByPosition(FacePosition position) {
        switch (position) {
            case UP:    return upFace;
            case DOWN:  return downFace;
            case FRONT: return frontFace;
            case BACK:  return backFace;
            case RIGHT: return rightFace;
            case LEFT:  return leftFace;
            default:    throw new IllegalArgumentException("Invalid face position: " + position);
        }
    }
    
    /**
     * Set a face at a specified position.
     * @param position The position to set the face at.
     * @param face The face to set.
     */
    public void setFaceByPosition(FacePosition position, CubeFace face) {
        face.setPosition(position);
        
        switch (position) {
            case UP:    upFace = face; break;
            case DOWN:  downFace = face; break;
            case FRONT: frontFace = face; break;
            case BACK:  backFace = face; break;
            case RIGHT: rightFace = face; break;
            case LEFT:  leftFace = face; break;
            default:    throw new IllegalArgumentException("Invalid face position: " + position);
        }
    }
    
    /**
     * Checks if the cube state is valid (i.e., each color appears exactly 9 times).
     * @return true if the cube state is valid, false otherwise.
     */
    public boolean isValidState() {
        // Count occurrences of each color
        int[] colorCounts = new int[6]; // one for each standard color
        
        countColorsOnFace(upFace, colorCounts);
        countColorsOnFace(downFace, colorCounts);
        countColorsOnFace(frontFace, colorCounts);
        countColorsOnFace(backFace, colorCounts);
        countColorsOnFace(rightFace, colorCounts);
        countColorsOnFace(leftFace, colorCounts);
        
        // Each color should appear exactly 9 times
        for (int count : colorCounts) {
            if (count != 9) {
                return false;
            }
        }
        
        return true;
    }
    
    private void countColorsOnFace(CubeFace face, int[] colorCounts) {
        for (int i = 0; i < CubeFace.FACE_SIZE; i++) {
            for (int j = 0; j < CubeFace.FACE_SIZE; j++) {
                CubeColor color = face.getSticker(i, j);
                
                if (color.equals(CubeColor.WHITE)) {
                    colorCounts[0]++;
                } else if (color.equals(CubeColor.YELLOW)) {
                    colorCounts[1]++;
                } else if (color.equals(CubeColor.GREEN)) {
                    colorCounts[2]++;
                } else if (color.equals(CubeColor.BLUE)) {
                    colorCounts[3]++;
                } else if (color.equals(CubeColor.RED)) {
                    colorCounts[4]++;
                } else if (color.equals(CubeColor.ORANGE)) {
                    colorCounts[5]++;
                }
            }
        }
    }
}
