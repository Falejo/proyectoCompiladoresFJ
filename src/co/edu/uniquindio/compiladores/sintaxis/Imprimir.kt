package co.edu.uniquindio.compiladores.sintaxis

import javafx.scene.control.TreeItem

class Imprimir(var expresion: Expresion):Sentencia() {
    override fun toString(): String {
        return "Imprimir(expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem<String>("Imprimir")
        raiz.children.add(expresion.getArbolVisual())
        return raiz
    }

    override fun getJavaCode(): String {
        return "JOptionPane.showMessageDialog(null,"+expresion.getJavaCode()+"); \n"
    }

}