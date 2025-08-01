package com.cubemasterpro.model;

/**
 * Represents a single face of a Rubik's cube with its stickers.
 */
public class CubeFace {
    public static final int FACE_SIZE = 3;
    
    private FacePosition position;
    private CubeColor[][] stickers;
    
    public CubeFace() {
        this.stickers = new CubeColor[FACE_SIZE][FACE_SIZE];
    }
    
    public FacePosition getPosition() {
        return position;
    }
    
    public void setPosition(FacePosition position) {
        this.position = position;
    }
    
    public CubeColor[][] getStickers() {
        return stickers;
    }
    
    public void setStickers(CubeColor[][] stickers) {
        this.stickers = stickers;
    }
    
    public CubeColor getSticker(int row, int col) {
        if (row < 0 || row >= FACE_SIZE || col < 0 || col >= FACE_SIZE) {
            throw new IndexOutOfBoundsException("Invalid sticker coordinates: " + row + ", " + col);
        }
        return stickers[row][col];
    }
    
    public void setSticker(int row, int col, CubeColor color) {
        if (row < 0 || row >= FACE_SIZE || col < 0 || col >= FACE_SIZE) {
            throw new IndexOutOfBoundsException("Invalid sticker coordinates: " + row + ", " + col);
        }
        stickers[row][col] = color;
    }
    
    /**
     * Creates a new face with all stickers of the same color.
     * @param position The position of the face on the cube.
     * @param color The color of all stickers on the face.
     * @return A new face with uniform color.
     */
    public static CubeFace createUniformFace(FacePosition position, CubeColor color) {
        CubeFace face = new CubeFace();
        face.setPosition(position);
        
        CubeColor[][] stickers = new CubeColor[FACE_SIZE][FACE_SIZE];
        for (int i = 0; i < FACE_SIZE; i++) {
            for (int j = 0; j < FACE_SIZE; j++) {
                stickers[i][j] = color;
            }
        }
        
        face.setStickers(stickers);
        return face;
    }
    
    /**
     * Rotate the face 90 degrees clockwise.
     */
    public void rotateClockwise() {
        CubeColor[][] rotated = new CubeColor[FACE_SIZE][FACE_SIZE];
        
        for (int i = 0; i < FACE_SIZE; i++) {
            for (int j = 0; j < FACE_SIZE; j++) {
                rotated[j][FACE_SIZE - 1 - i] = stickers[i][j];
            }
        }
        
        stickers = rotated;
    }
    
    /**
     * Rotate the face 90 degrees counter-clockwise.
     */
    public void rotateCounterClockwise() {
        CubeColor[][] rotated = new CubeColor[FACE_SIZE][FACE_SIZE];
        
        for (int i = 0; i < FACE_SIZE; i++) {
            for (int j = 0; j < FACE_SIZE; j++) {
                rotated[FACE_SIZE - 1 - j][i] = stickers[i][j];
            }
        }
        
        stickers = rotated;
    }
}
