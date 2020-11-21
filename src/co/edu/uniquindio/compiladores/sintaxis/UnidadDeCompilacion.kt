package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class UnidadDeCompilacion(var nombreClass: Token, var listaFunciones:ArrayList<Funcion>) {
    var erroresSemanticos:ArrayList<Error> = ArrayList()
    override fun toString(): String {
        return "UnidadCompilacion(listaFunciones=$listaFunciones), \n"
    }

    fun getArbolVisual():TreeItem<String>{
        var raiz=TreeItem<String>("Clase: ${nombreClass.lexema}")

        for(f in listaFunciones){

            raiz.children.add(f.getArbolVisual())
        }
        return raiz
    }
}