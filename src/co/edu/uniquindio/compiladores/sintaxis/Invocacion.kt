package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem
import co.edu.uniquindio.compiladores.lexico.Error
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


    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        var listaTipoArgs=obtenerTiposArgumentos(tablaSimbolos,ambito,erroresSemanticos)
        var s =tablaSimbolos.buscarSimboloFuncion(nombre.lexema,listaTipoArgs)

        if (s== null){
            erroresSemanticos.add(Error("La funcion ${nombre.lexema}  $listaTipoArgs no existe", nombre.fila,nombre.columna,""))
        }
    }

    fun obtenerTiposArgumentos(tablaSimbolos: TablaSimbolos, ambito: String, listaErrores: ArrayList<Error>): ArrayList<String>{
        var listaArgs =ArrayList<String>()

        for ( arg in listaArgumentos){
            listaArgs.add(arg.obtenerTipo(tablaSimbolos,ambito,listaErrores ))
        }
        return listaArgs
    }


    override fun getJavaCode(): String {
        var codigo ="\t \t"+ nombre.getJavaCode()+" ("

        if (listaArgumentos.isNotEmpty()) {
            for (arg in listaArgumentos) {
                codigo += arg.getJavaCode() + ", "
            }
            codigo = codigo.substring(0, codigo.length - 2)
        }
        codigo+= "); \n"
        return codigo
    }

}