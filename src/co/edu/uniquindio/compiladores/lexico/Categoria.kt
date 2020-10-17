package co.edu.uniquindio.compiladores.lexico

/**
 * clase que contiene todas las categorias correspondiente a los tokens
 */
enum class Categoria {
    ENTERO, DECIMAL, IDENTIFICADOR, OPERADOR_RELACIONAL, OPERADOR_LOGICO_BINARIO, PARENTESIS_IZQUIERDO, PARENTESIS_DERECHO,
    LLAVE_IZQUIERDA, LLAVE_DERECHA,CORCHETE_IZQUIERDO, CORCHETE_DERECHO, DESCONOCIDO, CARACTER, OPERADOR_INCREMENTO,
    OPERADOR_DECREMENTO, PALABRA_RESERVADA, CADENA, SEPARADOR_COMA, OPERADOR_MATEMATICO, OPERADOR_ASIGNACION,
    OPERADOR_LOGICO_UNARIO, FIN_SENTENCIA, COMENTARIO_LINEA, COMENTARIO_BLOQUE, PUNTO, DOSPUNTOS, ERROR
}