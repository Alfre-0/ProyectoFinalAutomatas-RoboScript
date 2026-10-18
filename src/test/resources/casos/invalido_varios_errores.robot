# Error mixto: combina errores lexicos y sintacticos en el mismo archivo
# Codigo de robot con formato incorrecto (12ROB no cumple el patron NNNLll)
ROBOT 12ROB
INICIO X10,Y05

# Error lexico: @ no es un caracter valido del lenguaje
MOVER X5,Y5 @

# Error de direccion: ARRIBA no existe en la gramatica
GIRAR ARRIBA

# Codigo de objeto invalido: OBJ-25 no tiene los 4 digitos requeridos
LEVANTAR OBJ-25

# Bloques sin su FIN correspondiente (error sintactico)
SI OBSTACULO
    AVANZAR 10

REPETIR 3
    GIRAR DERECHA
