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
import trabalhoia.threads.BestFirstThread;
import trabalhoia.threads.BuscaLarguraThread;
import trabalhoia.threads.BuscaProfundidadeThread;
import trabalhoia.threads.ExibirPredefinidos;

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
    private int flagBusca;
    private int movManuais;
    private ArrayList<Integer> listMovimentos;
    public int getCaso;
    
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
    @FXML
    private JFXRadioButton rdBestFirst;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorpane.getStylesheets().add("trabalhoia/estilos/tema.css");
        bandeira = 9;
        iniciarImagens();
        rdCasos(false, false, false, true);
        rdMetodo(true, false);
        setRdBuscas(true, false, false);
        exibirProgress(false);
        
        movManuais = 0;
        imgPrincipal = new Image("trabalhoia/recursos/numeros.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
        flagBusca = 1;
        listMovimentos = new ArrayList<>();
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
                    movManuais = 0;
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
        movManuais = 0;
    }

    @FXML
    private void evtEmbaralhar(ActionEvent event) {
        algoritmos.embaralhar();
        print();
        movManuais = 0;
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

    private void setArrayMov(int[] estado)
    {
        listMovimentos = new ArrayList<>();
        for (int i = 0; i < estado.length; i++) {
            listMovimentos.add(estado[i]);
            //System.out.print(estado[i] + " - ");
        }
    }

    public ArrayList<Integer> getListMovimentos() {
        return listMovimentos;
    }
    
    public int[] getMovimentos(int caso)
    {
        if(caso == 1)
            return new int[]{213380, 3347, 8};
        else if(caso == 2)
            return new int[]{34621,1651,8};
        else
            return new int[]{5409,5812,10};
    }
    
    @FXML
    private void evtCaso1(ActionEvent event) {
        rdCasos(true, false, false, false);
        getCaso = 1;
        
        algoritmos.setEstado(new int[]{3, 0, 1, 4, 7, 2, 8, 6, 5});
        setArrayMov(new int[]{7, 4, 3, 0, 1, 2, 5, 8});
        flagBusca = 4;
        print();
    }
    
    @FXML
    private void evtCaso2(ActionEvent event) {
        rdCasos(false, true, false, false);
        getCaso = 2;
        
        algoritmos.setEstado(new int[]{1, 2, 5, 0, 3, 7, 6, 4, 8});
        setArrayMov(new int[]{5, 2, 1, 0, 3, 4, 7, 8});
        flagBusca = 4;
        print();
    }

    @FXML
    private void evtCaso3(ActionEvent event) {
        rdCasos(false, false, true, false);
        getCaso = 3;
        
        algoritmos.setEstado(new int[]{3, 0, 8, 6, 1, 2, 7, 5, 4});
        setArrayMov(new int[]{5, 8, 7, 6, 3, 0, 1, 4, 5, 8});
        flagBusca = 4;
        print();
    }

    @FXML
    private void evtCasoNenhum(ActionEvent event) {
        rdCasos(false, false, false, true);
    }

    @FXML
    private void evtBuscarSolucao(ActionEvent event) {
        exibirProgress(true);

        switch(flagBusca)
        {
            case 1: 
                th = new Thread(new BuscaProfundidadeThread(this, algoritmos));
                th.start();
            break;


            case 2:
                th = new Thread(new BuscaLarguraThread(this, algoritmos));
                th.start();
            break;

            case 3:
                th = new Thread(new BestFirstThread(this, algoritmos));
                th.start();
            break;

            case 4:
                th = new Thread(new ExibirPredefinidos(this, algoritmos));
                th.start();
            break;

            default:
            break;
            
        }
        
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        {
            movManuais++;
            print();
            if(algoritmos.validarFimDeJogo())
            {
                Alert a =  new Alert(Alert.AlertType.INFORMATION, "Parabéns!\nVocê concluiu o jogo em " + movManuais +" movimentos! Agora saia do joguinho e volte a estudar!", ButtonType.OK);
                a.showAndWait();
                algoritmos.setEstado(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
                movManuais = 0;
            }
        }
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
        movManuais = 0;
    }

    @FXML
    private void evtPre2(MouseEvent event) {
        imgPrincipal = new Image("trabalhoia/recursos/namorada.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
        movManuais = 0;
    }

    @FXML
    private void evtPre3(MouseEvent event) {
        imgPrincipal = new Image("trabalhoia/recursos/eu.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
        movManuais = 0;
    }

    @FXML
    private void evtPre4(MouseEvent event) {
        imgPrincipal = new Image("trabalhoia/recursos/flamengo.png");
        algoritmos = new Algoritmos(imgPrincipal, bandeira);
        print();
        movManuais = 0;
    }

    @FXML
    private void evtOpen(ActionEvent event) {
        abrirImagem();
        movManuais = 0;
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
            movManuais = 0;
        }catch(Exception er){
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela bandeira! " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }


    @FXML
    private void evtSobre(ActionEvent event) {
        try
        {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("TelaAbout.fxml")));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
        }catch(Exception er){
            Alert a = new Alert(Alert.AlertType.ERROR, "Erro ao abrir tela sobre! " + er.getMessage(), ButtonType.OK);
            a.showAndWait();
        }
    }

    public static int getBandeira() {
        return bandeira;
    }

    @FXML
    private void evtBuscaProfundidade(ActionEvent event) {
        setRdBuscas(true, false, false);
        rdCasos(false, false, false, true);
        flagBusca = 1;
    }

    @FXML
    private void evtBuscaLargura(ActionEvent event) {
        setRdBuscas(false, true, false);
        rdCasos(false, false, false, true);
        flagBusca = 2;
    }

    @FXML
    private void evtBestFirst(ActionEvent event) {
        setRdBuscas(false, false, true);
        rdCasos(false, false, false, true);
        flagBusca = 3;
    }
    
    public void setRdBuscas(boolean v1, boolean v2, boolean v3)
    {
        rdBuscaProfundidade.setSelected(v1);
        rdBuscaLargura.setSelected(v2);
        rdBestFirst.setSelected(v3);
    }
    
    public void exibirResultados(int[] vet)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Busca realizada em:"
                + "\nProfundidade (Depth First): " + vet[0] + " movimentos"
                + "\nLargura (Breadth First): " + vet[1] + " movimentos"
                + "\nHeurística (Best First): " + vet[2] + " movimentos", ButtonType.OK);
        
        a.showAndWait();
    }
    
    public void exibirBuscaProfundidade(int mov)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Busca em profundidade (Depth First) realizada em: " + mov + " movimentos.", ButtonType.OK);
        a.showAndWait();
    }
    
    public void exibirBuscaLargura(int mov)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Busca em largura (Breadth First) realizada em: " + mov + " movimentos.", ButtonType.OK);
        a.showAndWait();
    }
    
    public void exibirBuscaHeuristica(int mov)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Busca Heuristica (Best First) realizada em: " + mov + " movimentos.", ButtonType.OK);
        a.showAndWait();
    }
}
