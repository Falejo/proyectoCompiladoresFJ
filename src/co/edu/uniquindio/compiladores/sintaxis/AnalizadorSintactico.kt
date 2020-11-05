package co.edu.uniquindio.compiladores.sintaxis

import co.edu.uniquindio.compiladores.lexico.Categoria
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.lexico.Error

class AnalizadorSintactico (var listaTokens:ArrayList<Token>){

    var posicionActual=0
    var tokenActual = listaTokens[0]
    var listaErrores = ArrayList<Error>()

    /**
     *
     */
    fun obtenerSiguienteToken(){

        posicionActual++

        if(posicionActual < listaTokens.size){
            tokenActual = listaTokens[posicionActual]
        }
    }

    /**
     *
     */
    fun reportarError(mensaje:String){
        listaErrores.add( Error(mensaje,tokenActual.fila, tokenActual.columna))

    }

    /**
     * <UnidadDeCompilacion> ::= <ListaFunciones>
     */
    fun esUnidadDeCompilacion ():UnidadDeCompilacion? {
        val listaFunciones:ArrayList<Funcion> = esListaFunciones()
        return if ( listaFunciones.size > 0 ){
            UnidadDeCompilacion(listaFunciones)
            }else null
        }

    /**
     * <ListaFunciones> ::= <Funcion> [<ListaFunciones>]
     */
    fun esListaFunciones(): ArrayList<Funcion>{

        var listaFunciones = ArrayList<Funcion>()
        var funcion = esFuncion()

        while(funcion!=null ){
            listaFunciones.add(funcion)
            funcion = esFuncion()
        }

        return listaFunciones
    }

    /**
     *<Funcion> ::= def <TipoRetorno> identificador "("[<ListaParametros]")" <BloqueSentencias>
     */
    fun esFuncion(): Funcion?{

        if(tokenActual.categoria == Categoria.PALABRA_RESERVADA && tokenActual.lexema == "~task" ){
            obtenerSiguienteToken()

            var tipoRetorno = esTipoRetorno()

            if(tipoRetorno!= null){
                obtenerSiguienteToken()

                if(tokenActual.categoria == Categoria.IDENTIFICADOR){
                    var nombreFuncion = tokenActual
                    obtenerSiguienteToken()

                    if(tokenActual.categoria == Categoria.PARENTESIS_IZQUIERDO ){
                        obtenerSiguienteToken()

                        var listaParametros = esListaParametros()

                        if( tokenActual.categoria == Categoria.PARENTESIS_DERECHO){
                            obtenerSiguienteToken()

                            var bloqueSentencia = esBloqueSentencias()

                            if(bloqueSentencia!=null){
                                return Funcion(nombreFuncion, tipoRetorno, listaParametros, bloqueSentencia)
                            }else{
                                reportarError( "el bloque de sentencias esta vacio")
                            }

                        }else{
                            reportarError("falta el parentesis derecho")
                        }
                    }else{
                        reportarError("falta el parentesis izquierdo")
                    }
                }else{
                    reportarError("falta el nombre de la funcion")
                }
            }else{
                reportarError("falta el tipo de retorno de la funcion")
            }
        }
        return null
    }

    /**
     * <TipoRetorno> ::= ~ente | ~duplex | ~cade | ~isTF | ~car | ~not
     */
    fun esTipoRetorno():Token?{
        if( tokenActual.categoria == Categoria.PALABRA_RESERVADA){
            if(tokenActual.lexema == "~ente" || tokenActual.lexema == "~duplex" || tokenActual.lexema == "~cade" ||
                    tokenActual.lexema == "~isTF" || tokenActual.lexema == "~car" || tokenActual.lexema == "~not"){
                return  tokenActual
            }
        }
        return null
    }



    /**
     *<ListaParametros> ::= <Parametro>[","<ListaParametros>]
     */
    fun esListaParametros(): ArrayList<Parametro>{
        var listaParametros = ArrayList<Parametro>()
        var parametro = esParametro()

        while(parametro!=null ) {
            listaParametros.add(parametro)
            if (tokenActual.categoria == Categoria.SEPARADOR_COMA) {
                obtenerSiguienteToken()
                parametro = esParametro()
            } else {
                if (tokenActual.categoria != Categoria.PARENTESIS_DERECHO) {
                    reportarError("falta una coma en la lista de parametros")

                }
                break
            }
        }
        return listaParametros

    }

    /**
     *<Parametro> ::= identificador":"<TipoDato>
     */
    fun esParametro():Parametro? {

        if (tokenActual.categoria == Categoria.IDENTIFICADOR) {
            val nombre = tokenActual
            obtenerSiguienteToken()

            if (tokenActual.categoria == Categoria.DOSPUNTOS) {
                obtenerSiguienteToken()

                val tipoDato = esTipoDato()

                if(tipoDato != null){
                    obtenerSiguienteToken()

                    return Parametro(nombre, tipoDato)
                }else {
                    reportarError("falta el tipo de retorno en el parametro")
                }
                }else{
                    reportarError("falta el operador :")
                }
            }
            return  null
        }

        /**
         * <TipoRetorno> ::= ~ente | ~duplex | ~cade | ~isTF | ~car |
         */
        fun esTipoDato():Token?{
            if( tokenActual.categoria == Categoria.PALABRA_RESERVADA){
                if(tokenActual.lexema == "~ente" || tokenActual.lexema == "~duplex" || tokenActual.lexema == "~cade" ||
                        tokenActual.lexema == "~isTF" || tokenActual.lexema == "~car" ){
                    return  tokenActual
                }
            }
            return null
        }


    /**
     *<BloqueSentencia> ::= "{"[<ListaSentencias>]"}"
     */
    fun esBloqueSentencias():ArrayList<Sentencia>?{

        if(tokenActual.categoria == Categoria.LLAVE_IZQUIERDA){
            obtenerSiguienteToken()

            var listaSentencias = esListaSentencias()

            if(tokenActual.categoria == Categoria.LLAVE_DERECHA){
                obtenerSiguienteToken()

                return listaSentencias
            }else {
                reportarError("falta la llave derecha de la funcion")
            }
            }else{

                reportarError("falta la llave izquierda de la funcion")

            }

        return null
    }

    /**
     * * BNF para reresentar una sentencia
     * <ListaSentencias>::=  <Sentecia> [<ListaSentencias> ]
     */
    fun esListaSentencias():ArrayList<Sentencia>?{
        var listaSentencias =ArrayList<Sentencia>()
        var sentecia=esSentencia()

        while (sentecia !=null){
            listaSentencias.add(sentecia)
            obtenerSiguienteToken()
            sentecia=esSentencia()
        }
        return listaSentencias
    }

    fun esSentencia():Sentencia?{
        return null
    }


    /**
     * <Sentencia> ::= <DesicionSimple>  | <Ciclo> |  <Impresion> | <Leer>  | <Asignacion> | <DeclaracionVariable> | TipoRetorno   |  <Incremento> | <Decremento> | <InvocacionFuncion>
     */
    /**fun esSentencia():Sentencia? {
        var sentencia: Sentencia? = esCicloForEach()
        if (sentencia != null) {
            return sentencia
        }
        sentencia = esCicloWhile()
        if (sentencia != null) {
            return sentencia
        }
        sentencia = esAsignacion()
        if (sentencia != null){
            return sentencia
        }
        sentencia=esDecicionSimple()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esDeclaracion()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esInvocacionFuncion()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esIncremento()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esDecremento()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esTRyCatch()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esInterrupcion()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esRetorno()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esImpresion()
        if (sentencia != null) {
            return sentencia
        }
        sentencia=esLeer()
        if (sentencia != null) {
            return sentencia
        }
        return null
    }*/

    /**
     *
     */

    
}