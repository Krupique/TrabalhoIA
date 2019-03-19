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
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import trabalhoia.estrutura.Algoritmos;
import trabalhoia.estrutura.BuscaProfundidadeThread;

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
    private Algoritmos algoritmos;
    private static int bandeira;
    Thread th = new Thread();
    @FXML
    private ImageView imgpre1;
    @FXML
    private ImageView imgpre2;
    @FXML
    private ImageView imgpre3;
    @FXML
    private ImageView imgpre4;
    @FXML
    private JFXRadioButton rdBuscaProfundidade;
    @FXML
    private JFXRadioButton rdBuscaLargura;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorpane.getStylesheets().add("trabalhoia/estilos/tema.css");
        bandeira = 9;
        iniciarImagens();
        rdCasos(false, false, false, true);
        rdMetodo(true, false);
        exibirProgress(false);
        
        imgPrincipal = new Image("trabalhoia/recursos/numeros.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
    }    
    
    public void iniciarImagens()
    {
        Image img = new Image("trabalhoia/recursos/numeros.png");
        imgpre1.setImage(img);
        
        img = new Image("trabalhoia/recursos/namorada.png");
        imgpre2.setImage(img);
        
        img = new Image("trabalhoia/recursos/eu.png");
        imgpre3.setImage(img);
        
        img = new Image("trabalhoia/recursos/flamengo.png");
        imgpre4.setImage(img);
    }

    @FXML
    private void evtAbrirImagem(ActionEvent event) {
        abrirImagem();
        
    }
    
    private void abrirImagem()
    {
        Properties prop = System.getProperties();
        FileChooser fc = new FileChooser();
        
        if(prop.getProperty("os.name").toLowerCase().contains("windows"))
            fc.setInitialDirectory(new File("C:/"));
        else
            fc.setInitialDirectory(new File("/home"));
        
        File arq = fc.showOpenDialog(null);
        if(arq != null)
        {
            int larg, alt;
            larg = (int)new Image(arq.toURI().toString()).getWidth();
            alt = (int)new Image(arq.toURI().toString()).getHeight();
            
            if(larg == alt)
            {
                larg %= 3;
                alt %= 3;
                
                if(larg == 0 && alt == 0)
                {
                    imgPrincipal = new Image(arq.toURI().toString());
                    algoritmos = new Algoritmos(imgPrincipal, bandeira);
                    print();
                }
                else{
                    Alert a = new Alert(Alert.AlertType.ERROR, "Imagem inválida frango!\nAlgum dos lados da imagem não é múltiplo de 3!\nCertifique-se de abrir uma imagem quadrada e de tamanho múltiplo de 3!", ButtonType.OK);
                    a.showAndWait();
                }
                
            }
            else{
                Alert a = new Alert(Alert.AlertType.ERROR, "Imagem inválida frango!\nA imagem que você está tentando abrir não é quadrada!\nCertifique-se de abrir uma imagem quadrada e de tamanho múltiplo de 3!", ButtonType.OK);
                a.showAndWait();
            }
        }
    }

    @FXML
    private void evtOrdenar(ActionEvent event) {
        algoritmos.ordenar();
        print();
        
    }

    @FXML
    private void evtEmbaralhar(ActionEvent event) {
        algoritmos.embaralhar();
        print();
    }
    
    public void print()
    {
        Image[] imgs = algoritmos.getImgs();
        img1.setImage(imgs[0]);
        img2.setImage(imgs[1]);
        img3.setImage(imgs[2]);
        img4.setImage(imgs[3]);
        img5.setImage(imgs[4]);
        img6.setImage(imgs[5]);
        img7.setImage(imgs[6]);
        img8.setImage(imgs[7]);
        img9.setImage(imgs[8]);
        
    }
    
    //Função não concluída. Vo fazer se der vontade E tiver tempo.
    private void printMec(int pos, int bandeira)
    {
        Image[] imgs = algoritmos.getImgs();
        bandeira = algoritmos.getBandeira();
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
        th = new Thread(new BuscaProfundidadeThread(this, algoritmos));
        th.start();
        
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
    
    public void exibirProgress(boolean value)
    {
        progresslabel.setVisible(value);
        progressbar.setVisible(value);
    }

    @FXML
    private void evtClickImg1(MouseEvent event) {
        System.out.println("teste1");
        if(algoritmos.movimentar(1))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtClickImg2(MouseEvent event) {
        System.out.println("teste2");
        if(algoritmos.movimentar(2))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtClickImg3(MouseEvent event) {
        System.out.println("teste3");
        if(algoritmos.movimentar(3))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtClickImg4(MouseEvent event) {
        System.out.println("teste4");
        if(algoritmos.movimentar(4))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtClickImg5(MouseEvent event) {
        System.out.println("teste5");
        if(algoritmos.movimentar(5))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtClickImg6(MouseEvent event) {
        System.out.println("teste6");
        if(algoritmos.movimentar(6))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtClickImg7(MouseEvent event) {
        System.out.println("teste7");
        if(algoritmos.movimentar(7))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtClickImg8(MouseEvent event) {
        System.out.println("teste8");
        if(algoritmos.movimentar(8))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtClickImg9(MouseEvent event) {
        System.out.println("teste9");
        if(algoritmos.movimentar(9))
            print();
        else
        {
            Alert a =  new Alert(Alert.AlertType.ERROR, "Impossível mover esta peça frango!", ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtPre1(MouseEvent event) {
        imgPrincipal = new Image("trabalhoia/recursos/numeros.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
    }

    @FXML
    private void evtPre2(MouseEvent event) {
        imgPrincipal = new Image("trabalhoia/recursos/namorada.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
    }

    @FXML
    private void evtPre3(MouseEvent event) {
        imgPrincipal = new Image("trabalhoia/recursos/eu.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
    }

    @FXML
    private void evtPre4(MouseEvent event) {
        imgPrincipal = new Image("trabalhoia/recursos/flamengo.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
    }

    @FXML
    private void evtOpen(ActionEvent event) {
        abrirImagem();
    }

    @FXML
    private void evtClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void evtBandeira(ActionEvent event) {
        try
        {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("TelaBandeira.fxml")));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            bandeira = TelaBandeiraController.getBandeira();
            algoritmos = new Algoritmos(imgPrincipal, bandeira);
            print();
        }catch(Exception er){
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela bandeira! " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }

    @FXML
    private void evtComoJogar(ActionEvent event) {
    }

    @FXML
    private void evtSobre(ActionEvent event) {
    }

    public static int getBandeira() {
        return bandeira;
    }

    @FXML
    private void evtBuscaProfundidade(ActionEvent event) {
    }

    @FXML
    private void evtBuscaLargura(ActionEvent event) {
        algoritmos.buscaLargura();
        
    }
}
