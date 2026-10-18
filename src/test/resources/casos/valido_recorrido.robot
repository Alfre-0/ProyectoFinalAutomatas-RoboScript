# Recorrido en cuadrado: avanza por los 4 lados y recoge un objeto al final
ROBOT 999XYZ
INICIO X01,Y01

# Primer lado
AVANZAR 20
GIRAR DERECHA

# Segundo lado
AVANZAR 15
GIRAR DERECHA

# Tercer lado
AVANZAR 20
GIRAR DERECHA

# Cuarto lado, retrocede un poco antes de recoger
AVANZAR 15
RETROCEDER 2

# Recoge y suelta objeto al terminar el recorrido
LEVANTAR OBJ-0042
MOVER X10,Y10
SOLTAR
