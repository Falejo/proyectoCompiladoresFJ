package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Asignacion(): Sentencia() {
    var nombre:Token?=null
    var operador:Token?=null
    var expresion:Expresion?=null
    var invocacion:Sentencia?=null

    constructor( nombre:Token, operador:Token?,  expresion: Expresion?):this(){
        this.nombre=nombre
        this.operador=operador
        this.expresion=expresion
    }

    constructor( nombre:Token, operador:Token?,  invocacion: Sentencia):this(){
        this.nombre=nombre
        this.operador=operador
        this.invocacion=invocacion
    }

    override fun toString(): String {
        return "Asignacion(nombre=$nombre, operador=$operador, expresion=$expresion, invocacion=$invocacion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz= TreeItem<String>("Asignacion")
        if(operador != null) {
            raiz.children.add(TreeItem("Nombre: ${nombre!!.lexema}"))
        }
        if(operador != null) {
            raiz.children.add(TreeItem("Operador: ${operador!!.lexema}"))
        }

        if (expresion != null) {
            raiz.children.add(expresion!!.getArbolVisual())
        }else{
            raiz.children.add(invocacion!!.getArbolVisual())
        }

        return raiz
    }

}