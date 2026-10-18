# Error sintactico: bloques REPETIR y SI sin su FIN de cierre
ROBOT 555BOT
INICIO X00,Y00

# Este REPETIR nunca se cierra con FIN, el parser detecta error
REPETIR 4
    AVANZAR 10
    GIRAR DERECHA
    SI OBSTACULO
        RETROCEDER 2
# Faltan dos FIN aqui (uno para SI, uno para REPETIR)
