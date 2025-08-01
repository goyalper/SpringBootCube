package com.cubemasterpro.model;

/**
 * Represents a single move on a Rubik's Cube.
 */
public class CubeMove {
    private FacePosition face;
    private MoveType moveType;
    
    public CubeMove() {
    }

    public CubeMove(FacePosition face, MoveType moveType) {
        this.face = face;
        this.moveType = moveType;
    }

    public FacePosition getFace() {
        return face;
    }

    public void setFace(FacePosition face) {
        this.face = face;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }
    
    /**
     * Returns the string representation of this move in standard cube notation.
     * @return The move in standard notation.
     */
    @Override
    public String toString() {
        String faceStr;
        
        switch (face) {
            case UP:    faceStr = "U"; break;
            case DOWN:  faceStr = "D"; break;
            case FRONT: faceStr = "F"; break;
            case BACK:  faceStr = "B"; break;
            case RIGHT: faceStr = "R"; break;
            case LEFT:  faceStr = "L"; break;
            default:    throw new IllegalStateException("Unknown face: " + face);
        }
        
        switch (moveType) {
            case CLOCKWISE:           return faceStr;
            case COUNTER_CLOCKWISE:   return faceStr + "'";
            case DOUBLE:              return faceStr + "2";
            default:                  throw new IllegalStateException("Unknown move type: " + moveType);
        }
    }
    
    /**
     * Parse a cube move from standard notation.
     * @param notation The notation string (e.g., "R", "R'", "R2").
     * @return A CubeMove object representing the specified move.
     */
    public static CubeMove fromNotation(String notation) {
        if (notation == null || notation.isEmpty()) {
            throw new IllegalArgumentException("Notation cannot be empty");
        }
        
        // Get the face
        char faceChar = notation.charAt(0);
        FacePosition face;
        
        switch (faceChar) {
            case 'U': face = FacePosition.UP; break;
            case 'D': face = FacePosition.DOWN; break;
            case 'F': face = FacePosition.FRONT; break;
            case 'B': face = FacePosition.BACK; break;
            case 'R': face = FacePosition.RIGHT; break;
            case 'L': face = FacePosition.LEFT; break;
            default:  throw new IllegalArgumentException("Invalid face in notation: " + faceChar);
        }
        
        // Get the move type
        MoveType moveType;
        
        if (notation.length() == 1) {
            moveType = MoveType.CLOCKWISE;
        } else if (notation.length() == 2) {
            char modifier = notation.charAt(1);
            
            if (modifier == '\'') {
                moveType = MoveType.COUNTER_CLOCKWISE;
            } else if (modifier == '2') {
                moveType = MoveType.DOUBLE;
            } else {
                throw new IllegalArgumentException("Invalid move modifier in notation: " + modifier);
            }
        } else {
            throw new IllegalArgumentException("Invalid notation format: " + notation);
        }
        
        return new CubeMove(face, moveType);
    }
}
