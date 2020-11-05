package co.edu.uniquindio.compiladores.controladores

import co.edu.uniquindio.compiladores.lexico.AnalizadorLexico
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.sintaxis.AnalizadorSintactico
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import java.io.File
import java.lang.Exception
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

class InicioController : Initializable {

    @FXML lateinit var tablaCodigoFuente:TextArea

    //Tabla donde se interpretan los Tokens
    @FXML lateinit var tablaTokens:TableView<Token>
    @FXML lateinit var colLexema: TableColumn<Token, String>
    @FXML lateinit var colCategoria: TableColumn<Token, String>
    @FXML lateinit var colFila: TableColumn<Token, Int>
    @FXML lateinit var colColumna: TableColumn<Token, Int>


    @FXML lateinit var arbolVisual:TreeView<String>

    lateinit var lexico: AnalizadorLexico

    @FXML
    fun analizarCodigo( e : ActionEvent){

        if(tablaCodigoFuente.text.length > 0){

        val lexico = AnalizadorLexico(tablaCodigoFuente.text)
            lexico.analizar()
            tablaTokens.items=FXCollections.observableArrayList(lexico.listaTokens)


            if(lexico.listaErrores.isEmpty()){

            val sintaxis = AnalizadorSintactico(lexico.listaTokens)
            val uc = sintaxis.esUnidadDeCompilacion()



            if(uc!=null){

                arbolVisual.root = uc.getArbolVisual()
            print( lexico.listaTokens )
            }
        }else{
                var alerta = Alert(Alert.AlertType.WARNING)
                alerta.headerText= "mensaje"
                alerta.contentText = "hay errores lexicos en el código fuente"
            }

            }
    }

    /**
     * Este metodo permite limpiar el text area
     */
    @FXML
    fun limpiar(){
        tablaCodigoFuente.text=""
        tablaTokens.items= FXCollections.observableArrayList(ArrayList<Token>())

    }

    /**
     * ESTE metodo reflejar en la tabla la especificacion de cada uno de los tokens
     */

    override  fun initialize(primaryStage: URL?, p1:ResourceBundle?){
        colLexema.cellValueFactory=PropertyValueFactory(  "lexema")
        colCategoria.cellValueFactory=PropertyValueFactory( "categoria")
        colFila.cellValueFactory=PropertyValueFactory( "fila")
        colColumna.cellValueFactory=PropertyValueFactory( "columna")
    }






}