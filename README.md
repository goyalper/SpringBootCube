# SpringBootCube: A Modern Rubik's Cube Solver Web App

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-green.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-3.3-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

A professional web application to solve a 3x3 Rubik's Cube using either camera-based face scanning or manual face color selection, with a clean, responsive, and intuitive UI/UX.

## 🎯 Features

- **Camera-Based Cube Scanner**: Use your device webcam to capture 6 cube faces with real-time color detection using OpenCV
- **Manual Face Color Input**: Interactive 3x3 grid UI for each face with color picker
- **Solving Engine**: Implementation of Kociemba's 2-phase algorithm for optimal solutions
- **3D Cube Visualization**: Interactive Three.js cube model with animation of solution steps
- **Elegant UI/UX**: Responsive design using Tailwind CSS with Light/Dark Mode
- **Progressive Web App Support**: Installable on devices with offline capabilities

## 🛠️ Technology Stack

### Backend
- **Spring Boot 3.1.5**
- **Spring Web MVC**
- **Thymeleaf** for HTML templating
- **OpenCV with JavaCV** for real-time cube scanning
- **Kociemba Algorithm** for Rubik's Cube solving
- **Lombok** for reducing boilerplate code

### Frontend
- **Tailwind CSS** for modern styling
- **Alpine.js** for reactive UI interactions
- **Three.js** for 3D cube visualization
- **HTML5/CSS3/JavaScript**

## 📋 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6.3 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/goyalper/SpringBootCube.git
   cd SpringBootCube
   ```

2. Build the project:
   ```bash
   mvn clean package
   ```

3. Run the application:
   ```bash
   java -jar target/springbootcube-0.0.1-SNAPSHOT.jar
   ```

4. Open your browser and navigate to:
   ```
   http://localhost:8080
   ```

### Using Docker

You can also run the application using Docker:

```bash
docker build -t springbootcube .
docker run -p 8080:8080 springbootcube
```

## 📱 Usage

### Camera Scanning
1. Navigate to the "Scan Cube" page
2. Allow camera access when prompted
3. Position your cube so that a face is clearly visible
4. Click the "Capture" button to scan each face
5. Review the detected colors
6. Repeat for all six faces
7. Click "Solve Cube" to get the solution

### Manual Entry
1. Navigate to the "Manual Entry" page
2. Select a face tab (Up, Front, Right, etc.)
3. Click on the color you want to apply
4. Click on the stickers to change their colors
5. Repeat for all six faces
6. Click "Solve Cube" to get the solution

### Understanding the Solution
1. View the 3D animation of the solution
2. Follow the step-by-step instructions displayed
3. Use the playback controls to navigate through steps
4. Refer to the notation guide for understanding moves

## 🧠 Advanced Usage

### Cube Notation
- **F**: Front face clockwise
- **B**: Back face clockwise
- **U**: Up face clockwise
- **D**: Down face clockwise
- **L**: Left face clockwise
- **R**: Right face clockwise
- **'** (prime): Counterclockwise (e.g., F')
- **2**: Double turn (e.g., F2)

### Solving Methods
The application uses Kociemba's Two-Phase Algorithm, which can solve any valid cube state in 20 moves or less (God's Number).

## 📂 Project Structure

```
src/
├── main/
│   ├── java/com/cubemasterpro/
│   │   ├── controller/          # Web and API controllers
│   │   ├── service/             # Business logic services
│   │   ├── model/               # Data models (Cube, Face, etc.)
│   │   ├── util/                # Utilities (Solver, CV tools)
│   ├── resources/
│   │   ├── templates/           # Thymeleaf HTML templates
│   │   ├── static/css/          # CSS stylesheets
│   │   ├── static/js/           # JavaScript files
│   │   └── application.properties
└── test/                       # Unit and integration tests
```

## 🔮 Future Enhancements

- Timer mode and solving race mode
- Support for 4x4, 5x5 cubes
- AR integration to overlay solution in real time
- Connect with smart cubes (e.g., GoCube)
- User accounts to save and share solutions
- Mobile app version using Flutter or React Native

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgements

- [Herbert Kociemba](http://kociemba.org/cube.htm) for the Two-Phase Algorithm
- [OpenCV](https://opencv.org/) for computer vision capabilities
- [Three.js](https://threejs.org/) for 3D visualization
- [Tailwind CSS](https://tailwindcss.com/) for the UI design system
- [Alpine.js](https://alpinejs.dev/) for lightweight interactivity

---

Created with ❤️ by goyalper