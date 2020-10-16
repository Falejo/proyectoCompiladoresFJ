package co.edu.uniquindio.compiladores.prueba

import co.edu.uniquindio.compiladores.lexico.AnalizadorLexico

fun main(){
   val lexico = AnalizadorLexico("34.34\ncasa\n34585\n~hola$\"hola\"")
    lexico.analizar()
    print(lexico.listaTokens)


}
