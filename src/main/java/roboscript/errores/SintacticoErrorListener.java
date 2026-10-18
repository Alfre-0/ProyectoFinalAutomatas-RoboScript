package roboscript.errores;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SintacticoErrorListener extends BaseErrorListener {

    // Cada error guarda su linea y el orden en que se registro. Asi la lista final
    // se entrega ordenada por linea, sin importar si el error lo detecto el parser
    // (syntaxError) o el recorrido posterior del arbol (registrarError).
    private static final class Entrada {
        final int linea;
        final int orden;
        final String detallado;
        final String resumen;

        Entrada(int linea, int orden, String mensaje) {
            this.linea = linea;
            this.orden = orden;
            this.detallado = String.format("ERROR SINTÁCTICO: Línea: %d, %s", linea, mensaje);
            this.resumen = String.format("Línea %d: %s", linea, mensaje);
        }
    }

    private final List<Entrada> errores = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        registrarError(line, traducirMensaje(msg, offendingSymbol));
    }

    public void registrarError(int linea, String mensaje) {
        errores.add(new Entrada(linea, errores.size(), mensaje));
    }

    private String traducirMensaje(String msg, Object offendingSymbol) {
        String textoToken = "";
        if (offendingSymbol instanceof Token) {
            textoToken = ((Token) offendingSymbol).getText();
        }

        // Solo se analiza la parte "expecting ..." del mensaje de ANTLR. Asi un FIN
        // que aparece como token inesperado no se confunde con un FIN que falta.
        String esperados = msg.contains("expecting") ? msg.substring(msg.indexOf("expecting")) : msg;

        if (msg.startsWith("missing 'FIN'") || esperados.contains("'FIN'")) {
            return "Se esperaba FIN.";
        }

        if ("FIN".equals(textoToken)) {
            return "FIN inesperado: no hay un bloque SI o REPETIR abierto.";
        }

        if (msg.contains("NUMERO")) {
            return "Se esperaba NUMERO.";
        }

        if (msg.contains("DERECHA") || msg.contains("IZQUIERDA")) {
            return "Se esperaba DERECHA o IZQUIERDA después de GIRAR.";
        }

        if (msg.contains("COORDENADA")) {
            return "Se esperaba una COORDENADA válida (ejemplo: X10,Y05).";
        }

        if (msg.contains("CODIGO_ROBOT")) {
            return "Se esperaba el código del robot (formato: ###LLL).";
        }

        if (!textoToken.isEmpty() && !textoToken.equals("<EOF>")) {
            return "Instrucción o token no esperado: '" + textoToken + "'.";
        }

        return "Error en la estructura de la instrucción.";
    }

    private List<Entrada> ordenados() {
        List<Entrada> copia = new ArrayList<>(errores);
        copia.sort(Comparator.<Entrada>comparingInt(x -> x.linea).thenComparingInt(x -> x.orden));
        return copia;
    }

    public boolean tieneErrores() {
        return !errores.isEmpty();
    }

    public int cantidadErrores() {
        return errores.size();
    }

    public List<String> getErroresDetallados() {
        List<String> lista = new ArrayList<>();
        for (Entrada x : ordenados()) {
            lista.add(x.detallado);
        }
        return lista;
    }

    public List<String> getErroresResumen() {
        List<String> lista = new ArrayList<>();
        for (Entrada x : ordenados()) {
            lista.add(x.resumen);
        }
        return lista;
    }
}