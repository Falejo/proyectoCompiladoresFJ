package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class ExpresionCadena(var cadena: Token, var expresion:Expresion?):Expresion() {
    override fun toString(): String {
        return "ExpresionCadena(cadena=$cadena, expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem("Expesion Cadena")
        raiz.children.add(TreeItem("Cadena: ${cadena.lexema}"))
        if (expresion != null){
            raiz.children.addAll(expresion!!.getArbolVisual())
        }
        return raiz
    }

}