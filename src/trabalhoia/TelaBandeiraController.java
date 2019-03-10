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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Henrique K. Secchi
 */
public class TelaBandeiraController implements Initializable {

    @FXML
    private JFXButton bt1;
    @FXML
    private JFXButton bt2;
    @FXML
    private JFXButton bt3;
    @FXML
    private JFXButton bt4;
    @FXML
    private JFXButton bt5;
    @FXML
    private JFXButton bt6;
    @FXML
    private JFXButton bt7;
    @FXML
    private JFXButton bt8;
    @FXML
    private JFXButton bt9;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btAplicar;

    private static int bandeira;
    private int aux;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.getStylesheets().add("trabalhoia/estilos/tema.css");
        bandeira = TelaPrincipalController.getBandeira();
        aux = bandeira;
        ifss();
    }    
    
    private void ifss()
    {
        switch (bandeira) {
            case 1:
                visibilidade(false, true, true, true, true, true, true, true, true);
                break;
            case 2:
                visibilidade(true, false, true, true, true, true, true, true, true);
                break;
            case 3:
                visibilidade(true, true, false, true, true, true, true, true, true);
                break;
            case 4:
                visibilidade(true, true, true, false, true, true, true, true, true);
                break;
            case 5:
                visibilidade(true, true, true, true, false, true, true, true, true);
                break;
            case 6:
                visibilidade(true, true, true, true, true, false, true, true, true);
                break;
            case 7:
                visibilidade(true, true, true, true, true, true, false, true, true);
                break;
            case 8:
                visibilidade(true, true, true, true, true, true, true, false, true);
                break;
            case 9:
                visibilidade(true, true, true, true, true, true, true, true, false);
                break;
            default:
                break;
        }
            
    }

    @FXML
    private void evt1(ActionEvent event) {
        visibilidade(false, true, true, true, true, true, true, true, true);
        bandeira = 1;
    }

    @FXML
    private void evt2(ActionEvent event) {
        visibilidade(true, false, true, true, true, true, true, true, true);
        bandeira = 2;
    }

    @FXML
    private void evt3(ActionEvent event) {
        visibilidade(true, true, false, true, true, true, true, true, true);
        bandeira = 3;
    }

    @FXML
    private void evt4(ActionEvent event) {
        visibilidade(true, true, true, false, true, true, true, true, true);
        bandeira = 4;
    }

    @FXML
    private void evt5(ActionEvent event) {
        visibilidade(true, true, true, true, false, true, true, true, true);
        bandeira = 5;
    }

    @FXML
    private void evt6(ActionEvent event) {
        visibilidade(true, true, true, true, true, false, true, true, true);
        bandeira = 6;
    }

    @FXML
    private void evt7(ActionEvent event) {
        visibilidade(true, true, true, true, true, true, false, true, true);
        bandeira = 7;
    }

    @FXML
    private void evt8(ActionEvent event) {
        visibilidade(true, true, true, true, true, true, true, false, true);
        bandeira = 8;
    }

    @FXML
    private void evt9(ActionEvent event) {
        visibilidade(true, true, true, true, true, true, true, true, false);
        bandeira = 9;
    }

    @FXML
    private void evtCancelar(ActionEvent event) {
        bandeira = aux;
        fechar();
    }

    @FXML
    private void evtAplicar(ActionEvent event) {
        fechar();
    }
    
    private void fechar()
    {
        Stage stage = (Stage)btCancelar.getScene().getWindow();
        stage.close();
    }
    
    
    private void visibilidade(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7, boolean b8, boolean b9)
    {
        bt1.setVisible(b1);
        bt2.setVisible(b2);
        bt3.setVisible(b3);
        bt4.setVisible(b4);
        bt5.setVisible(b5);
        bt6.setVisible(b6);
        bt7.setVisible(b7);
        bt8.setVisible(b8);
        bt9.setVisible(b9);
    }

    public static int getBandeira() {
        return bandeira;
    }
}
