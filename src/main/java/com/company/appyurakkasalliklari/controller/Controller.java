package com.company.appyurakkasalliklari.controller;

import com.company.appyurakkasalliklari.ComponentContainer.Components;
import com.company.appyurakkasalliklari.constants.ProjectConstants;
import com.company.appyurakkasalliklari.model.*;
import com.company.appyurakkasalliklari.service.Service;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.*;

public class Controller{
    @FXML
    private RadioButton atr2Kuzatilgan,atr5Kuzatilgan,atr8Kuzatilgan,atr9Kuzatilgan,atr10Kuzatilgan,atr11Kuzatilgan,atr12Kuzatilgan,atr13Kuzatilgan, atr14Kuzatilgan,atr15Kuzatilgan,atr16Kuzatilgan,atr17Kuzatilgan,atr18Kuzatilgan,atr19Kuzatilgan,atr20Kuzatilgan, atr21Kuzatilgan,atr22Kuzatilgan,atr23Kuzatilgan,atr24Kuzatilgan,atr25Kuzatilgan,atr26Kuzatilgan,atr27Kuzatilgan, atr28Kuzatilgan,atr29Kuzatilgan,atr30Kuzatilgan,atr31Kuzatilgan,atr32Kuzatilgan;
    @FXML
    private RadioButton atr2Kuzatilmagan,atr5Kuzatilmagan,atr8Kuzatilmagan,atr9Kuzatilmagan,atr10Kuzatilmagan,atr11Kuzatilmagan,atr12Kuzatilmagan,atr13Kuzatilmagan, atr14Kuzatilmagan,atr15Kuzatilmagan,atr16Kuzatilmagan,atr17Kuzatilmagan,atr18Kuzatilmagan,atr19Kuzatilmagan,atr20Kuzatilmagan, atr21Kuzatilmagan,atr22Kuzatilmagan,atr23Kuzatilmagan,atr24Kuzatilmagan,atr25Kuzatilmagan,atr26Kuzatilmagan,atr27Kuzatilmagan, atr28Kuzatilmagan,atr29Kuzatilmagan,atr30Kuzatilmagan,atr31Kuzatilmagan,atr32Kuzatilmagan;

    @FXML
    private RadioButton atr3Kamaygan,atr3Ortgan,atr4Kotarilgan,atr4Kotarilmagan,atr6Qisqa,atr6Uzoq,atr7ChapKurak,atr7ChapQol,atr7ChapYelka,atr7OngYelka;

    @FXML
    private Label info,help,belgilar,tashhis;

    @FXML
    private Accordion accordion1;

    private List<RadioButton>radioButtonList;

    @FXML
    private TextField newAtribut,newValue;
    @FXML
    private ComboBox<String> qiymat1,qiymat2,qiymat3,qiymat4,qoidalarCombobox,atributTanlashCombobox,zanjirAtribut,zanjirQiymat;

    @FXML
    private ListView<String> qoidalarList;

    @FXML
    private ToggleButton kiritishTanlashToggle,newQoidaToggle,zanjirOrKasallikToggle;

    @FXML
    private RadioButton qiymat1Radio,qiymat2Radio,qiymat3Radio,qiymat4Radio;


    private String currentAtribut;

    @FXML
    private Button btn2;

    @FXML
    private TextField newKasallik;
    @FXML
    private Tab tab1,tab2;

    @FXML
    private TabPane tabPane;

    public static List<MulohazalarItem> bemordagiBelgilar;

    @FXML
    void initialize() {
        bemordagiBelgilar = new ArrayList<>(10);
        radioButtonList = new ArrayList<>(10);

        Components.qoidaList = new ArrayList<>(15);
        Components.atributQiymatList = new ArrayList<>(15);
        Components.kasallikList = new ArrayList<>(15);
        Components.atributList = new ArrayList<>(15);
        Components.qiymatList = new ArrayList<>(15);
        Service.load();
        loadNewAddAtributQiymat();
    }

    public void loadNewAddAtributQiymat(){
        if (Components.atributQiymatList.size() > 33){
            for (int i = 33; i < Components.atributQiymatList.size(); i++) {
                AtributQiymat atributQiymat = Components.atributQiymatList.get(i);
                List<String> qiymat = atributQiymat.getQiymat();
                String []values = new String[atributQiymat.getQiymat().size()];
                for (int j = 0; j < atributQiymat.getQiymat().size(); j++) {
                    values[j] = qiymat.get(j);
                }

                addNewItemToPanel(atributQiymat.getAtribut(),values);
            }
        }
    }

    public void addNewItemToPanel(String atributName, String...values){
        TitledPane titledPane = new TitledPane();
        titledPane.setAlignment(Pos.CENTER);
        titledPane.setText(atributName);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPadding(new Insets(10));
        anchorPane.setPrefSize(200,180);

        int height = 24;

        ToggleGroup group1 = new ToggleGroup();

        for (String value : values) {
            RadioButton radioButton1 = new RadioButton();
            radioButton1.setPrefSize(120 ,18);
            radioButton1.setText(value);
            radioButton1.setToggleGroup(group1);
            radioButton1.setOnAction(action);
            radioButton1.setLayoutX(236);
            radioButton1.setLayoutY(height);
            radioButton1.setAlignment(Pos.CENTER);
            anchorPane.getChildren().add(radioButton1);
            radioButtonList.add(radioButton1);
            height += 40;
        }

        titledPane.setContent(anchorPane);
        titledPane.setOnMouseClicked(mouseEventEventHandler);
        accordion1.getPanes().add(titledPane);

    }

    EventHandler<ActionEvent> action = event -> {
        RadioButton radioButton1 = (RadioButton) event.getSource();

        MulohazalarItem item = new MulohazalarItem();
        item.setQiymat(radioButton1.getText());
        item.setAtribut(currentAtribut);

        if (!bemordagiBelgilar.contains(item)){
            bemordagiBelgilar.add(item);
            return;
        }

        for (int i = 0; i < bemordagiBelgilar.size(); i++) {
            if (bemordagiBelgilar.get(i).getAtribut().equals(currentAtribut)) {
                bemordagiBelgilar.get(i).setQiymat(radioButton1.getText());
                return;
            }
        }
    };

    EventHandler<MouseEvent> mouseEventEventHandler = mouseEvent -> {
        TitledPane titledPane1 = (TitledPane) mouseEvent.getSource();
        currentAtribut = titledPane1.getText();
    };

    @FXML
    void atr2Clicked(ActionEvent event){

        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr2Kuzatilgan.getId())){
            Service.addNewSign(2,1);
        }else if(radioButton.getId().equals(atr2Kuzatilmagan.getId())){
            Service.addNewSign(2,2);
        }
    }
    @FXML
    void atr3Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr3Ortgan.getId())){
            Service.addNewSign(3,5);
        }else if(radioButton.getId().equals(atr3Kamaygan.getId())){
            Service.addNewSign(3,6);
        }
    }
    @FXML
    void atr4Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr4Kotarilgan.getId())){
            Service.addNewSign(4,3);
        }else if (radioButton.getId().equals(atr4Kotarilmagan.getId())){
            Service.addNewSign(4,4);
        }
    }
    @FXML
    void atr5Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr5Kuzatilgan.getId())){
            Service.addNewSign(5,1);
        }else if(radioButton.getId().equals(atr5Kuzatilmagan.getId())){
            Service.addNewSign(5,2);
        }
    }
    @FXML
    void atr6Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr6Qisqa.getId())){
            Service.addNewSign(6,7);
        }else if(radioButton.getId().equals(atr6Uzoq.getId())){
            Service.addNewSign(6,8);
        }
    }
    @FXML
    void atr7Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();

        if (radioButton.getId().equals(atr7ChapKurak.getId())){
            Service.addNewSign(7,12);
        }else if(radioButton.getId().equals(atr7ChapQol.getId())){
            Service.addNewSign(7,11);
        }else if (radioButton.getId().equals(atr7ChapYelka.getId())){
            Service.addNewSign(7,9);
        }else if (radioButton.getId().equals(atr7OngYelka.getId())){
            Service.addNewSign(7,10);
        }
    }
    @FXML
    void atr8Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr8Kuzatilgan.getId())){
            Service.addNewSign(8,1);
        }else if(radioButton.getId().equals(atr8Kuzatilmagan.getId())){
            Service.addNewSign(8,2);
        }
    }
    @FXML
    void atr9Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr9Kuzatilgan.getId())){
            Service.addNewSign(9,1);
        }else if(radioButton.getId().equals(atr9Kuzatilmagan.getId())){
            Service.addNewSign(9,2);
        }
    }
    @FXML
    void atr10Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr10Kuzatilgan.getId())){
            Service.addNewSign(10,1);
        }else if(radioButton.getId().equals(atr10Kuzatilmagan.getId())){
            Service.addNewSign(10,2);
        }
    }
    @FXML
    void atr11Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr11Kuzatilgan.getId())){
            Service.addNewSign(11,1);
        }else if(radioButton.getId().equals(atr11Kuzatilmagan.getId())){
            Service.addNewSign(11,2);
        }
    }
    @FXML
    void atr12Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr12Kuzatilgan.getId())){
            Service.addNewSign(12,1);
        }else if(radioButton.getId().equals(atr12Kuzatilmagan.getId())){
            Service.addNewSign(12,2);
        }
    }
    @FXML
    void atr13Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr13Kuzatilgan.getId())){
            Service.addNewSign(13,1);
        }else if(radioButton.getId().equals(atr13Kuzatilmagan.getId())){
            Service.addNewSign(13,2);
        }
    }
    @FXML
    void atr14Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr14Kuzatilgan.getId())){
            Service.addNewSign(14,1);
        }else if(radioButton.getId().equals(atr14Kuzatilmagan.getId())){
            Service.addNewSign(14,2);
        }
    }
    @FXML
    void atr15Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr15Kuzatilgan.getId())){
            Service.addNewSign(15,1);
        }else if(radioButton.getId().equals(atr15Kuzatilmagan.getId())){
            Service.addNewSign(15,2);
        }
    }
    @FXML
    void atr16Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr16Kuzatilgan.getId())){
            Service.addNewSign(16,1);
        }else if(radioButton.getId().equals(atr16Kuzatilmagan.getId())){
            Service.addNewSign(16,2);
        }
    }
    @FXML
    void atr17Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr17Kuzatilgan.getId())){
            Service.addNewSign(17,1);
        }else if(radioButton.getId().equals(atr17Kuzatilmagan.getId())){
            Service.addNewSign(17,2);
        }
    }
    @FXML
    void atr18Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr18Kuzatilgan.getId())){
            Service.addNewSign(18,1);
        }else if(radioButton.getId().equals(atr18Kuzatilmagan.getId())){
            Service.addNewSign(18,2);
        }
    }
    @FXML
    void atr19Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr19Kuzatilgan.getId())){
            Service.addNewSign(19,1);
        }else if(radioButton.getId().equals(atr19Kuzatilmagan.getId())){
            Service.addNewSign(19,2);
        }
    }
    @FXML
    void atr20Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr20Kuzatilgan.getId())){
            Service.addNewSign(20,1);
        }else if(radioButton.getId().equals(atr20Kuzatilmagan.getId())){
            Service.addNewSign(20,2);
        }
    }
    @FXML
    void atr21Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr21Kuzatilgan.getId())){
            Service.addNewSign(21,1);
        }else if(radioButton.getId().equals(atr21Kuzatilmagan.getId())){
            Service.addNewSign(21,2);
        }
    }
    @FXML
    void atr22Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr22Kuzatilgan.getId())){
            Service.addNewSign(22,1);
        }else if(radioButton.getId().equals(atr22Kuzatilmagan.getId())){
            Service.addNewSign(22,2);
        }
    }
    @FXML
    void atr23Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr23Kuzatilgan.getId())){
            Service.addNewSign(23,1);
        }else if(radioButton.getId().equals(atr23Kuzatilmagan.getId())){
            Service.addNewSign(23,2);
        }
    }
    @FXML
    void atr24Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr24Kuzatilgan.getId())){
            Service.addNewSign(24,1);
        }else if(radioButton.getId().equals(atr24Kuzatilmagan.getId())){
            Service.addNewSign(24,2);
        }
    }
    @FXML
    void atr25Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr25Kuzatilgan.getId())){
            Service.addNewSign(25,1);
        }else if(radioButton.getId().equals(atr25Kuzatilmagan.getId())){
            Service.addNewSign(25,2);
        }
    }
    @FXML
    void atr26Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr26Kuzatilgan.getId())){
            Service.addNewSign(26,1);
        }else if(radioButton.getId().equals(atr26Kuzatilmagan.getId())){
            Service.addNewSign(26,2);
        }
    }
    @FXML
    void atr27Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr27Kuzatilgan.getId())){
            Service.addNewSign(27,1);
        }else if(radioButton.getId().equals(atr27Kuzatilmagan.getId())){
            Service.addNewSign(27,2);
        }
    }
    @FXML
    void atr28Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr28Kuzatilgan.getId())){
            Service.addNewSign(28,1);
        }else if(radioButton.getId().equals(atr28Kuzatilmagan.getId())){
            Service.addNewSign(28,2);
        }
    }
    @FXML
    void atr29Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr29Kuzatilgan.getId())){
            Service.addNewSign(29,1);
        }else if(radioButton.getId().equals(atr29Kuzatilmagan.getId())){
            Service.addNewSign(29,2);
        }
    }
    @FXML
    void atr30Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr30Kuzatilgan.getId())){
            Service.addNewSign(30,1);
        }else if(radioButton.getId().equals(atr30Kuzatilmagan.getId())){
            Service.addNewSign(30,2);
        }
    }
    @FXML
    void atr31Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr31Kuzatilgan.getId())){
            Service.addNewSign(31,1);
        }else if(radioButton.getId().equals(atr31Kuzatilmagan.getId())){
            Service.addNewSign(31,2);
        }
    }
    @FXML
    void atr32Clicked(ActionEvent event){
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getId().equals(atr32Kuzatilgan.getId())){
            Service.addNewSign(32,1);
        }else if(radioButton.getId().equals(atr32Kuzatilmagan.getId())){
            Service.addNewSign(32,2);
        }
    }
    @FXML
    void tab1Selected(Event event){
    }

    @FXML
    void tab2Selected(Event event){

    }

    @FXML
    void tab3Selected(Event event){
        updateListBox();
        qoidalarCombobox.getItems().clear();
        qoidalarCombobox.getItems().addAll(Service.getQoidalar());
        qoidalarCombobox.getItems().addAll(Service.getQoidalar());
        setQiymatlar();
    }

    @FXML
    void qiymat1SelectedIndex(Event event){
        if (qiymat1.getSelectionModel().getSelectedIndex() > -1 && atributTanlashCombobox.isDisable()){
            qiymat2.setDisable(false);
        }
    }

    @FXML
    void qiymat2SelectedIndex(Event event){
        if (qiymat2.getSelectionModel().getSelectedIndex() > -1){
            qiymat2Radio.setDisable(false);
            qiymat3.setDisable(false);
        }
    }

    @FXML
    void qiymat3SelectedIndex(Event event){
        if (qiymat3.getSelectionModel().getSelectedIndex() > -1){
            qiymat3Radio.setDisable(false);
            qiymat4.setDisable(false);
        }
    }
    @FXML
    void qiymat4SelectedIndex(Event event){
        if (qiymat4.getSelectionModel().getSelectedIndex() > -1){
            qiymat4Radio.setDisable(false);
        }
    }

    @FXML
    void atributTanlashSelectedIndex(Event event){
        if (atributTanlashCombobox.getSelectionModel().getSelectedIndex() > -1){
            if (atributTanlashCombobox.getSelectionModel().getSelectedIndex() > -1){
                qiymat1.getItems().clear();
                String[] qiymatlar = Service.getQiymatlar(atributTanlashCombobox.getSelectionModel().getSelectedIndex());
                qiymat1.getItems().addAll(qiymatlar);
            }

        }
    }

    @FXML
    void inputType(Event event){
        if (atributTanlashCombobox.isDisable()){
            newAtribut.setDisable(true);
            newAtribut.setVisible(false);
            atributTanlashCombobox.setDisable(false);
            atributTanlashCombobox.setVisible(true);
            kiritishTanlashToggle.setText("Kiritish");

            atributTanlashCombobox.getItems().clear();
            atributTanlashCombobox.getItems().addAll(Service.getAtributs());
            qiymat1.getItems().clear();
            qiymat2.getItems().clear();
            qiymat3.getItems().clear();
            qiymat4.getItems().clear();

            qiymat2.setDisable(true);
            qiymat3.setDisable(true);
            qiymat4.setDisable(true);

            qiymat1Radio.setSelected(true);
            qiymat2Radio.setDisable(true);
            qiymat3Radio.setDisable(true);
            qiymat4Radio.setDisable(true);
            qiymat1.getSelectionModel().select(-1);
            return;
        }
        setQiymatlar();

        newAtribut.setDisable(false);
        newAtribut.setVisible(true);
        atributTanlashCombobox.setVisible(false);
        atributTanlashCombobox.setDisable(true);
        kiritishTanlashToggle.setText("Tanlash");
    }

    @FXML
    void addNewValueClick(Event event){
        String text = newValue.getText().toLowerCase();

        if (text.isEmpty()){
            showMessage("Qiymat kiritilmadi");
            return;
        }

        boolean success = Service.addNewValue(text);

        if (!success){
            showMessage("Bunday qiymat mavjud");
            return;
        }

        setQiymatlar();
        newValue.setText("");
        showMessage("Yangi qiymat qo'shildi");
    }

    @FXML
    void addAtributClick(Event event){
        if (atributTanlashCombobox.isDisable()){
            String atribut = newAtribut.getText().toLowerCase();

            if (atribut.isEmpty()){
                showMessage("Atributni kiriting!");
                return;
            }
            // atibut-qiymat qo'shish
            boolean isAdded = true;
            String value1 = qiymat1.getSelectionModel().getSelectedItem();
            String value2 = qiymat2.getSelectionModel().getSelectedItem();
            String value3 = qiymat3.getSelectionModel().getSelectedItem();
            String value4 = qiymat4.getSelectionModel().getSelectedItem();

            if (qoidalarCombobox.getSelectionModel().getSelectedIndex() == -1){

                if (qiymat2.getSelectionModel().getSelectedIndex() == -1
                        && qiymat3.getSelectionModel().getSelectedIndex() == -1
                        && qiymat4.getSelectionModel().getSelectedIndex() == -1) {
                    isAdded = Service.addNewAtributQiymat(-1,-1,atribut,value1);
                    if (isAdded) addNewItemToPanel(atribut,value1);

                } else if (qiymat3.getSelectionModel().getSelectedIndex() == -1
                        && qiymat4.getSelectionModel().getSelectedIndex() == -1) {
                    isAdded = Service.addNewAtributQiymat(-1,-1,atribut,value1, value2);
                    if (isAdded) addNewItemToPanel(atribut,value1,value2);
                } else if (qiymat4.getSelectionModel().getSelectedIndex() == -1) {
                    isAdded = Service.addNewAtributQiymat(-1,-1,atribut,value1, value2,value3);
                    if (isAdded) addNewItemToPanel(atribut,value1,value2,value3);
                }else {
                    isAdded = Service.addNewAtributQiymat(-1,-1,atribut,value1, value2,value3, value4);
                    if (isAdded) addNewItemToPanel(atribut,value1,value2,value3,value4);
                }
                setQiymatlar();
                if (isAdded){
                    showMessage("Yangi atribut-qiymat muffaqiyatli qo'shildi!");
                }else {
                    showMessage("Atribut qiymatlari o'zgartirildi!");
                }
            } else {
                //yangi atributni qoidaga ham qo'shish
                int pos = -1;

                if (qiymat1Radio.isSelected()) pos = 1;
                if (qiymat2Radio.isSelected()) pos = 2;
                if (qiymat3Radio.isSelected()) pos = 3;
                if (qiymat4Radio.isSelected()) pos = 4;

                if (qiymat2.getSelectionModel().getSelectedIndex() == -1
                        && qiymat3.getSelectionModel().getSelectedIndex() == -1
                        && qiymat4.getSelectionModel().getSelectedIndex() == -1) {
                    isAdded = Service.addNewAtributQiymat(qoidalarCombobox.getSelectionModel().getSelectedIndex(),pos,atribut,value1);
                    if (isAdded) addNewItemToPanel(atribut,value1);
                } else if (qiymat3.getSelectionModel().getSelectedIndex() == -1
                        && qiymat4.getSelectionModel().getSelectedIndex() == -1) {
                    isAdded = Service.addNewAtributQiymat(qoidalarCombobox.getSelectionModel().getSelectedIndex(),pos,atribut,value1, value2);
                    if (isAdded) addNewItemToPanel(atribut,value1,value2);
                } else if (qiymat4.getSelectionModel().getSelectedIndex() == -1) {
                    isAdded = Service.addNewAtributQiymat(qoidalarCombobox.getSelectionModel().getSelectedIndex(),pos,atribut,value1, value2,value3);
                    if (isAdded) addNewItemToPanel(atribut,value1,value2,value3);
                }else {
                    isAdded = Service.addNewAtributQiymat(qoidalarCombobox.getSelectionModel().getSelectedIndex(),pos,atribut,value1, value2,value3, value4);
                    if (isAdded) addNewItemToPanel(atribut,value1,value2,value3,value4);
                }
                updateListBox();
                setQiymatlar();
                if (isAdded){
                    showMessage("Yangi atribut-qiymat qoidaga muffaqiyatli qo'shildi !");
                }else {
                    showMessage("Qoida atribut qiymati muffaqiyatli o'zgartirildi !");
                }
            }
        }
        else {
            if (qoidalarCombobox.getSelectionModel().getSelectedIndex() == -1){
                showMessage("Qoidani tanlang!");
                return;
            }

            if (atributTanlashCombobox.getSelectionModel().getSelectedIndex() == -1){
                showMessage("Atributni tanlang!");
                return;
            }else {
                if (qiymat1.getSelectionModel().getSelectedIndex() == -1){
                   showMessage("Qiymatni tanlang!");
                    return;
                }

                Service.addNewAtributQiymat(qoidalarCombobox.getSelectionModel().getSelectedIndex(),1,atributTanlashCombobox.getSelectionModel().getSelectedItem(),qiymat1.getSelectionModel().getSelectedItem());
                updateListBox();
                showMessage("Tanlangan atribut-qiymat qoidaga muffaqiyatli qo'shildi !");
            }
        }

        resetExpertPanelComponents();

    }

    public void showMessage(String message){
        Alert message1 = new Alert(Alert.AlertType.INFORMATION,message);
        message1.setTitle(ProjectConstants.ALERT_TITLE);
        message1.setHeaderText(ProjectConstants.ALERT_HEADER);
        message1.show();
    }

    public void updateListBox(){
        qoidalarList.getItems().clear();
        qoidalarList.refresh();
        qoidalarList.getItems().add(Service.writeToList());
    }

    public void setQiymatlar(){
        qiymat1.getItems().clear();
        qiymat2.getItems().clear();
        qiymat3.getItems().clear();
        qiymat4.getItems().clear();

        String[] qiymatlar = Service.getQiymatlar(-1);
        qiymat1.getItems().addAll(qiymatlar);
        qiymat2.getItems().addAll(qiymatlar);
        qiymat3.getItems().addAll(qiymatlar);
        qiymat4.getItems().addAll(qiymatlar);
    }

    @FXML
    public void resetComponetValue(){
        atr2Kuzatilgan.setSelected(false);
        atr3Kamaygan.setSelected(false);
        atr3Ortgan.setSelected(false);
        atr4Kotarilgan.setSelected(false);
        atr4Kotarilmagan.setSelected(false);
        atr5Kuzatilgan.setSelected(false);
        atr6Qisqa.setSelected(false);
        atr6Uzoq.setSelected(false);
        atr7OngYelka.setSelected(false);
        atr7ChapKurak.setSelected(false);
        atr7ChapQol.setSelected(false);
        atr7ChapYelka.setSelected(false);
        atr8Kuzatilgan.setSelected(false);
        atr9Kuzatilgan.setSelected(false);
        atr10Kuzatilgan.setSelected(false);
        atr11Kuzatilgan.setSelected(false);
        atr12Kuzatilgan.setSelected(false);
        atr13Kuzatilgan.setSelected(false);
        atr14Kuzatilgan.setSelected(false);
        atr15Kuzatilgan.setSelected(false);
        atr16Kuzatilgan.setSelected(false);
        atr17Kuzatilgan.setSelected(false);
        atr18Kuzatilgan.setSelected(false);
        atr19Kuzatilgan.setSelected(false);
        atr20Kuzatilgan.setSelected(false);
        atr21Kuzatilgan.setSelected(false);
        atr22Kuzatilgan.setSelected(false);
        atr23Kuzatilgan.setSelected(false);
        atr24Kuzatilgan.setSelected(false);
        atr25Kuzatilgan.setSelected(false);
        atr26Kuzatilgan.setSelected(false);
        atr27Kuzatilgan.setSelected(false);
        atr28Kuzatilgan.setSelected(false);
        atr29Kuzatilgan.setSelected(false);
        atr30Kuzatilgan.setSelected(false);
        atr31Kuzatilgan.setSelected(false);
        atr32Kuzatilgan.setSelected(false);
        atr2Kuzatilmagan.setSelected(false);
        atr5Kuzatilmagan.setSelected(false);
        atr8Kuzatilmagan.setSelected(false);
        atr9Kuzatilmagan.setSelected(false);
        atr10Kuzatilmagan.setSelected(false);
        atr11Kuzatilmagan.setSelected(false);
        atr12Kuzatilmagan.setSelected(false);
        atr13Kuzatilmagan.setSelected(false);
        atr14Kuzatilmagan.setSelected(false);
        atr15Kuzatilmagan.setSelected(false);
        atr16Kuzatilmagan.setSelected(false);
        atr17Kuzatilmagan.setSelected(false);
        atr18Kuzatilmagan.setSelected(false);
        atr19Kuzatilmagan.setSelected(false);
        atr20Kuzatilmagan.setSelected(false);
        atr21Kuzatilmagan.setSelected(false);
        atr22Kuzatilmagan.setSelected(false);
        atr23Kuzatilmagan.setSelected(false);
        atr24Kuzatilmagan.setSelected(false);
        atr25Kuzatilmagan.setSelected(false);
        atr26Kuzatilmagan.setSelected(false);
        atr27Kuzatilmagan.setSelected(false);
        atr28Kuzatilmagan.setSelected(false);
        atr29Kuzatilmagan.setSelected(false);
        atr30Kuzatilmagan.setSelected(false);
        atr31Kuzatilmagan.setSelected(false);
        atr32Kuzatilmagan.setSelected(false);

        for (int i = 0; i < radioButtonList.size(); i++) {
            radioButtonList.get(i).setSelected(false);
        }

        bemordagiBelgilar.clear();
        Service.invalidRule.clear();
    }

    @FXML
    private void resetExpertPanelComponents() {
        qoidalarCombobox.getSelectionModel().select(-1);
        atributTanlashCombobox.getSelectionModel().select(-1);
        newAtribut.setText("");
        qiymat1.getSelectionModel().select(-1);
        qiymat2.getSelectionModel().select(-1);
        qiymat3.getSelectionModel().select(-1);
        qiymat4.getSelectionModel().select(-1);
    }

    @FXML
    private void btn2Click(){
        if (newQoidaToggle.isSelected()){
           //qoida qoshish
           if (newKasallik.isVisible()){
               if (newKasallik.getText().isEmpty()){
                   showMessage("Xulosa kiriting!");
                   return;
               }

               if (bemordagiBelgilar.isEmpty()){
                   showMessage("Xech qanday atribut tanlanmadi!");
                   return;
               }

               Service.addNewQoida(newKasallik.getText(),null);
               Service.load();
               updateListBox();
               newKasallik.setText("");
               resetComponetValue();
               showMessage("Qoida muffaqqiyatli qo'shildi!");
               return;
           }
           //qoida (zanjir) qoshish
           if (zanjirAtribut.getSelectionModel().getSelectedIndex() == -1){
               showMessage("Atribut tanlanmadi!");
               return;
           }

           if (zanjirQiymat.getSelectionModel().getSelectedIndex() == -1){
               showMessage("Qiymat tanlanmadi !");
               return;
           }

           Service.addNewQoida(zanjirAtribut.getSelectionModel().getSelectedItem(),zanjirQiymat.getSelectionModel().getSelectedItem());
           zanjirQiymat.getSelectionModel().select(-1);
           zanjirQiymat.getSelectionModel().select(-1);
           resetComponetValue();
           initialize();
           showMessage("Qoida muffaqiyatli qo'shildi!");
           return;
       }

       if (bemordagiBelgilar.isEmpty()){
           showMessage("Xech qanday atribut tanlanmadi!");
           return;
       }

        Result result = Service.getResult2(bemordagiBelgilar);

        if (Objects.isNull(result)){
            showMessage("Tizim aniq xulosa berolmadi !");
            resetComponetValue();
            return;
        }

//        if (result.isManyAnswer()){
//            String answers = Service.getAnswers(result.getMulohazalar());
//
//            GridPane pane = new GridPane();
//            Label label = new Label();
//            label.setText(answers);
//            pane.add(label,1,1);
//
//            Dialog<Void> dialog = new Dialog<>();
//            ButtonType buttonType1 = new ButtonType("Ha", ButtonBar.ButtonData.CANCEL_CLOSE);
//            ButtonType buttonType2 = new ButtonType("Yo'q", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//            dialog.getDialogPane().getButtonTypes().add(buttonType1);
//            dialog.getDialogPane().getButtonTypes().add(buttonType2);
//            dialog.setTitle("Message");
//            dialog.setHeaderText("Quyidagilardan birortasi kuzatiladimi ?");
//
//            //System.out.println("result.getMulohazalar() = " + result.getMulohazalar());
//            dialog.setResultConverter(buttonType ->{
//                if (buttonType.equals(buttonType1)){
//                    bemordagiBelgilar.add(result.getMulohazalar().get(0));
//                    btn2Click();
//                }
//
//                if (buttonType.equals(buttonType2)){
//                    Service.addInvalidRole(result.getMulohazalar().get(0).getAtribut(),result.getMulohazalar().get(0).getQiymat());
//                    bemordagiBelgilar.remove(result.getMulohazalar().get(0));
//                    btn2Click();
//                    //resetComponetValue();
//                }
//                dialog.hide();
//                dialog.close();
//                return dialog.getResult();
//            });
//
//            dialog.getDialogPane().setContent(pane);
//            dialog.showAndWait();
//            // showMessage("Yana qo'shimcha atribut qiymat kiriting!");
//            return;
//        }

        if (Objects.nonNull(result.getDiagnosis())){
            belgilar.setText(Service.getSigns());
            tashhis.setText(String.format(ProjectConstants.TASHXIS,result.getDiagnosis().toLowerCase()));
            info.setText(result.getDiseaseInfo());
            help.setText(result.getHelp());
            tabPane.getSelectionModel().select(1);
            tabPane.getTabs().get(1).setDisable(false);
            return;
        }
        showMessage("Tizim aniq xulosa berolmadi !");
        resetComponetValue();

    }

    @FXML
    void newQoidaToggle(){

        if (newQoidaToggle.isSelected()){
            newKasallik.setVisible(true);
            tab1.setText("Qoida qo'shish oynasi");
            zanjirOrKasallikToggle.setVisible(true);
            zanjirOrKasallikToggle.setDisable(false);
            btn2.setText("Qo'shish");
            return;
        }

        zanjirOrKasallikToggle.setSelected(false);
        newKasallik.setText("");
        newKasallik.setVisible(false);
        tab1.setText("Ma'lumotlarni kiritish oynasi");
        zanjirOrKasallikToggle.setVisible(false);
        zanjirOrKasallikToggle.setDisable(true);
        zanjirAtribut.setVisible(false);
        zanjirQiymat.setVisible(false);
        btn2.setText("Xulosa");
    }

    @FXML
    void zanjirOrKasallik(Event event){
        if (!zanjirAtribut.isVisible()){
            newKasallik.setVisible(false);
            zanjirAtribut.setVisible(true);
            zanjirQiymat.setVisible(true);
            zanjirOrKasallikToggle.setText("Off");
            zanjirAtribut.getItems().clear();
            zanjirAtribut.getItems().addAll(Service.getAtributs());
            zanjirQiymat.getItems().clear();
            return;
        }
        newKasallik.setVisible(true);
        zanjirAtribut.setVisible(false);
        zanjirQiymat.setVisible(false);
        zanjirOrKasallikToggle.setText("On");

    }

    @FXML
    void zanjirAtributClick(Event event){
        zanjirQiymat.getItems().clear();
        zanjirQiymat.getItems().addAll(Service.getQiymatlar(zanjirAtribut.getSelectionModel().getSelectedIndex()));
    }


}

