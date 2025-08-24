package com.cubemasterpro.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubemasterpro.model.CubeSolution;
import com.cubemasterpro.model.RubiksCube;
import com.cubemasterpro.service.CubeSolverService;

/**
 * REST API Controller for Cube Solving Operations.
 */
@RestController
@RequestMapping("/api/solve")
public class SolveController {
    
    @Autowired
    private CubeSolverService cubeSolverService;
    
    /**
     * Solve a Rubik's cube.
     * @param cube The cube configuration to solve.
     * @return The solution steps.
     */
    @PostMapping("/cube")
    public ResponseEntity<CubeSolution> solveCube(@RequestBody RubiksCube cube) {
        // Validate cube configuration
        if (!cube.isValidState()) {
            return ResponseEntity.badRequest().body(null);
        }
        
        // Check if solvable
        if (!cubeSolverService.isSolvable(cube)) {
            CubeSolution errorSolution = new CubeSolution();
            errorSolution.setSolvable(false);
            return ResponseEntity.ok(errorSolution);
        }
        
        // Solve the cube
        CubeSolution solution = cubeSolverService.solve(cube);
        return ResponseEntity.ok(solution);
    }
    
    /**
     * Get a sample solved cube configuration.
     * @return A solved Rubik's cube.
     */
    @GetMapping("/sample")
    public ResponseEntity<Map<String, Object>> getSampleCube() {
        RubiksCube solvedCube = RubiksCube.createSolvedCube();
        CubeSolution solution = cubeSolverService.solve(solvedCube);
        
        return ResponseEntity.ok(Map.of(
            "cube", solvedCube,
            "solution", solution,
            "message", "This is a sample solved cube configuration"
        ));
    }
}