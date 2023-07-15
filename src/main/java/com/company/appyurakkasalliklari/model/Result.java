package com.company.appyurakkasalliklari.model;

import java.util.*;

public class Result {
    private String diagnosis;

    private String signs;

    private String diseaseInfo;

    private String help;

    private Boolean manyAnswer;

    private List<MulohazalarItem> mulohazalar;

    public String getDiagnosis() {
        return diagnosis;
    }

    public Result() {
        mulohazalar = new ArrayList<>();
    }

    public Result(String diagnosis, String signs, String diseaseInfo, String help) {
        this.diagnosis = diagnosis;
        this.signs = signs;
        this.diseaseInfo = diseaseInfo;
        this.help = help;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
    }

    public String getDiseaseInfo() {
        return diseaseInfo;
    }

    public void setDiseaseInfo(String diseaseInfo) {
        this.diseaseInfo = diseaseInfo;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public Boolean isManyAnswer() {
        return manyAnswer;
    }

    public void setManyAnswer(Boolean manyAnswer) {
        this.manyAnswer = manyAnswer;
    }

    public Boolean getManyAnswer() {
        return manyAnswer;
    }

    public List<MulohazalarItem> getMulohazalar() {
        return mulohazalar;
    }

    public void  addMulohaza(MulohazalarItem mulohaza){
        if (!mulohazalar.contains(mulohaza)){
            mulohazalar.add(mulohaza);
        }
    }

    public void  addAllMulohaza(List<MulohazalarItem> mulohazalar){
        for (MulohazalarItem item : mulohazalar) {
            addMulohaza(item);
        }
    }

    public void removeAll(List<MulohazalarItem> mulohazalar){
        for (MulohazalarItem item : mulohazalar) {
            if (this.mulohazalar.contains(item)){
                this.mulohazalar.remove(item);
            }
        }
    }

    public void setMulohazalar(List<MulohazalarItem> mulohazalar) {
        this.mulohazalar = mulohazalar;
    }
}
