# Error sintactico: GIRAR con direccion desconocida (ARRIBA no es valido)
# AVANZAR y RETROCEDER sin numero son validos segun la gramatica (el numero es opcional)
ROBOT 123ROB
INICIO X10,Y05


# Esto genera error semantico/lexico: ARRIBA no es DERECHA ni IZQUIERDA
GIRAR ARRIBA

# Instruccion valida despues del error
AVANZAR 5
