package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Retorno(var expresion: Expresion?):Sentencia() {
    override fun toString(): String {
        return "Retorno(expresion=$expresion)"
    }
    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem("Retorno")
        if (expresion != null) {
            raiz.children.add(expresion!!.getArbolVisual())
        }
        return raiz
    }



    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, listaErrores: ArrayList<Error>, ambito: String) {


        if (expresion != null) {
            expresion!!.analizarSemantica(tablaSimbolos, listaErrores, ambito)
            var tipo = expresion!!.obtenerTipo(tablaSimbolos, ambito, listaErrores)
            if (tipo != null) {
                listaErrores.add(Error("El tipo dato de funcion ($tipo) no coincide con tipo dato de retorno ($ambito)",0,0,"" ))

            }
        }

    }

    fun obtenerTipo(tablaSimbolos: TablaSimbolos, ambito: String, listaErrores: ArrayList<Error>){


    }


    override fun getJavaCode(): String {
        var codigo=""
        if (expresion != null) {
            codigo= "return "+expresion!!.getJavaCode()+"; \n"
        }

        return codigo
    }

}