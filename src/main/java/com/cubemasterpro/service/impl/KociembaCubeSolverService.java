package com.cubemasterpro.service.impl;

import com.cubemasterpro.model.CubeMove;
import com.cubemasterpro.model.CubeSolution;
import com.cubemasterpro.model.RubiksCube;
import com.cubemasterpro.service.CubeSolverService;
import com.cubemasterpro.util.KociembaAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Implementation of the CubeSolverService using Kociemba's Two-Phase Algorithm.
 */
@Service
public class KociembaCubeSolverService implements CubeSolverService {
    
    @Value("${cube.solver.algorithm:kociemba}")
    private String solverAlgorithm;
    
    @Override
    public CubeSolution solve(RubiksCube cube) {
        // Convert the cube to the Kociemba algorithm's input format
        String cubeString = KociembaAlgorithm.toKociembaString(cube);
        
        // Check if the cube is solvable
        boolean solvable = KociembaAlgorithm.isSolvable(cubeString);
        
        // If the cube is not solvable, return an empty solution
        if (!solvable) {
            CubeSolution solution = new CubeSolution();
            solution.setSolvable(false);
            return solution;
        }
        
        // Solve the cube
        String solutionString = KociembaAlgorithm.solve(cubeString);
        
        // Parse the solution string into a CubeSolution object
        CubeSolution solution = KociembaAlgorithm.parseSolution(solutionString);
        
        return solution;
    }
    
    @Override
    public boolean isSolvable(RubiksCube cube) {
        String cubeString = KociembaAlgorithm.toKociembaString(cube);
        return KociembaAlgorithm.isSolvable(cubeString);
    }
    
    @Override
    public RubiksCube applySolution(RubiksCube cube, CubeSolution solution) {
        // Create a deep copy of the cube to avoid modifying the original
        RubiksCube result = deepCopyCube(cube);
        
        // Apply each move in the solution
        for (CubeMove move : solution.getMoves()) {
            applyMove(result, move);
        }
        
        return result;
    }
    
    /**
     * Create a deep copy of a cube.
     * @param cube The cube to copy.
     * @return A deep copy of the cube.
     */
    private RubiksCube deepCopyCube(RubiksCube cube) {
        // In a real implementation, this would create a deep copy of the cube.
        // For simplicity, we'll just return the original cube for now.
        return cube;
    }
    
    /**
     * Apply a move to a cube.
     * @param cube The cube to modify.
     * @param move The move to apply.
     */
    private void applyMove(RubiksCube cube, CubeMove move) {
        // In a real implementation, this would modify the cube based on the move.
        // For simplicity, we'll leave this empty for now.
    }
}
