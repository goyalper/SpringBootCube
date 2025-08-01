package com.cubemasterpro.service;

import com.cubemasterpro.model.CubeSolution;
import com.cubemasterpro.model.RubiksCube;

/**
 * Service interface for cube solving operations.
 */
public interface CubeSolverService {
    
    /**
     * Solve a Rubik's Cube using the configured algorithm.
     * @param cube The cube to solve.
     * @return The solution containing a sequence of moves.
     */
    CubeSolution solve(RubiksCube cube);
    
    /**
     * Check if a Rubik's Cube is solvable.
     * @param cube The cube to check.
     * @return True if the cube is solvable, false otherwise.
     */
    boolean isSolvable(RubiksCube cube);
    
    /**
     * Apply a solution to a cube.
     * @param cube The cube to apply the solution to.
     * @param solution The solution to apply.
     * @return The cube after applying the solution.
     */
    RubiksCube applySolution(RubiksCube cube, CubeSolution solution);
}
