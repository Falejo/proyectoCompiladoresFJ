package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Leer(var variable:Token):Sentencia() {
    override fun toString(): String {
        return "Leer(variable=$variable)"
    }
    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem<String>("Leer")
        raiz.children.add(TreeItem("Variable: ${variable.lexema}"))
        return raiz
    }


}