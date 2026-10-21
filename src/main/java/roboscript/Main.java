package roboscript;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import roboscript.errores.LexicoErrorListener;
import roboscript.errores.SintacticoErrorListener;
import roboscript.resumen.ResumenListener;
import roboscript.util.TablaTokens;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String rutaArchivo;
        if (args.length > 0) {
            rutaArchivo = args[0];
        } else {
            rutaArchivo = "src/test/resources/casos/valido_completo.robot";
        }

        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.err.println("Error: El archivo '" + rutaArchivo + "' no existe.");
            return;
        }

        try {
            procesarArchivo(archivo);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void procesarArchivo(File archivo) throws IOException {
        CharStream input = CharStreams.fromPath(archivo.toPath());

        // Implementando el Lexer
        
        RoboScriptLexer lexer = new RoboScriptLexer(input);
        lexer.removeErrorListeners(); // Para evitar que salgan los errores en ingles
        LexicoErrorListener errorLexico = new LexicoErrorListener();
        lexer.addErrorListener(errorLexico);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        // Implementando el Parser

        int totalTokens = 0;
        for (Token t : tokens.getTokens()) {
            if (t.getType() != Token.EOF) {
                totalTokens++;
            }
        }

        TablaTokens.imprimir(tokens);


        RoboScriptParser parser = new RoboScriptParser(tokens);
        parser.removeErrorListeners();
        SintacticoErrorListener errorSintactico = new SintacticoErrorListener();
        parser.addErrorListener(errorSintactico);

        ParseTree arbol = parser.programa();

        ResumenListener resumen = new ResumenListener(errorSintactico);
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(resumen, arbol);

        boolean esValido = !errorLexico.tieneErrores() && !errorSintactico.tieneErrores();

        imprimirReporte(archivo.getName(), esValido, totalTokens, errorLexico, errorSintactico, resumen);
    }

    private static void imprimirReporte(String nombreArchivo,
            boolean esValido,
            int totalTokens,
            LexicoErrorListener errorLexico,
            SintacticoErrorListener errorSintactico,
            ResumenListener resumen) {

        System.out.println("=========================================");
        System.out.println("          ANALISIS DE ROBOSCRIPT");
        System.out.println("=========================================");
        System.out.println("Archivo: " + nombreArchivo);

        if (esValido) {
            System.out.println("RESULTADO: ARCHIVO VALIDO");
            System.out.println("-----------------------------------------");
            System.out.println("ANALISIS LEXICO");
            System.out.println("-----------------------------------------");
            System.out.println("Tokens reconocidos: " + totalTokens);
            System.out.println("Errores léxicos: 0");
            System.out.println("-----------------------------------------");
            System.out.println("ANALISIS SINTACTICO");
            System.out.println("-----------------------------------------");
            System.out.println("Instrucciones analizadas: " + resumen.instruccionesAnalizadas);
            System.out.println("Errores sintácticos: 0");
            System.out.println("-----------------------------------------");
            System.out.println("RESUMEN DEL PROGRAMA");
            System.out.println("-----------------------------------------");
            System.out.println("Robot: " + resumen.robot);
            System.out.println("Posición inicial: " + resumen.posicionInicial);
            System.out.println("Movimientos: " + resumen.movimientos);
            System.out.println("Giros: " + resumen.giros);
            System.out.println("Objetos levantados: " + resumen.objetosLevantados);
            System.out.println("Objetos soltados: " + resumen.objetosSoltados);
            System.out.println("Bloques SI: " + resumen.bloquesSi);
            System.out.println("Bloques REPETIR: " + resumen.bloquesRepetir);
            System.out.println("=========================================");
            System.out.println("        \u2713 ARCHIVO SINTACTICAMENTE VALIDO");
            System.out.println("=========================================");

        } else {
            System.out.println();
            System.out.println("RESULTADO: ARCHIVO NO VALIDO");

            if (errorLexico.tieneErrores()) {
                System.out.println("-----------------------------------------");
                System.out.println("ERRORES LEXICOS");
                System.out.println("-----------------------------------------");
                for (String err : errorLexico.getErroresResumen()) {
                    System.out.println(err);
                }
            }

            if (errorSintactico.tieneErrores()) {
                System.out.println("-----------------------------------------");
                System.out.println("ERRORES SINTACTICOS");
                System.out.println("-----------------------------------------");
                for (String err : errorSintactico.getErroresResumen()) {
                    System.out.println(err);
                }
            }

            System.out.println("-----------------------------------------");
            System.out.println("RESUMEN");
            System.out.println("-----------------------------------------");
            System.out.println("Tokens reconocidos: " + totalTokens);
            System.out.println("Errores léxicos: " + errorLexico.cantidadErrores());
            System.out.println("Errores sintácticos: " + errorSintactico.cantidadErrores());
            System.out.println("=========================================");
        }
    }
}
