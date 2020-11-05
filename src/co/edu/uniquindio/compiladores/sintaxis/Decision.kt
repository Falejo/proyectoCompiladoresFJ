package co.edu.uniquindio.compiladores.sintaxis

import javafx.scene.control.TreeItem

class Decision(var expresionLogica:ExpresionLogica, var listaSentencia:ArrayList<Sentencia>, var listaSentenciaElse: ArrayList<Sentencia>?): Sentencia() {

    override fun toString(): String {
        return "Decision(expresionLogica=$expresionLogica, listaSentencia=$listaSentencia, listaSentenciaElse=$listaSentenciaElse)"
    }

    override fun getArbolVisual(): TreeItem<String> {

        var raiz = TreeItem("Decisión")

        var condicion = TreeItem("Condición")
        raiz.children.add(expresionLogica.getArbolVisual())

        raiz.children.add(condicion)

        var raizTrue = TreeItem("Sentencias Verdaderas")

        for (s in listaSentencia){
            raizTrue.children.add(s.getArbolVisual())
        }

        raiz.children.add(raizTrue)

        if(listaSentenciaElse!=null) {

        var raizFalse = TreeItem("Sentencias falsas")

        for (s in listaSentenciaElse!!){
            raizFalse.children.add(s.getArbolVisual())
         }
            raiz.children.add(raizFalse)
        }

        return raiz
    }
}