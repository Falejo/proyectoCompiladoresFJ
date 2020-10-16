package co.edu.uniquindio.compiladores.lexico

class AnalizadorLexico(var codigoFuente:String) {


    var posicionActual = 0
    var caracterActual = codigoFuente[0]
    var listaTokens = ArrayList<Token>()
    var finCodigo = 0.toChar()
    var filaActual = 0
    var columnaActual = 0
    val operadoresAritmeticos = ArrayList<Char>()
    var listaErrores = ArrayList<Error>()


    fun almacenarToken(lexema:String, categoria: Categoria, fila:Int, columna:Int) = listaTokens.add(Token(lexema, categoria, fila, columna))

    fun hacerBT(posicionInicial:Int, filaInicial:Int, columnaInicial:Int){
        posicionActual = posicionInicial
        filaActual = filaInicial
        columnaActual = filaInicial
        caracterActual = codigoFuente[posicionActual]
    }

    fun analizar(){

        operadoresAritmeticos.add('+')
        operadoresAritmeticos.add('-')
        operadoresAritmeticos.add('*')
        operadoresAritmeticos.add('/')


        while(caracterActual != finCodigo){

            if (caracterActual == ' ' || caracterActual == '\t' || caracterActual == '\n'){
                obtenerSiguienteCaracter()
                continue
            }

            if (esEntero()) continue
            if (esDecimal()) continue
            if (esIdentificador()) continue
            almacenarToken(""+caracterActual, Categoria.DESCONOCIDO, filaActual, columnaActual)
            obtenerSiguienteCaracter()

        }
    }

    fun esDecimal():Boolean{
        if (caracterActual == '.' || caracterActual.isDigit()){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual

            if (caracterActual == '.'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if (caracterActual.isDigit()){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()

                }
            }else{
                lexema += caracterActual
                obtenerSiguienteCaracter()

                while (caracterActual.isDigit()){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                }
                if (caracterActual == '.') {
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                }

            }
            while (caracterActual.isDigit()){
                lexema += caracterActual
                obtenerSiguienteCaracter()
            }
            almacenarToken(lexema, Categoria.DECIMAL, filaInicial, columnaInicial)
            return true

        }
        return false
    }

    fun esEntero():Boolean{
        if (caracterActual.isDigit()){

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()

            while (caracterActual.isDigit()){
                lexema += caracterActual
                obtenerSiguienteCaracter()
            }

            if (caracterActual == '.'){
                hacerBT(posicionInicial, filaInicial, columnaInicial)
                return false
            }

            almacenarToken(lexema, Categoria.ENTERO, filaInicial, columnaInicial)
            return true
        }
        return false
    }


    /**
     * este metodo permite la validacón del token Identificadores
     */

    fun esIdentificador():Boolean {

        if(caracterActual=='~'){
            var lexema = ""
            var filaInicial=filaActual
            var columnaInicial=columnaActual


            lexema+=caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual.isLetter()){
                do{
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                }while (caracterActual != '~')
            }else{
                lexema+=caracterActual
                obtenerSiguienteCaracter()
                almacenarToken(lexema, Categoria.DESCONOCIDO, filaInicial,columnaInicial);
                return true
            }
            lexema+=caracterActual
            obtenerSiguienteCaracter()
            almacenarToken(lexema, Categoria.IDENTIFICADOR, filaInicial, columnaInicial);

            return true
        }
        return false
    }

    /**
     * Este metodo permite construir el token de caracter
     */
    fun esCaracter():Boolean{
        if(caracterActual+""=="'"){
            var lexema =""
            var filaInicial=filaActual
            var columnaInicial=columnaActual
            var posicionInicial=posicionActual

            lexema+=caracterActual
            obtenerSiguienteCaracter()

            if(caracterActual+""=="'"){
                lexema+=caracterActual
                obtenerSiguienteCaracter()

                if(caracterActual+""=="'") {
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }

                lexema+=caracterActual
                obtenerSiguienteCaracter()

                if(caracterActual+""=="'") {
                    lexema += caracterActual
                    obtenerSiguienteCaracter()

                    if(caracterActual+""=="'") {
                        lexema += caracterActual
                        almacenarToken(lexema, Categoria.CARACTER, filaInicial, columnaInicial);
                        obtenerSiguienteCaracter()
                        return true
                    }else{
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                }else{
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
            }else {
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
        }
        return false
    }

    /**
     ** Este metodo permite construir el token de agrupador
     */
    fun esAgrupador():Boolean{
        var lexema =""
        var filaInicial=filaActual
        var columnaInicial=columnaActual
        var posicionInicial=posicionActual
        if (caracterActual=='{'){
            lexema+=caracterActual
            almacenarToken(lexema, Categoria.LLAVE_IZQUIERDA, filaInicial, columnaInicial);
            obtenerSiguienteCaracter()
            return true
        }
        if (caracterActual=='}'){
            lexema+=caracterActual
            almacenarToken(lexema, Categoria.LLAVE_DERECHA, filaInicial, columnaInicial);
            obtenerSiguienteCaracter()
            return true
        }
        if (caracterActual=='('){
            lexema+=caracterActual
            almacenarToken(lexema, Categoria.PARENTESIS_IZQUIERDO, filaInicial, columnaInicial);
            obtenerSiguienteCaracter()
            return true
        }
        if (caracterActual==')'){
            lexema+=caracterActual
            almacenarToken(lexema, Categoria.PARENTESIS_DERECHO, filaInicial, columnaInicial);
            obtenerSiguienteCaracter()
            return true
        }
        if (caracterActual=='['){
            lexema+=caracterActual
            almacenarToken(lexema, Categoria.CORCHETE_IZQUIERDO, filaInicial, columnaInicial);
            obtenerSiguienteCaracter()
            return true
        }
        if (caracterActual==']'){
            lexema+=caracterActual
            almacenarToken(lexema, Categoria.CORCHETE_DERECHO, filaInicial, columnaInicial);
            obtenerSiguienteCaracter()
            return true
        }
        return false
    }

    fun obtenerSiguienteCaracter(){
        if (posicionActual == codigoFuente.length-1){
            caracterActual = finCodigo
        }else{

            if (caracterActual == '\n'){
                filaActual++
                columnaActual = 0
            }else{
                columnaActual++
            }

            posicionActual++
            caracterActual = codigoFuente[posicionActual]
        }
    }



}
