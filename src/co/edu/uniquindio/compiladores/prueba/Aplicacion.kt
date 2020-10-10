package co.edu.uniquindio.compiladores.prueba

import co.edu.uniquindio.compiladores.lexico.AnalizadorLexico

fun main(){
   val lexico = AnalizadorLexico("34.34\ncasa\n34585")
    lexico.analizar()
    print(lexico.listaTokens)


}
