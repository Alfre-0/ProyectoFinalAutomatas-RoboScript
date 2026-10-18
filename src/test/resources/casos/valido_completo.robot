# Prueba valida: usa todas las instrucciones del lenguaje una vez
ROBOT 123ABC
INICIO X10,Y05

# Movimientos basicos
AVANZAR 5
GIRAR DERECHA
AVANZAR 10
RETROCEDER 3
GIRAR IZQUIERDA

# Manejo de objetos
LEVANTAR OBJ-0025
MOVER X20,Y15
SOLTAR

# Seguir linea con duracion
SEGUIR LINEA 30s

# Condicional simple
SI OBSTACULO
    GIRAR IZQUIERDA
    AVANZAR 5
FIN

# Condicional con SINO
SI OBSTACULO
    RETROCEDER 2
SINO
    AVANZAR 8
FIN

# Repeticion simple
REPETIR 2
    AVANZAR 10
    GIRAR DERECHA
FIN
