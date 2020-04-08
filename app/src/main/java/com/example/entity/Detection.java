package com.example.entity;

import java.util.Date;

/**
 * 实体类
 *
 * @author jinpeng_chen
 * @create 2020-03-31 下午 9:06
 **/

public class Detection {
    private Integer id;
    private Double O2;
    private Double CO2;
    private Double PH;
    private Double WD;
    private Double XI;
    private Double GE;
    private Date date;

    @Override
    public String toString() {
        return "Detection{" +
                "id=" + id +
                ", O2=" + O2 +
                ", CO2=" + CO2 +
                ", PH=" + PH +
                ", WD=" + WD +
                ", XI=" + XI +
                ", GE=" + GE +
                ", date=" + date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getO2() {
        return O2;
    }

    public void setO2(Double o2) {
        O2 = o2;
    }

    public Double getCO2() {
        return CO2;
    }

    public void setCO2(Double CO2) {
        this.CO2 = CO2;
    }

    public Double getPH() {
        return PH;
    }

    public void setPH(Double PH) {
        this.PH = PH;
    }

    public Double getWD() {
        return WD;
    }

    public void setWD(Double WD) {
        this.WD = WD;
    }

    public Double getXI() {
        return XI;
    }

    public void setXI(Double XI) {
        this.XI = XI;
    }

    public Double getGE() {
        return GE;
    }

    public void setGE(Double GE) {
        this.GE = GE;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Detection(Integer id, Double o2, Double CO2, Double PH, Double WD, Double XI, Double GE, Date date) {
        this.id = id;
        O2 = o2;
        this.CO2 = CO2;
        this.PH = PH;
        this.WD = WD;
        this.XI = XI;
        this.GE = GE;
        this.date = date;
    }

    public Detection() {
    }
}
