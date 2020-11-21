package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Arreglo(var nombre:Token, var tipoArreglo:Token,var listaExpresiones:ArrayList<Expresion>?):Sentencia() {
    override fun toString(): String {
        return "Arreglo(nombre=$nombre, tipoArreglo=$tipoArreglo, listaExpresiones=$listaExpresiones)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem<String>("Arreglo")
        raiz.children.add(TreeItem("Nombre: ${nombre.lexema}"))
        raiz.children.add(TreeItem("Tipo: ${tipoArreglo.lexema}"))
        var raizExp = TreeItem<String>("Argumentos")

        if (listaExpresiones != null) {
            for (exp in listaExpresiones!!) {

                raizExp.children.add(exp!!.getArbolVisual())
            }
            raiz.children.add(raizExp)
        }

        return raiz
    }
}