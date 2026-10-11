package roboscript.util;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import roboscript.RoboScriptLexer;

public class TablaTokens {

    public static void imprimir(CommonTokenStream tokens) {
        System.out.println("=========================================================");
        System.out.println("                 TABLA DE TOKENS LÉXICOS");
        System.out.println("=========================================================");
        System.out.printf("%-18s %-24s %-8s %-8s%n", "Lexema", "Token", "Línea", "Columna");
        System.out.println("---------------------------------------------------------");

        for (Token t : tokens.getTokens()) {
            if (t.getType() == Token.EOF) {
                continue;
            }

            String lexema = t.getText();
            String nombreSimbolico = RoboScriptLexer.VOCABULARY.getSymbolicName(t.getType());
            String tokenNombre = (nombreSimbolico != null) ? "T_" + nombreSimbolico : "T_DESCONOCIDO";

            int linea = t.getLine();
            int columna = t.getCharPositionInLine() + 1;

            System.out.printf("%-18s %-24s %-8d %-8d%n", lexema, tokenNombre, linea, columna);
        }
        System.out.println("=========================================================\n");
    }
}
