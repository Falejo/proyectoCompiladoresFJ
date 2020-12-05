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

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        if (expresion != null) {
            expresion!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
    }

    override fun getJavaCode(): String {
        var codigo=""
        if (expresion != null) {
            codigo= "return "+expresion!!.getJavaCode()+"; \n"
        }

        return codigo
    }

}