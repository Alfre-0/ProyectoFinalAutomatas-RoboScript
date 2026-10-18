# Analizador RoboScript

Proyecto Final del curso de Autómatas y Lenguajes Formales.  
Universidad Mariano Gálvez de Guatemala  
Facultad de Ingeniería en Sistemas de Información  

### Integrantes
- José Alfredo Garcia Rodríguez 3490-24-13232
- José Ricardo Martínez Garcia 3490-20-19500

---

## Descripción

Analizador léxico y sintáctico desarrollado en Java utilizando ANTLR 4 para el lenguaje RoboScript. El sistema valida la estructura de instrucciones para el control de un robot, detecta errores léxicos y sintácticos, y genera un reporte con el resumen de la ejecución.

## Requisitos

- Java JDK 21 o superior
- Maven 3.9+ o IntelliJ IDEA

## Estructura del proyecto

- `src/main/antlr4/roboscript/RoboScript.g4`: Gramática con las reglas léxicas y sintácticas.
- `src/main/java/roboscript/`:
  - `Main.java`: Clase principal del programa.
  - `errores/`: Listeners para capturar y formatear errores léxicos y sintácticos.
  - `resumen/`: Listener para recorrer el árbol sintáctico y obtener contadores.
  - `util/`: Impresión de la tabla de tokens reconocidos.
- `src/test/resources/casos/`: Archivos de prueba `.robot` (casos válidos e inválidos).

## Compilación y Ejecución

### Desde consola con Maven

Para compilar el proyecto y generar el archivo ejecutable `.jar`:

```bash
mvn clean package
```
*(Si no tienes Maven en las variables de entorno en Windows, puedes usar `.\mvn.bat clean package`)*

Para ejecutar el analizador con un archivo de prueba:

```bash
java -jar target/roboscript.jar src/test/resources/casos/valido_completo.robot
```

### Ejecutar casos con errores:

```bash
java -jar target/roboscript.jar src/test/resources/casos/invalido_varios_errores.robot
```

### Desde IntelliJ IDEA

1. Abrir la carpeta del proyecto en IntelliJ IDEA.
2. Abrir `src/main/java/roboscript/Main.java`.
3. Ejecutar la clase (por defecto analizará un archivo de prueba de la carpeta `casos`).
4. Para probar otro archivo, configurar en *Run > Edit Configurations* el argumento del programa con la ruta del archivo `.robot` deseado.
