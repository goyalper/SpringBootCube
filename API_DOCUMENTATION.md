# SpringBootCube - Complete Java End-to-End Application

A modern, comprehensive Java web application for solving Rubik's cubes using advanced algorithms and computer vision. This project demonstrates enterprise-level Java development with Spring Boot, featuring a complete frontend, REST APIs, and advanced cube-solving algorithms.

## 🚀 Features

### Backend (Spring Boot)
- **RESTful API**: Complete REST endpoints for cube operations
- **Layered Architecture**: Controllers, Services, Repositories pattern
- **Computer Vision**: OpenCV integration for cube scanning
- **Advanced Algorithms**: Kociemba Two-Phase Algorithm implementation
- **Validation**: Comprehensive input validation and error handling
- **Testing**: Unit and integration tests with MockMvc

### Frontend (Modern Web)
- **Responsive Design**: Mobile-first responsive UI with Tailwind CSS
- **Interactive Components**: Alpine.js for dynamic interactions
- **3D Visualization**: Three.js integration for cube rendering
- **Multiple Input Methods**: Camera scanning, manual entry, file upload
- **Dark/Light Theme**: Automatic theme detection with manual toggle
- **Progressive Enhancement**: Works without JavaScript enabled

### Business Logic
- **Cube State Management**: Complete cube representation and validation
- **Solving Algorithms**: Multiple solving methods including Kociemba
- **Move Optimization**: Algorithm to reduce solution steps
- **Configuration Validation**: Ensures cube states are mathematically valid

## 🛠️ Technology Stack

### Backend Technologies
- **Java 17**: Modern Java with latest features
- **Spring Boot 3.1.5**: Enterprise framework with auto-configuration
- **Spring Web MVC**: RESTful web services
- **Thymeleaf**: Server-side templating engine
- **OpenCV (JavaCV)**: Computer vision for cube scanning
- **Maven**: Dependency management and build automation
- **JUnit 5**: Modern testing framework

### Frontend Technologies
- **HTML5/CSS3**: Modern markup and styling
- **Tailwind CSS**: Utility-first CSS framework
- **Alpine.js**: Minimal reactive framework
- **Three.js**: 3D graphics and animations
- **Responsive Design**: Mobile-first approach

### Development Tools
- **Spring Boot DevTools**: Hot reloading for development
- **Lombok**: Reduces boilerplate code
- **Git**: Version control
- **Docker**: Containerization support

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/cubemasterpro/
│   │   ├── SpringBootCubeApplication.java    # Main application entry point
│   │   ├── controller/                       # REST controllers and web controllers
│   │   │   ├── HomeController.java          # Web page controllers
│   │   │   ├── CubeController.java          # Cube scanning APIs
│   │   │   ├── SolveController.java         # Cube solving APIs
│   │   │   └── ApiController.java           # General API endpoints
│   │   ├── service/                         # Business logic layer
│   │   │   ├── CubeScannerService.java      # Cube scanning interface
│   │   │   ├── CubeSolverService.java       # Cube solving interface
│   │   │   └── impl/                        # Service implementations
│   │   ├── model/                           # Domain models
│   │   │   ├── RubiksCube.java             # Main cube representation
│   │   │   ├── CubeFace.java               # Individual face model
│   │   │   ├── CubeColor.java              # Color enumeration
│   │   │   ├── CubeMove.java               # Move representation
│   │   │   └── CubeSolution.java           # Solution model
│   │   └── util/                           # Utility classes
│   │       └── KociembaAlgorithm.java      # Solving algorithm
│   └── resources/
│       ├── templates/                       # Thymeleaf HTML templates
│       │   ├── layout.html                 # Base layout template
│       │   ├── home.html                   # Landing page
│       │   ├── scan.html                   # Cube scanning page
│       │   ├── manual.html                 # Manual entry page
│       │   ├── about.html                  # Information page
│       │   └── solution.html               # Solution display
│       ├── static/                         # Static web assets
│       │   ├── css/                        # Custom stylesheets
│       │   └── js/                         # JavaScript files
│       └── application.properties          # Configuration
└── test/                                   # Test suites
    └── java/com/cubemasterpro/
        ├── SpringBootCubeApplicationTests.java
        └── controller/
            └── CubeControllerTest.java
```

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Git

### Running the Application

1. **Clone the repository**:
   ```bash
   git clone https://github.com/goyalper/SpringBootCube.git
   cd SpringBootCube
   ```

2. **Build and run**:
   ```bash
   mvn spring-boot:run
   ```

3. **Access the application**:
   - Web Interface: http://localhost:8080
   - API Documentation: http://localhost:8080/api/cube/test

### Using Docker

1. **Build the Docker image**:
   ```bash
   docker build -t springbootcube .
   ```

2. **Run the container**:
   ```bash
   docker run -p 8080:8080 springbootcube
   ```

## 📚 API Documentation

### Cube Scanning APIs

#### Test Service
```http
GET /api/cube/test
```
Returns sample cube faces and validates service functionality.

#### Scan Face from Image
```http
POST /api/cube/scan
Content-Type: multipart/form-data

image: [image file]
position: [FRONT|BACK|LEFT|RIGHT|UP|DOWN]
```

#### Capture Face from Webcam
```http
GET /api/cube/capture/{position}
```

### Cube Solving APIs

#### Solve Cube
```http
POST /api/solve/cube
Content-Type: application/json

{
  "upFace": { "position": "UP", "stickers": [...] },
  "downFace": { "position": "DOWN", "stickers": [...] },
  // ... other faces
}
```

#### Get Sample Cube
```http
GET /api/solve/sample
```
Returns a sample solved cube configuration and solution.

## 🧪 Testing

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=CubeControllerTest
```

### Run with coverage:
```bash
mvn test jacoco:report
```

## 🔧 Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080

# Thymeleaf Configuration
spring.thymeleaf.cache=false

# File Upload Limits
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Cube Solver Configuration
cube.solver.algorithm=kociemba
cube.scan.timeout=30
```

## 🎯 Key Features Demonstrated

### Enterprise Java Development
- **Dependency Injection**: Spring's IoC container
- **AOP (Aspect-Oriented Programming)**: Cross-cutting concerns
- **Data Validation**: Bean validation with custom validators
- **Exception Handling**: Global exception handling with @ControllerAdvice
- **Testing Strategy**: Unit tests, integration tests, MockMvc

### Modern Web Development
- **RESTful API Design**: HTTP verbs, status codes, JSON responses
- **Progressive Enhancement**: Works with and without JavaScript
- **Responsive Design**: Mobile-first approach
- **Accessibility**: ARIA labels, semantic HTML, keyboard navigation
- **Performance**: Optimized assets, lazy loading, caching

### Algorithm Implementation
- **Complex Logic**: Rubik's cube solving algorithms
- **Data Structures**: Efficient cube representation
- **Optimization**: Move sequence optimization
- **Mathematical Validation**: Cube state validation

### DevOps and Deployment
- **Containerization**: Docker support
- **Configuration Management**: Externalized configuration
- **Monitoring**: Actuator endpoints for health checks
- **Logging**: Structured logging with different levels

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [Herbert Kociemba](http://kociemba.org/cube.htm) for the Two-Phase Algorithm
- [OpenCV](https://opencv.org/) for computer vision capabilities
- [Spring Boot](https://spring.io/projects/spring-boot) for the excellent framework
- [Tailwind CSS](https://tailwindcss.com/) for the utility-first CSS framework

## 🔮 Future Enhancements

- [ ] **Real-time Solving**: WebSocket integration for live solving
- [ ] **User Accounts**: Authentication and personalized experiences
- [ ] **Advanced Analytics**: Solve time tracking and statistics
- [ ] **Mobile App**: React Native or Flutter companion app
- [ ] **AR Integration**: Augmented reality cube overlay
- [ ] **Tournament Mode**: Competitive solving features
- [ ] **Smart Cube Integration**: Connect with Bluetooth cubes
- [ ] **Machine Learning**: Improved computer vision with ML models

---

**Built with ❤️ using Java, Spring Boot, and modern web technologies.**