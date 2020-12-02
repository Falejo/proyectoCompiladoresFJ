package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Invocacion(var nombre:Token, var listaArgumentos:ArrayList<Expresion>):Sentencia() {
    override fun toString(): String {
        return "Invocacion(nombre=$nombre, listaArgumentos=$listaArgumentos)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem<String>("Invocacion Funcion")
        raiz.children.add(TreeItem("Nombre Funcion: ${nombre.lexema}"))
        var raizSentencias= TreeItem("Argumentos")
        for(arg in listaArgumentos){
            raizSentencias.children.add(arg.getArbolVisual())
        }
        raiz.children.add(raizSentencias)

        return raiz
    }
}