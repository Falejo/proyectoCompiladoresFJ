package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class CicloFor(var lista:Token, var item:Token, var tipoDato:Token, var listaSentencias:ArrayList<Sentencia>?): Sentencia(){
    override fun toString(): String {
        return "CicloForEach(lista=$lista, item=$item, tipoDato=$tipoDato, listaSentencias=$listaSentencias)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem<String>("Ciclo For")

        raiz.children.add(TreeItem("Lista: ${lista.lexema}"))
        raiz.children.add(TreeItem("Item: ${item.lexema}"))
        if(tipoDato != null) {
            raiz.children.add(TreeItem("Tipo: ${tipoDato!!.lexema}"))
        }

        var raizSentencias= TreeItem("Sentencias")

        if(listaSentencias != null) {
            for (s in listaSentencias!!) {
                raizSentencias.children.add(s.getArbolVisual())
            }
        }

        raiz.children.add(raizSentencias)

        return raiz
    }

}