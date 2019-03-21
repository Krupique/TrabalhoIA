/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Henrique K. Secchi
 */
public class TelaAboutController implements Initializable {

    @FXML
    private AnchorPane paneAbout;
    @FXML
    private TextArea txtArea;
    @FXML
    private JFXButton btFechar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneAbout.getStylesheets().add("trabalhoia/estilos/tema.css");
        
        txtArea.setText("TRABALHO DE INTELIGÊNCIA ARTIFICIAL\n"
                + "Objetivo: Resolver o problema 8-puzzle por meio de funções de buscas.\n"
                + "Buscas utilizadas: Busca cega em profundidade (Depth First Search), Busca cega em largura (Breadth First Search) e Busca Heurística (Best First Search)."
                + "\n\nRealizado por:\n Henrique Krupck Secchi     RA: 101527624"
                  + "\n Rafael Eduardo A. Silva      RA: 101528116");
    }    

    @FXML
    private void evtFechar(ActionEvent event) {
        Stage stage = (Stage)paneAbout.getScene().getWindow();
        stage.close();
    }
    
}
