package com.cubemasterpro.controller;

import com.cubemasterpro.model.*;
import com.cubemasterpro.service.CubeScannerService;
import com.cubemasterpro.service.CubeSolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for the home page and general navigation.
 */
@Controller
public class HomeController {
    
    @Autowired
    private CubeScannerService scannerService;
    
    @Autowired
    private CubeSolverService solverService;
    
    /**
     * Display the home page.
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Rubik's Cube Solver");
        return "home";
    }
    
    /**
     * Display the about page.
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Rubik's Cube Solver");
        return "about";
    }
    
    /**
     * Display the scan cube page.
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @GetMapping("/scan")
    public String scanCube(Model model) {
        model.addAttribute("title", "Scan Cube");
        model.addAttribute("faces", FacePosition.values());
        return "scan";
    }
    
    /**
     * Display the manual entry page.
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @GetMapping("/manual")
    public String manualEntry(Model model) {
        model.addAttribute("title", "Manual Entry");
        model.addAttribute("faces", FacePosition.values());
        model.addAttribute("colors", new CubeColor[]{
            CubeColor.WHITE, CubeColor.YELLOW, CubeColor.GREEN, 
            CubeColor.BLUE, CubeColor.RED, CubeColor.ORANGE
        });
        
        // Add a new or existing cube to the model
        if (!model.containsAttribute("cube")) {
            model.addAttribute("cube", RubiksCube.createSolvedCube());
        }
        
        return "manual";
    }
    
    /**
     * Process the manual entry form submission.
     * @param cubeData The cube data from the form.
     * @param redirectAttributes The redirect attributes.
     * @return A redirect to the solution page.
     */
    @PostMapping("/manual/submit")
    public String submitManualEntry(@RequestParam Map<String, String> cubeData, 
                                   RedirectAttributes redirectAttributes) {
        // Build a cube from the form data
        RubiksCube cube = buildCubeFromFormData(cubeData);
        
        // Check if the cube is valid and solvable
        if (!cube.isValidState()) {
            redirectAttributes.addFlashAttribute("error", "Invalid cube state. Each color must appear exactly 9 times.");
            redirectAttributes.addFlashAttribute("cube", cube);
            return "redirect:/manual";
        }
        
        if (!solverService.isSolvable(cube)) {
            redirectAttributes.addFlashAttribute("error", "This cube configuration is not solvable.");
            redirectAttributes.addFlashAttribute("cube", cube);
            return "redirect:/manual";
        }
        
        // Solve the cube
        CubeSolution solution = solverService.solve(cube);
        
        // Add the cube and solution to the session
        redirectAttributes.addFlashAttribute("cube", cube);
        redirectAttributes.addFlashAttribute("solution", solution);
        
        return "redirect:/solution";
    }
    
    /**
     * Process the cube scan form submission.
     * @param faceImage The face image from the form.
     * @param position The position of the face.
     * @param redirectAttributes The redirect attributes.
     * @return A redirect to the scan page.
     */
    @PostMapping("/scan/upload")
    public String uploadScan(@RequestParam("faceImage") MultipartFile faceImage,
                            @RequestParam("position") FacePosition position,
                            RedirectAttributes redirectAttributes) {
        try {
            // Scan the face from the image
            CubeFace face = scannerService.scanFaceFromImage(faceImage, position);
            
            // Get the cube from the session or create a new one
            RubiksCube cube;
            if (redirectAttributes.getFlashAttributes().containsKey("cube")) {
                cube = (RubiksCube) redirectAttributes.getFlashAttributes().get("cube");
            } else {
                cube = RubiksCube.createSolvedCube();
            }
            
            // Update the face in the cube
            cube.setFaceByPosition(position, face);
            
            // Add the updated cube to the session
            redirectAttributes.addFlashAttribute("cube", cube);
            redirectAttributes.addFlashAttribute("message", "Face " + position + " scanned successfully!");
            
            // Check if all faces have been scanned
            boolean allFacesScanned = true;
            for (FacePosition pos : FacePosition.values()) {
                if (cube.getFaceByPosition(pos) == null) {
                    allFacesScanned = false;
                    break;
                }
            }
            
            if (allFacesScanned) {
                redirectAttributes.addFlashAttribute("allFacesScanned", true);
            }
            
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error scanning face: " + e.getMessage());
        }
        
        return "redirect:/scan";
    }
    
    /**
     * Display the solution page.
     * @param model The model to add attributes to.
     * @return The name of the view to render.
     */
    @GetMapping("/solution")
    public String solution(Model model) {
        model.addAttribute("title", "Solution");
        
        // If there's no cube or solution in the model, redirect to home
        if (!model.containsAttribute("cube") || !model.containsAttribute("solution")) {
            return "redirect:/";
        }
        
        return "solution";
    }
    
    /**
     * Build a Rubik's Cube from form data.
     * @param formData The form data.
     * @return The built cube.
     */
    private RubiksCube buildCubeFromFormData(Map<String, String> formData) {
        RubiksCube cube = new RubiksCube();
        
        // Process each face position
        for (FacePosition position : FacePosition.values()) {
            CubeFace face = new CubeFace();
            face.setPosition(position);
            
            CubeColor[][] stickers = new CubeColor[CubeFace.FACE_SIZE][CubeFace.FACE_SIZE];
            
            // Process each sticker in the face
            for (int i = 0; i < CubeFace.FACE_SIZE; i++) {
                for (int j = 0; j < CubeFace.FACE_SIZE; j++) {
                    String key = position.name() + "_" + i + "_" + j;
                    String colorName = formData.get(key);
                    
                    CubeColor color = CubeColor.fromName(colorName);
                    stickers[i][j] = color;
                }
            }
            
            face.setStickers(stickers);
            cube.setFaceByPosition(position, face);
        }
        
        return cube;
    }
}
