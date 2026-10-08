grammar RoboScript;

// ============ REGLAS DEL PARSER ============

programa
    : robotDecl inicioDecl instruccion* EOF
    ;

robotDecl
    : ROBOT CODIGO_ROBOT
    ;

inicioDecl
    : INICIO COORDENADA
    ;

instruccion
    : avanzarInst
    | retrocederInst
    | girarInst
    | seguirLineaInst
    | levantarInst
    | moverInst
    | soltarInst
    | repetirInst
    | siInst
    ;

avanzarInst     : AVANZAR NUMERO ;
retrocederInst  : RETROCEDER NUMERO ;
girarInst       : GIRAR (DERECHA | IZQUIERDA) ;
seguirLineaInst : SEGUIR LINEA DURACION ;
levantarInst    : LEVANTAR CODIGO_OBJETO ;
moverInst       : MOVER COORDENADA ;
soltarInst      : SOLTAR ;

repetirInst
    : REPETIR NUMERO instruccion* FIN
    ;

siInst
    : SI OBSTACULO instruccion* (SINO instruccion*)? FIN
    ;

// ============ REGLAS DEL LEXER ============
// Las palabras reservadas van antes que CODIGO_ROBOT e ID.

ROBOT      : 'ROBOT' ;
INICIO     : 'INICIO' ;
AVANZAR    : 'AVANZAR' ;
RETROCEDER : 'RETROCEDER' ;
GIRAR      : 'GIRAR' ;
DERECHA    : 'DERECHA' ;
IZQUIERDA  : 'IZQUIERDA' ;
SEGUIR     : 'SEGUIR' ;
LINEA      : 'LINEA' ;
LEVANTAR   : 'LEVANTAR' ;
MOVER      : 'MOVER' ;
SOLTAR     : 'SOLTAR' ;
REPETIR    : 'REPETIR' ;
SI         : 'SI' ;
SINO       : 'SINO' ;
FIN        : 'FIN' ;
OBSTACULO  : 'OBSTACULO' ;
VELOCIDAD  : 'VELOCIDAD' ;

CODIGO_ROBOT  : [0-9][0-9][0-9][A-Z][A-Z][A-Z] ;
CODIGO_ZONA   : 'Z-' [0-9][0-9] ;
CODIGO_OBJETO : 'OBJ-' [0-9][0-9][0-9][0-9] ;
COORDENADA    : 'X' [0-9]+ ',' 'Y' [0-9]+ ;
PORCENTAJE    : [0-9]+ '%' ;
DURACION      : [0-9]+ ('s' | 'm') ;

NUMERO : [0-9]+ ;
ID     : [a-zA-Z_] [a-zA-Z_0-9]* ;
REL_OP : '==' | '!=' | '<=' | '>=' | '<' | '>' ;

WS         : [ \t\r\n]+ -> skip ;
COMENTARIO : '#' ~[\r\n]* -> skip ;