/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 *
 * @author Henrique K. Secchi
 */
public class TelaPrincipalController implements Initializable {
    
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private HBox hbox;
    @FXML
    private GridPane gridpane;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;
    @FXML
    private ImageView img6;
    @FXML
    private ImageView img7;
    @FXML
    private ImageView img8;
    @FXML
    private ImageView img9;
    @FXML
    private AnchorPane panealgoritmos;
    @FXML
    private AnchorPane panecasosteste;
    @FXML
    private Label progresslabel;
    @FXML
    private AnchorPane panemetodo;
    @FXML
    private JFXButton btAbrir;
    @FXML
    private JFXButton btOrdenar;
    @FXML
    private JFXButton btEmbaralhar;
    @FXML
    private JFXRadioButton caso1;
    @FXML
    private JFXRadioButton caso2;
    @FXML
    private JFXRadioButton caso3;
    @FXML
    private JFXRadioButton casoNenhum;
    @FXML
    private JFXButton btBuscarSolucao;
    @FXML
    private JFXProgressBar progressbar;
    @FXML
    private JFXRadioButton rdmanual;
    @FXML
    private JFXRadioButton rdautomatico;
    
    private Image imgPrincipal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorpane.getStylesheets().add("trabalhoia/estilos/tema.css");
        
        rdCasos(false, false, false, true);
        rdMetodo(true, false);
        exibirProgress(false);
    }    

    @FXML
    private void evtAbrirImagem(ActionEvent event) {
        Properties prop = System.getProperties();
        FileChooser fc = new FileChooser();
        
        if(prop.getProperty("os.name").toLowerCase().contains("windows"))
            fc.setInitialDirectory(new File("C:/"));
        else
            fc.setInitialDirectory(new File("/home"));
        
        File arq = fc.showOpenDialog(null);
        if(arq != null)
        {
            imgPrincipal = new Image(arq.toURI().toString());
            
        }
    }

    @FXML
    private void evtOrdenar(ActionEvent event) {
    }

    @FXML
    private void evtEmbaralhar(ActionEvent event) {
    }

    @FXML
    private void evtCaso1(ActionEvent event) {
        rdCasos(true, false, false, false);
    }

    @FXML
    private void evtCaso2(ActionEvent event) {
        rdCasos(false, true, false, false);
    }

    @FXML
    private void evtCaso3(ActionEvent event) {
        rdCasos(false, false, true, false);
    }

    @FXML
    private void evtCasoNenhum(ActionEvent event) {
        rdCasos(false, false, false, true);
    }

    @FXML
    private void evtBuscarSolucao(ActionEvent event) {
        exibirProgress(true);
    }

    @FXML
    private void evtManual(ActionEvent event) {
        rdMetodo(true, false);
        
    }

    @FXML
    private void evtAutomatic(ActionEvent event) {
        rdMetodo(false, true);
    }
    
    private void rdCasos(boolean v1, boolean v2, boolean v3, boolean v4)
    {
        caso1.setSelected(v1);
        caso2.setSelected(v2);
        caso3.setSelected(v3);
        casoNenhum.setSelected(v4);
    }
    
    private void rdMetodo(boolean v1, boolean v2)
    {
        rdmanual.setSelected(v1);
        rdautomatico.setSelected(v2);
        exibirAreaAutomatica(v2);
    }
    
    private void exibirAreaAutomatica(boolean value)
    {
        panealgoritmos.setVisible(value);
        panecasosteste.setVisible(value);
        btBuscarSolucao.setVisible(value);
    }
    
    private void exibirProgress(boolean value)
    {
        progresslabel.setVisible(value);
        progressbar.setVisible(value);
    }

    @FXML
    private void evtClickImg1(MouseEvent event) {
        System.out.println("teste1");
    }

    @FXML
    private void evtClickImg2(MouseEvent event) {
        System.out.println("teste2");
    }

    @FXML
    private void evtClickImg3(MouseEvent event) {
        System.out.println("teste3");
    }

    @FXML
    private void evtClickImg4(MouseEvent event) {
        System.out.println("teste4");
    }

    @FXML
    private void evtClickImg5(MouseEvent event) {
        System.out.println("teste5");
    }

    @FXML
    private void evtClickImg6(MouseEvent event) {
        System.out.println("teste6");
    }

    @FXML
    private void evtClickImg7(MouseEvent event) {
        System.out.println("teste7");
    }

    @FXML
    private void evtClickImg8(MouseEvent event) {
        System.out.println("teste8");
    }

    @FXML
    private void evtClickImg9(MouseEvent event) {
        System.out.println("teste9");
    }
}
