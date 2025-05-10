# Snake Game

# Estructura (Clean Architecture)
```
com.lmar.snakegame
│
├── app             // MainActivity
├── core            // Utilidades comunes (extensiones, constantes, etc.)
├── domain
│   ├── model       // Clases de modelo puro (Snake, Food, Level, etc.)
│   └── usecase     // Casos de uso (MoverSerpiente, IniciarJuego, etc.)
├── data
│   └── repository  // Implementación de repositorios si usas persistencia
├── di              // Inyección de dependencias (Hilt)
├── presentation
│   ├── menu        // Menú principal
│   ├── game        // Pantalla del juego
│   └── navigation  // Navegación con Navigation Compose
└── Application.kt
```