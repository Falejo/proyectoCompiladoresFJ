package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem
import co.edu.uniquindio.compiladores.lexico.Error

class Incremento(var nombre:Token,var operador:Token):Sentencia() {
    override fun toString(): String {
        return "Incremento(nombre=$nombre, operador=$operador)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem("Operacion de Incremento")
        raiz.children.add(TreeItem("nombre: ${nombre.lexema}"))
        return raiz
    }

    override fun getJavaCode(): String {
        return nombre.getJavaCode()+"++;"
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        var simb=tablaSimbolos.buscarSimboloValor(nombre.lexema, ambito)

        if (simb == null){
            erroresSemanticos.add(Error("La variable ${nombre.lexema} no existe dentro del ambito $ambito", nombre.fila, nombre.columna,""))
        }
    }

}