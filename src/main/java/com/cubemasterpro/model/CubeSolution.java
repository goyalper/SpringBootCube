package com.cubemasterpro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a solution to a Rubik's Cube, containing a sequence of moves.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CubeSolution {
    private List<CubeMove> moves = new ArrayList<>();
    private int totalMoveCount;
    private boolean isSolvable;
    
    /**
     * Adds a move to the solution.
     * @param move The move to add.
     */
    public void addMove(CubeMove move) {
        moves.add(move);
        totalMoveCount++;
    }
    
    /**
     * Optimizes the solution by combining consecutive moves on the same face.
     * For example, R R becomes R2, and R R R becomes R'.
     */
    public void optimize() {
        if (moves.isEmpty()) {
            return;
        }
        
        List<CubeMove> optimizedMoves = new ArrayList<>();
        CubeMove current = moves.get(0);
        int count = 1;
        
        for (int i = 1; i < moves.size(); i++) {
            CubeMove next = moves.get(i);
            
            // If the next move is on the same face as the current one
            if (next.getFace() == current.getFace()) {
                count++;
                
                // Simplify moves (e.g., 4 quarter turns = 0 turns)
                count = count % 4;
                
                // Adjust the current move type based on count
                if (count == 0) {
                    // Skip this move entirely (4 quarter turns = no move)
                    current = null;
                } else if (count == 1) {
                    current.setMoveType(MoveType.CLOCKWISE);
                } else if (count == 2) {
                    current.setMoveType(MoveType.DOUBLE);
                } else if (count == 3) {
                    current.setMoveType(MoveType.COUNTER_CLOCKWISE);
                }
            } else {
                // Different face, add the current move to optimized list if it exists
                if (current != null) {
                    optimizedMoves.add(current);
                }
                
                // Start tracking the new move
                current = next;
                count = 1;
            }
        }
        
        // Add the last move if it exists
        if (current != null) {
            optimizedMoves.add(current);
        }
        
        // Update the moves list and total count
        moves = optimizedMoves;
        totalMoveCount = optimizedMoves.size();
    }
    
    /**
     * Gets the solution as a string in standard cube notation.
     * @return The solution string.
     */
    public String toNotationString() {
        StringBuilder builder = new StringBuilder();
        for (CubeMove move : moves) {
            builder.append(move.toString()).append(" ");
        }
        return builder.toString().trim();
    }
}
