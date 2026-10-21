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

### Opción 1: Desde IntelliJ IDEA

1. **Abrir el proyecto:**
   - Abre IntelliJ IDEA y selecciona **Open**.
   - Elige la carpeta del proyecto `ProyectoAutomatas-roboscript`. IntelliJ detectará automáticamente el archivo `pom.xml` y descargará las dependencias de Maven.

2. **Ejecutar el programa:**
   - En el explorador de archivos del proyecto, navega a `src/main/java/roboscript/Main.java`.
   - Haz clic derecho sobre el archivo `Main.java` (o sobre el icono verde ▶ al lado de `public static void main`) y selecciona **Run 'Main.main()'**.
   - Por defecto, analizará el archivo `src/test/resources/casos/valido_completo.robot` y mostrará la tabla de tokens y el resumen en la consola inferior.

3. **Probar otros archivos de prueba (.robot):**
   - En la barra superior de IntelliJ, haz clic en el menú desplegable de ejecución (al lado del botón de Play verde ▶) y selecciona **Edit Configurations...**
   - En el campo **Program arguments** (Argumentos del programa), escribe la ruta del archivo que desees evaluar, por ejemplo:
     ```text
     src/test/resources/casos/invalido_varios_errores.robot
     ```
   - Haz clic en **Apply** y luego en **Run**.
   
3.1 O simplemente elija el archivo a probar y de clic derecho, seleccionar Copy Path, y seleccionar Path From Content Root y pegar en el main en la variable rutaArchivo
  

---

### Opción 2: Desde la consola con Maven

También puedes compilar y empaquetar el proyecto en un `.jar` ejecutable:

1. **Compilar y generar el archivo JAR:**
   ```bash
   mvn clean package
   ```
   *(Esto generará el archivo `roboscript.jar` dentro de la carpeta `target/`)*

2. **Ejecutar un caso válido:**
   ```bash
   java -jar target/roboscript.jar src/test/resources/casos/valido_completo.robot
   ```

3. **Ejecutar un caso con errores:**
   ```bash
   java -jar target/roboscript.jar src/test/resources/casos/invalido_varios_errores.robot
   ```

