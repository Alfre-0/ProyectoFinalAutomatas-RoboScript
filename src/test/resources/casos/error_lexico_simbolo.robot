# Error lexico: simbolos ilegales mezclados con instrucciones validas
ROBOT 123ROB
INICIO X10,Y05

# El @ y $ son caracteres que el lexer no reconoce
AVANZAR 10 @
GIRAR DERECHA $
RETROCEDER 5 &

# Mas instrucciones validas despues del error, el parser intenta continuar
AVANZAR 3
SOLTAR
