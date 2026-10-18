package roboscript.resumen;

import roboscript.RoboScriptBaseListener;
import roboscript.RoboScriptParser;
import roboscript.errores.SintacticoErrorListener;

public class ResumenListener extends RoboScriptBaseListener {

    private final SintacticoErrorListener errorListener;

    public String robot = "No definido";
    public String posicionInicial = "No definida";
    public int movimientos = 0;
    public int giros = 0;
    public int objetosLevantados = 0;
    public int objetosSoltados = 0;
    public int bloquesSi = 0;
    public int bloquesRepetir = 0;
    public int instruccionesAnalizadas = 0;

    public ResumenListener(SintacticoErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void enterRobotDecl(RoboScriptParser.RobotDeclContext ctx) {
        int linea = ctx.getStart().getLine();

        if (ctx.CODIGO_ROBOT() != null) {
            this.robot = ctx.CODIGO_ROBOT().getText();
        } else if (ctx.CODIGO_ROBOT_INVALIDO() != null) {
            String textoInvalido = ctx.CODIGO_ROBOT_INVALIDO().getText();
            this.robot = textoInvalido;
            errorListener.registrarError(linea, textoInvalido + " no cumple con la expresión regular de CODIGO_ROBOT");
        } else if (ctx.ID() != null) {
            String textoInvalido = ctx.ID().getText();
            this.robot = textoInvalido;
            errorListener.registrarError(linea, textoInvalido + " no cumple con la expresión regular de CODIGO_ROBOT");
        } else {
            errorListener.registrarError(linea, "Se esperaba el código del robot después de ROBOT.");
        }
    }

    @Override
    public void enterInicioDecl(RoboScriptParser.InicioDeclContext ctx) {
        int linea = ctx.getStart().getLine();

        if (ctx.COORDENADA() != null) {
            this.posicionInicial = ctx.COORDENADA().getText();
        } else {
            errorListener.registrarError(linea, "Se esperaba una COORDENADA válida después de INICIO.");
        }
    }

    @Override
    public void enterInstruccion(RoboScriptParser.InstruccionContext ctx) {
        instruccionesAnalizadas++;
    }

    @Override
    public void enterAvanzarInst(RoboScriptParser.AvanzarInstContext ctx) {
        int linea = ctx.getStart().getLine();

        if (ctx.NUMERO() != null) {
            movimientos++;
        } else {
            errorListener.registrarError(linea, "Se esperaba NUMERO después de AVANZAR.");
        }
    }

    @Override
    public void enterRetrocederInst(RoboScriptParser.RetrocederInstContext ctx) {
        int linea = ctx.getStart().getLine();

        if (ctx.NUMERO() != null) {
            movimientos++;
        } else {
            errorListener.registrarError(linea, "Se esperaba NUMERO después de RETROCEDER.");
        }
    }

    @Override
    public void enterGirarInst(RoboScriptParser.GirarInstContext ctx) {
        int linea = ctx.getStart().getLine();

        if (ctx.DERECHA() != null || ctx.IZQUIERDA() != null) {
            giros++;
        } else {
            errorListener.registrarError(linea, "Se esperaba DERECHA o IZQUIERDA después de GIRAR.");
        }
    }

    @Override
    public void enterLevantarInst(RoboScriptParser.LevantarInstContext ctx) {
        int linea = ctx.getStart().getLine();

        if (ctx.CODIGO_OBJETO() != null) {
            objetosLevantados++;
        } else if (ctx.CODIGO_OBJETO_INVALIDO() != null) {
            String textoInvalido = ctx.CODIGO_OBJETO_INVALIDO().getText();
            errorListener.registrarError(linea, textoInvalido + " no cumple con la expresión regular de CODIGO_OBJETO");
        } else if (ctx.ID() != null) {
            String textoInvalido = ctx.ID().getText();
            errorListener.registrarError(linea, textoInvalido + " no cumple con la expresión regular de CODIGO_OBJETO");
        } else {
            errorListener.registrarError(linea, "Se esperaba CODIGO_OBJETO después de LEVANTAR.");
        }
    }

    @Override
    public void enterMoverInst(RoboScriptParser.MoverInstContext ctx) {
        int linea = ctx.getStart().getLine();
        if (ctx.COORDENADA() == null) {
            errorListener.registrarError(linea, "Se esperaba COORDENADA después de MOVER.");
        }
    }

    @Override
    public void enterSoltarInst(RoboScriptParser.SoltarInstContext ctx) {
        objetosSoltados++;
    }

    @Override
    public void enterSeguirLineaInst(RoboScriptParser.SeguirLineaInstContext ctx) {
        int linea = ctx.getStart().getLine();
        if (ctx.DURACION() == null) {
            errorListener.registrarError(linea, "Se esperaba DURACION después de SEGUIR LINEA.");
        }
    }

    @Override
    public void enterSiInst(RoboScriptParser.SiInstContext ctx) {
        bloquesSi++;
    }

    @Override
    public void enterRepetirInst(RoboScriptParser.RepetirInstContext ctx) {
        int linea = ctx.getStart().getLine();
        bloquesRepetir++;

        if (ctx.NUMERO() == null) {
            errorListener.registrarError(linea, "Se esperaba NUMERO después de REPETIR.");
        }
    }
}
