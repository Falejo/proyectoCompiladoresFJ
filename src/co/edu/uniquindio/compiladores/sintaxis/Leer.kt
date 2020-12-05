package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.Simbolo

class Leer(var variable:Token):Sentencia() {
    private var simbolo: Simbolo?=null

    override fun toString(): String {
        return "Leer(variable=$variable)"
    }
    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem<String>("Leer")
        raiz.children.add(TreeItem("Variable: ${variable.lexema}"))
        return raiz
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        simbolo=tablaSimbolos.buscarSimboloValor(variable.lexema, ambito)

        if (simbolo == null){
            erroresSemanticos.add(Error("La variable ${variable.lexema} no existe dentro del ambito $ambito", variable.fila, variable.columna,""))
        }
    }

    override fun getJavaCode(): String {
        var codigo=""

        if (simbolo != null) {
            when (simbolo!!.tipo) {
                "int" -> codigo= variable.getJavaCode() + "= Integer.parseInt(JOptionPane.showInputDialog(null, \"Escriba un valor\")); \n"
                "demimal" -> codigo= variable.getJavaCode() + "= Double.parseDouble(JOptionPane.showInputDialog(null, \"Escriba un valor\")); \n"
                "bool" -> codigo= variable.getJavaCode() + "= Boolean.parseBoolean(JOptionPane.showInputDialog(null, \"Escriba un valor\")); \n"
                else -> { // Note the block
                    codigo=  variable.getJavaCode() + "= JOptionPane.showInputDialog(null, \"Escriba un valor\"); \n"
                }
            }
        }
        return codigo
    }


}