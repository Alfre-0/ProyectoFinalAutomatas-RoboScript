# Prueba extrema con errores: múltiples errores lexicos y sintacticos entremezclados
# El objetivo es estresar el recuperador de errores del parser
# Codigo de robot mal formado: 99RX no tiene el formato correcto (NNNLll)
ROBOT 99RX
INICIO X05,Y05

# ---- BLOQUE 1: errores lexicos con simbolos ilegales ----
AVANZAR 10 @
GIRAR DERECHA #este comentario es valido, el # ignora el resto
RETROCEDER 5 $
MOVER X10,Y10 %

# ---- BLOQUE 2: direcciones que no existen en la gramatica ----
GIRAR NORTE
GIRAR SUR
GIRAR ARRIBA
GIRAR ABAJO

# ---- BLOQUE 3: codigos de objeto con formato incorrecto ----
# OBJ-5 y OBJ-99 no tienen los 4 digitos requeridos por CODIGO_OBJETO
LEVANTAR OBJ-5
LEVANTAR OBJ-99
LEVANTAR OBJ-999

# ---- BLOQUE 4: bloques anidados sin su FIN de cierre ----
REPETIR 3
    AVANZAR 5
    SI OBSTACULO
        GIRAR DERECHA
        REPETIR 2
            AVANZAR 2
            # Este REPETIR interno nunca se cierra

# ---- BLOQUE 5: mas simbolos ilegales intercalados ----
AVANZAR 7 !
RETROCEDER 3 ^
SEGUIR LINEA 10s

# ---- BLOQUE 6: SI sin FIN ----
SI OBSTACULO
    AVANZAR 4
    GIRAR IZQUIERDA
SINO
    RETROCEDER 1
# Falta FIN de este SI

# ---- BLOQUE 7: instruccion valida al final para ver si el parser recupera ----
AVANZAR 1
GIRAR DERECHA
