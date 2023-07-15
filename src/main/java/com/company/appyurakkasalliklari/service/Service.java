package com.company.appyurakkasalliklari.service;

import com.company.appyurakkasalliklari.ComponentContainer.Components;
import com.company.appyurakkasalliklari.constants.ProjectConstants;
import com.company.appyurakkasalliklari.controller.Controller;
import com.company.appyurakkasalliklari.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    public static Set<Integer> invalidRule = new TreeSet<>();
    public static void addNewSign(int atributId, int qiymatId) {
        Atribut atribut = getAtributById(atributId);
        Qiymat qiymat = getQiymatById(qiymatId);

        MulohazalarItem item = new MulohazalarItem();
        item.setAtribut(atribut.getName());
        item.setQiymat(qiymat.getName());

        for (int i = 0; i < Controller.bemordagiBelgilar.size(); i++) {
            if (Controller.bemordagiBelgilar.get(i).getAtribut()==atribut.getName()){
                Controller.bemordagiBelgilar.get(i).setQiymat(qiymat.getName());
                return;
            }
        }
        if (!Controller.bemordagiBelgilar.contains(item)){
            Controller.bemordagiBelgilar.add(item);
        }
    }

    public static Atribut getAtributById(int id){
        for (Atribut atribut : Components.atributList) {
            if (atribut.getId() == id) return atribut;
        }
        return null;
    }

    public static Qiymat getQiymatById(int id){
        for (Qiymat qiymat : Components.qiymatList) {
            if (qiymat.getId() == id) return qiymat;
        }
        return null;
    }

    public static Result getResult2(List<MulohazalarItem> bermordagiBelgilar) {

        if (bermordagiBelgilar.isEmpty()) return null;

        if (invalidRule.size() == Components.qoidaList.size()) return null;

        for (int i = 0; i < Components.qoidaList.size(); i++) {
            Qoida qoida = Components.qoidaList.get(i);
            List<MulohazalarItem> mulohazalar = Components.qoidaList.get(i).getMulohazalar();
            if (qoida.getZanjir() && bermordagiBelgilar.containsAll(mulohazalar)){
                MulohazalarItem item = new MulohazalarItem();
                item.setAtribut(qoida.getXulosa());
                item.setQiymat(qoida.getXulosaQiymat());
                bermordagiBelgilar.clear();
                bermordagiBelgilar.add(item);
            }
        }

        Qoida res = new Qoida();
        Result result = new Result();

        int max1 = 0;
        int max2 = 0;

        for (int i = 0; i < Components.qoidaList.size(); i++) {

            Qoida qoida = Components.qoidaList.get(i);
            if (invalidRule.contains(qoida.getId())) continue;

            //if (qoida.getZanjir()) continue;
            List<MulohazalarItem> mulohazalar = qoida.getMulohazalar();
            max2 = 0;
            for (int j = 0; j < qoida.getMulohazalar().size(); j++) {
                if (bermordagiBelgilar.contains(mulohazalar.get(j))) max2++;
            }

            if (max1 < max2) {
                max1 = max2;
                res = qoida;
            } else if (max1 == max2 && max2 != 0) {
                result.addAllMulohaza(mulohazalar);
            }
            if (max2 == mulohazalar.size() && !qoida.getZanjir()){
                res = qoida;
                break;
            }
        }

        if (Objects.nonNull(res.getXulosa())){
            result.setDiagnosis(res.getXulosa());

            for (Kasallik kasallik : Components.kasallikList) {
                if (kasallik.getName().equals(res.getXulosa())){
                    result.setHelp(kasallik.getHelp());
                    result.setDiseaseInfo(kasallik.getAbout());
                    return result;
                }
            }
        }

        return result;
    }

    public static String writeToList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Qoida qoida : Components.qoidaList) {
            stringBuilder.append(qoida.getId()).append(".QOIDA:\n").append("AGAR\n");
            for (MulohazalarItem mulohazalarItem : qoida.getMulohazalar()) {
                stringBuilder.append(mulohazalarItem.getAtribut()).append("=").append(mulohazalarItem.getQiymat()).append(" VA\n");
            }
            stringBuilder.replace(stringBuilder.lastIndexOf("VA"),stringBuilder.lastIndexOf("VA")+2,"bo'lsa");
            if (qoida.getZanjir()){
                stringBuilder.append("U holda ").append(qoida.getXulosa()).append("=").append(qoida.getXulosaQiymat()).append("\n\n");
            }else {
                stringBuilder.append("U holda kasallik=").append(qoida.getXulosa()).append("\n\n");
            }
        }
        return stringBuilder.toString();
    }

    public static void load() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type1 = new TypeToken<List<Qoida>>(){}.getType();
        Type type2 = new TypeToken<List<AtributQiymat>>(){}.getType();
        Type type3 = new TypeToken<List<Kasallik>>(){}.getType();
        Type type4 = new TypeToken<List<Atribut>>(){}.getType();
        Type type5 = new TypeToken<List<Qiymat>>(){}.getType();

        try {
            Reader reader1 = new BufferedReader(new FileReader(ProjectConstants.QOIDA_URL));
            Reader reader2 = new BufferedReader(new FileReader(ProjectConstants.ATRIBUT_QIYMAT_URL));
            Reader reader3 = new BufferedReader(new FileReader(ProjectConstants.KASALLIKLAR_URL));
            Reader reader4 = new BufferedReader(new FileReader(ProjectConstants.ATRIBUT_URL));
            Reader reader5 = new BufferedReader(new FileReader(ProjectConstants.QIYMAT_URL));

            Components.qoidaList = gson.fromJson(reader1,type1);
            Components.atributQiymatList = gson.fromJson(reader2,type2);
            Components.kasallikList = gson.fromJson(reader3,type3);
            Components.atributList = gson.fromJson(reader4,type4);
            Components.qiymatList = gson.fromJson(reader5,type5);

            reader1.close();
            reader2.close();
            reader3.close();
            reader4.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String[] getQoidalar() {
       String []qoidalar = new String[Components.qoidaList.size()];

        for (int i = 0; i < qoidalar.length; i++) {
            qoidalar[i] = String.format("%s - %s",i+1,"QOIDAGA QO'SHISH");
        }
        return qoidalar;
    }

    public static String[] getQiymatlar(int index) {
        String []qiymatlar;

        if (index == -1){
            qiymatlar = new String[Components.qiymatList.size()];
            for (int i = 0; i < qiymatlar.length; i++) {
                qiymatlar[i] = Components.qiymatList.get(i).getName();
            }
            return qiymatlar;
        }else if (index > -1){
            List<String> qiymat = Components.atributQiymatList.get(index).getQiymat();
            qiymatlar = new String[qiymat.size()];

            for (int i = 0; i < qiymat.size(); i++) {
                qiymatlar[i] = qiymat.get(i);
            }

            return qiymatlar;
        }
        return null;
    }

    public static String[] getAtributs() {
        String []atributs = new String[Components.atributList.size()];

        for (int i = 0; i < atributs.length; i++) {
            atributs[i] = Components.atributList.get(i).getName();
        }
        return atributs;
    }

    public static boolean addNewValue(String text) {

        text = text.toLowerCase();
        for (Qiymat qiymat : Components.qiymatList) {
            if (qiymat.getName().equals(text)) return false;
        }

        Qiymat qiymat = new Qiymat();
        qiymat.setName(text);
        qiymat.setId(Components.qiymatList.size()+1);

        Components.qiymatList.add(qiymat);
        writeToFile(ProjectConstants.QIYMAT_URL,Components.qiymatList);
        return true;
    }

    public static boolean addNewAtributQiymat(int qoidaId,int position,String atribut, String ...qiymatlar){
        int oparation = 1;
        int atributId = -1;

        for (Atribut atribut1 : Components.atributList) {
            if (atribut1.getName().equals(atribut)) {
                oparation = -1;
                atributId = atribut1.getId();
                break;
            }
        }

        switch (oparation){
            case -1:{
                //bunday atribut mavjud, atribut qiymat fayliga qiymatlarni qo'shamiz
                Set<String> qiymats = new TreeSet<>(Components.atributQiymatList.get(atributId - 1).getQiymat());
                qiymats.addAll(Arrays.asList(qiymatlar));
                Components.atributQiymatList.get(atributId-1).setQiymat(new ArrayList<>(qiymats));

                writeToFile(ProjectConstants.ATRIBUT_QIYMAT_URL,Components.atributQiymatList);
            };break;

            case 1:{
                Atribut atribut1 = new Atribut();
                atribut1.setName(atribut);
                atribut1.setId(Components.atributList.size()+1);
                Components.atributList.add(atribut1);

                AtributQiymat atributQiymat = new AtributQiymat();
                atributQiymat.setAtribut(atribut);
                atributQiymat.setQiymat(Arrays.asList(qiymatlar));
                atributQiymat.setId(Components.atributQiymatList.size()+1);
                Components.atributQiymatList.add(atributQiymat);

                if (qoidaId != -1){
                    Qoida qoida = Components.qoidaList.get(qoidaId);
                    List<MulohazalarItem> mulohazalar = qoida.getMulohazalar();
                    MulohazalarItem item = new MulohazalarItem();

                    item.setQiymat(qiymatlar[position-1]);
                    item.setAtribut(atribut);
                    mulohazalar.add(item);
                    qoida.setMulohazalar(mulohazalar);
                    Components.qoidaList.set(qoidaId,qoida);
                }

                writeToFile(ProjectConstants.ATRIBUT_URL,Components.atributList);
                writeToFile(ProjectConstants.ATRIBUT_QIYMAT_URL,Components.atributQiymatList);

                if (qoidaId != -1){
                    writeToFile(ProjectConstants.QOIDA_URL,Components.qoidaList);
                }

            };break;
        }

        if (qoidaId != -1){
            Qoida qoida = Components.qoidaList.get(qoidaId);
            List<MulohazalarItem> mulohazalar = qoida.getMulohazalar();

            boolean have = false;

            for (int i = 0; i < mulohazalar.size(); i++) {
                if (mulohazalar.get(i).getAtribut().equals(atribut)) {
                    mulohazalar.get(i).setQiymat(qiymatlar[position-1]);
                    have = true;
                    break;
                }
            }

            if (!have){
                MulohazalarItem item = new MulohazalarItem();
                item.setQiymat(qiymatlar[position-1]);
                item.setAtribut(atribut);
                mulohazalar.add(item);
            }
            qoida.setMulohazalar(mulohazalar);
            Components.qoidaList.set(qoidaId,qoida);
        }

        return oparation == 1;
    }

    public static String getSigns() {
        StringBuilder stringBuilder = new StringBuilder();
        Set<MulohazalarItem> collect = Controller.bemordagiBelgilar.stream().collect(Collectors.toSet());
        for (MulohazalarItem item : collect) {
            stringBuilder.append(item.getAtribut()).append("=").append(item.getQiymat()).append(",");
        }
        return stringBuilder.substring(0,stringBuilder.lastIndexOf(","));
    }

    public static void addNewQoida(String kasallik, String xulosaQiymat) {

        Qoida qoida = new Qoida();
        qoida.setId(Components.qoidaList.size()+1);
        qoida.setXulosa(kasallik);
        qoida.setZanjir(Objects.nonNull(xulosaQiymat));
        qoida.setXulosaQiymat(xulosaQiymat);
        qoida.setMulohazalar(Controller.bemordagiBelgilar);

        Components.qoidaList.add(qoida);

        Kasallik kasallik1 = new Kasallik();
        kasallik1.setName(kasallik);
        Components.kasallikList.add(kasallik1);

        writeToFile(ProjectConstants.QOIDA_URL,Components.qoidaList);
        writeToFile(ProjectConstants.KASALLIKLAR_URL,Components.kasallikList);

    }

    public static <T> void writeToFile(String url,List<T>list){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(url));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String toJson = gson.toJson(list);
            writer.write(toJson);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAnswers(List<MulohazalarItem> mulohazalar) {
        StringBuilder stringBuilder = new StringBuilder();
//        int number = 1;
//        for (MulohazalarItem item : mulohazalar) {
//            stringBuilder.append(number).append(".").append(item.getAtribut()).append("=").append(item.getQiymat()).append("\n\n");
//            number++;
//        }

        return stringBuilder.append(mulohazalar.get(0).getAtribut()).append(" ").append(mulohazalar.get(0).getQiymat()).append("mi ?\n\n").toString();
//        return stringBuilder.toString();
    }

    public static void addInvalidRole(String atribut, String qiymat) {
        MulohazalarItem invalidItem = new MulohazalarItem();
        invalidItem.setAtribut(atribut);
        invalidItem.setQiymat(qiymat);
        for (Qoida qoida : Components.qoidaList) {
           if (qoida.getMulohazalar().contains(invalidItem)){
               invalidRule.add(qoida.getId());
           }
        }
    }
}
