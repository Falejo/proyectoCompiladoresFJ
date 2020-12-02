package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Interrupcion(var interrupcion: Token?):Sentencia() {
    override fun toString(): String {
        return "Interrupcion(interrupcion=$interrupcion)"
    }
    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Interrupcion")

        return raiz
    }
}