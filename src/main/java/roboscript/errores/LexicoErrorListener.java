package roboscript.errores;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class LexicoErrorListener extends BaseErrorListener {

    private final List<String> erroresDetallados = new ArrayList<>();
    private final List<String> erroresResumen = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        String simbolo = msg;
        if (msg.contains("at: '")) {
            int inicio = msg.indexOf("at: '") + 5;
            int fin = msg.lastIndexOf("'");
            if (inicio < fin) {
                simbolo = msg.substring(inicio, fin);
            }
        }

        String formatoDetallado = String.format(
                "ERROR LÉXICO: Línea: %d, Columna: %d, Elemento no reconocido: %s",
                line, charPositionInLine + 1, simbolo
        );
        erroresDetallados.add(formatoDetallado);

        String formatoResumen = String.format(
                "Línea %d, columna %d: Símbolo no reconocido: %s",
                line, charPositionInLine + 1, simbolo
        );
        erroresResumen.add(formatoResumen);
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
