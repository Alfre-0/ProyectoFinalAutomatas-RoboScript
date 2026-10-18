# Cobertura de estructuras: anidamiento de SI y REPETIR
ROBOT 789ABC
INICIO X50,Y50

# Movimientos con todas las direcciones
AVANZAR 12
RETROCEDER 3
GIRAR IZQUIERDA
SEGUIR LINEA 45s

# Ciclo de recogida de objetos
LEVANTAR OBJ-9999
MOVER X80,Y90
SOLTAR

# SI con SINO completo
SI OBSTACULO
    AVANZAR 2
    GIRAR DERECHA
SINO
    AVANZAR 8
    GIRAR IZQUIERDA
FIN

# REPETIR con SI anidado adentro
REPETIR 3
    AVANZAR 5
    SI OBSTACULO
        GIRAR DERECHA
        RETROCEDER 1
    SINO
        AVANZAR 3
    FIN
    GIRAR IZQUIERDA
FIN

# Segunda ronda de movimientos despues del ciclo
SEGUIR LINEA 10s
AVANZAR 4
