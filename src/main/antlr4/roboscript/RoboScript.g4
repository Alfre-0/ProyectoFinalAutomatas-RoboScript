grammar RoboScript;

// Reglas del parser
programa: robotDecl inicioDecl instruccion* EOF;

robotDecl: ROBOT (CODIGO_ROBOT | CODIGO_ROBOT_INVALIDO | ID)?;

inicioDecl: INICIO COORDENADA?;

instruccion:
	avanzarInst
	| retrocederInst
	| girarInst
	| seguirLineaInst
	| levantarInst
	| moverInst
	| soltarInst
	| repetirInst
	| siInst;

avanzarInst: AVANZAR NUMERO?;

retrocederInst: RETROCEDER NUMERO?;

girarInst: GIRAR (DERECHA | IZQUIERDA | direccionInvalida)?;

direccionInvalida: ID;

seguirLineaInst: SEGUIR LINEA DURACION?;

levantarInst:
	LEVANTAR (CODIGO_OBJETO | CODIGO_OBJETO_INVALIDO | ID)?;

moverInst: MOVER COORDENADA?;

soltarInst: SOLTAR;

repetirInst: REPETIR NUMERO? instruccion* FIN;

siInst: SI OBSTACULO instruccion* (SINO instruccion*)? FIN;

// Reglas del lexer TOKENS
ROBOT: 'ROBOT';
INICIO: 'INICIO';
AVANZAR: 'AVANZAR';
RETROCEDER: 'RETROCEDER';
GIRAR: 'GIRAR';
DERECHA: 'DERECHA';
IZQUIERDA: 'IZQUIERDA';
SEGUIR: 'SEGUIR';
LINEA: 'LINEA';
LEVANTAR: 'LEVANTAR';
MOVER: 'MOVER';
SOLTAR: 'SOLTAR';
REPETIR: 'REPETIR';
SI: 'SI';
SINO: 'SINO';
FIN: 'FIN';
OBSTACULO: 'OBSTACULO';
VELOCIDAD: 'VELOCIDAD';

CODIGO_ROBOT: [0-9][0-9][0-9][A-Z][A-Z][A-Z];
CODIGO_ZONA: 'Z-' [0-9][0-9];
CODIGO_OBJETO: 'OBJ-' [0-9][0-9][0-9][0-9];
COORDENADA: 'X' [0-9]+ ',' 'Y' [0-9]+;
PORCENTAJE: [0-9]+ '%';
DURACION: [0-9]+ ('s' | 'm');

// Tokens para formatos incorrectos
CODIGO_OBJETO_INVALIDO: 'OBJ-' [0-9]+;
CODIGO_ROBOT_INVALIDO: [0-9]+ [A-Za-z]+;
CODIGO_ZONA_INVALIDO: 'Z-' [0-9]+;

NUMERO: [0-9]+;
ID: [a-zA-Z_] [a-zA-Z_0-9]*;
REL_OP: '==' | '!=' | '<=' | '>=' | '<' | '>';

ESPACIOBLANCO: [ \t\r\n]+ -> skip;
COMENTARIO: '#' ~[\r\n]* -> skip;