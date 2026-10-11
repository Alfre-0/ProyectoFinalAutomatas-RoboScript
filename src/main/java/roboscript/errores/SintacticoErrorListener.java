package roboscript.errores;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class SintacticoErrorListener extends BaseErrorListener {

    private final List<String> erroresDetallados = new ArrayList<>();
    private final List<String> erroresResumen = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        String mensajeEspanol = traducirMensaje(msg, offendingSymbol);
        erroresDetallados.add(String.format("ERROR SINTÁCTICO: Línea: %d, %s", line, mensajeEspanol));
        erroresResumen.add(String.format("Línea %d: %s", line, mensajeEspanol));
    }

    public void registrarError(int linea, String mensaje) {
        erroresDetallados.add(String.format("ERROR SINTÁCTICO: Línea: %d, %s", linea, mensaje));
        erroresResumen.add(String.format("Línea %d: %s", linea, mensaje));
    }

    private String traducirMensaje(String msg, Object offendingSymbol) {
        String textoToken = "";
        if (offendingSymbol instanceof Token) {
            textoToken = ((Token) offendingSymbol).getText();
        }

        if (msg.contains("missing 'FIN'") || msg.contains("expecting 'FIN'") || msg.contains("FIN")) {
            return "Se esperaba FIN.";
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

    public boolean tieneErrores() {
        return !erroresDetallados.isEmpty();
    }

    public int cantidadErrores() {
        return erroresDetallados.size();
    }

    public List<String> getErroresDetallados() {
        return erroresDetallados;
    }

    public List<String> getErroresResumen() {
        return erroresResumen;
    }
}
