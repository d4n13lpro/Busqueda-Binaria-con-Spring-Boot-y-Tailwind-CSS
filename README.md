# Búsqueda Binaria con Spring Boot y Tailwind CSS

## 🛠️ Stack 

![Java](https://img.shields.io/badge/Java-21-%23ED8B00?logo=openjdk&logoColor=white)  
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.6-%236DB33F?logo=springboot&logoColor=white)  
![Spring MVC](https://img.shields.io/badge/Spring_MVC-6.1-%236DB33F?logo=spring&logoColor=white)  
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-%23005F0F?logo=thymeleaf&logoColor=white)  
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-3.3-%2338B2AC?logo=tailwindcss&logoColor=white)  
![Maven](https://img.shields.io/badge/Maven-3.9-%23C71A36?logo=apachemaven&logoColor=white)


Una aplicación web moderna que demuestra el algoritmo de búsqueda binaria implementado en Java 21 con Spring Boot 3.2.6 y una interfaz de usuario estilizada con Tailwind CSS.



## 🚀 Características

- **Búsqueda Binaria Optimizada**: Implementación eficiente del algoritmo de búsqueda binaria
- **Interfaz Moderna**: UI responsiva desarrollada con Tailwind CSS
- **Medición de Rendimiento**: Incluye warm-up de JVM y medición precisa del tiempo de ejecución
- **Validación de Datos**: Manejo robusto de errores y validación de entrada
- **Arquitectura Limpia**: Separación clara entre capas de servicio y controlador



## 📋 Prerrequisitos

- Java 21 o superior
- Maven 3.6 o superior
- Navegador web moderno

## 🔧 Instalación y Ejecución

### Preparar el entorno
1. **Crear una carpeta local** para el proyecto:
   ```bash
   mkdir mi-proyecto-java
   cd mi-proyecto-java
   ```

2. **Abrir Git Bash** en la carpeta creada:
    - Click derecho en la carpeta → "Git Bash Here"
    - O desde terminal: navegar a la carpeta y ejecutar `bash`

### Clonar el repositorio
```bash
git clone https://github.com/d4n13lpro/Busqueda-Binaria-con-Spring-Boot-y-Tailwind-CSS.git
cd Busqueda-Binaria-con-Spring-Boot-y-Tailwind-CSS
```

### Compilar el proyecto
```bash
mvn clean install
```

### Ejecutar la aplicación
```bash
mvn spring-boot:run
```

### Acceder a la aplicación
Abre tu navegador y visita: `http://localhost:8080`

## 📸 Capturas de Pantalla

### Interfaz Principal
<img src="https://github.com/user-attachments/assets/4ea49228-747e-4bc0-8f01-3bc29057d01e" width="500" alt="Interfaz Principal">

*Formulario de entrada para el array y el valor objetivo*

### Resultado de Búsqueda Exitosa
<img src="https://github.com/user-attachments/assets/65b3375a-2a18-4021-a423-10a296084fe3" width="500" alt="Resultado Exitoso">

*Visualización del resultado cuando el elemento es encontrado*

### Resultado de Búsqueda Sin Coincidencias
<img src="https://github.com/user-attachments/assets/fdcb6272-890a-4115-8b5c-09ffac4f05a1" width="500" alt="Resultado No Encontrado">

*Mensaje mostrado cuando el elemento no existe en el array*

### Manejo de Errores
<img src="https://github.com/user-attachments/assets/6f62c533-078c-4a1e-afcb-199c946aef43" width="500" alt="Manejo de Errores">

*Validación y manejo de errores de entrada*

## 📖 Uso

1. **Ingresar el Array**: Introduce números separados por comas en el primer campo
    - Ejemplo: `2, 5, 1, 10, 12, 8`

2. **Definir el Objetivo**: Especifica el número que deseas buscar
    - Ejemplo: `12`

3. **Ejecutar la Búsqueda**: Haz clic en el botón "Buscar"

4. **Ver Resultados**: La aplicación mostrará:
    - El array ordenado
    - El índice donde se encontró el elemento (o -1 si no existe)
    - El tiempo de ejecución en microsegundos

## 🏗️ Arquitectura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/daniel/busquedaBinaria/
│   │       ├── controller/
│   │       │   └── SearchController.java
│   │       └── service/
│   │           └── SearchService.java
│   └── resources/
│       └── templates/
│           └── index.html
└── pom.xml
```

### Componentes Principales

#### SearchService
- **Responsabilidad**: Lógica de negocio para la búsqueda binaria
- **Características**:
    - Implementación del algoritmo de búsqueda binaria
    - Warm-up de JVM para mediciones precisas
    - Medición de rendimiento en microsegundos
    - Clase interna `SearchResult` para encapsular resultados

#### SearchController
- **Responsabilidad**: Manejo de peticiones HTTP y renderizado de vistas
- **Endpoints**:
    - `GET /`: Muestra el formulario principal
    - `POST /`: Procesa la búsqueda y muestra resultados
- **Características**:
    - Validación de entrada
    - Manejo de errores
    - Inyección de dependencias

#### index.html
- **Responsabilidad**: Interfaz de usuario
- **Características**:
    - Formulario responsivo con Tailwind CSS
    - Visualización de resultados y errores
    - Preservación de datos de entrada

## 🔍 Algoritmo de Búsqueda Binaria

El algoritmo implementado sigue estos pasos:

1. **Ordenación**: El array se ordena usando `Arrays.sort()` (Dual-Pivot Quicksort)
2. **Búsqueda**: Se aplica búsqueda binaria con complejidad O(log n)
3. **Warm-up**: Se ejecutan 10,000 iteraciones para optimizar la JVM
4. **Medición**: Se mide el tiempo de ejecución con precisión de nanosegundos

### Complejidad Temporal
- **Ordenación**: O(n log n)
- **Búsqueda Binaria**: O(log n)
- **Total**: O(n log n)

## 🎨 Interfaz de Usuario

La interfaz utiliza Tailwind CSS para proporcionar:

- **Diseño Responsivo**: Adaptable a diferentes tamaños de pantalla
- **Estilo Moderno**: Colores y tipografía contemporáneos
- **Feedback Visual**: Indicadores claros de éxito y error
- **Usabilidad**: Formularios intuitivos y fáciles de usar

## 🧪 Ejemplo de Uso

**Entrada:**
- Array: `5, 2, 8, 1, 9, 3`
- Objetivo: `8`

**Salida:**
- Array ordenado: `[1, 2, 3, 5, 8, 9]`
- Resultado: El valor 8 está en el índice: **4**
- Tiempo de ejecución: 15.32 µs

## 📊 Características de Rendimiento

- **Warm-up de JVM**: 10,000 iteraciones para optimización
- **Medición Precisa**: Tiempo en microsegundos
- **Algoritmo Eficiente**: Búsqueda binaria O(log n)
- **Ordenación Optimizada**: Dual-Pivot Quicksort de Java

## 🔧 Configuración

### application.properties
```properties
# Puerto del servidor (opcional)
server.port=8080

# Configuración de Thymeleaf
spring.thymeleaf.cache=false
spring.thymeleaf.enabled=true
spring.thymeleaf.encoding=UTF-8
```

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Añadir nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👤 Autor

**Daniel Acuña** - Desarrollador Principal

## 🙏 Agradecimientos

- Spring Boot Team por el excelente framework
- Tailwind CSS por el sistema de diseño
- Comunidad Java por las mejores prácticas

---

⭐ Si este proyecto te fue útil, ¡dale una estrella en GitHub!