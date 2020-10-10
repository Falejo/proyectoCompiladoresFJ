package co.edu.uniquindio.compiladores.prueba

/**
 * esta clase permite almacenar e identificar los errores que se presentan en el proyecto
 */
class Error(var error: String, var fila:Int,  var columna:Int, var categoria: String) {
    override fun toString():String{
        return "Error(error='$error', fila=$fila, columna=$columna, categoria=$categoria),"
    }
}