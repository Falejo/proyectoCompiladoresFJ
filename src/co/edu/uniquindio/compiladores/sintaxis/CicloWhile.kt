package co.edu.uniquindio.compiladores.sintaxis

import javafx.scene.control.TreeItem

class CicloWhile(var expRelacional: ExpresionRelacional?, var listaSentencias: ArrayList<Sentencia>?):Sentencia() {

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem<String>("Ciclo While")

        if (expRelacional != null){
            raiz.children.add(expRelacional!!.getArbolVisual())
        }


        var raizSentencias= TreeItem("Sentencias")

        if (listaSentencias != null) {
            for (s in listaSentencias!!) {
                raizSentencias.children.add(s.getArbolVisual())
            }
        }

        raiz.children.add(raizSentencias)
        return raiz
    }


    override fun getJavaCode(): String {
        var codigo = "while ("

        if (expRelacional != null){
            codigo += expRelacional!!.getJavaCode()
        }
        codigo += ") { \n"

        if (listaSentencias != null){
            for (sent in listaSentencias!!){
                codigo +=sent.getJavaCode()
            }
        }
        codigo += "} \n"
        return codigo
    }

}