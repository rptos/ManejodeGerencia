package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Henry on 26/1/2018.
 */

public class INV {
    public INV(String name, String code,String existence){
        setINVNOMBRE(name);
        setINVCODIGO(code);
        setINVEXISTENCIA(existence);
    }
    public  INV(){

    }

    @SerializedName("AIN_ALMFK")
    @Expose
    private String aINALMFK;
    @SerializedName("AIN_PK")
    @Expose
    private String aINPK;
    @SerializedName("AIN_UBI1")
    @Expose
    private String aINUBI1;
    @SerializedName("AIN_UBI2")
    @Expose
    private String aINUBI2;
    @SerializedName("AIN_UBI3")
    @Expose
    private String aINUBI3;
    @SerializedName("AIN_UBI4")
    @Expose
    private String aINUBI4;
    @SerializedName("AIN_UBI5")
    @Expose
    private String aINUBI5;
    @SerializedName("AIN_UBI6")
    @Expose
    private String aINUBI6;
    @SerializedName("CONTADOS")
    @Expose
    private Object cONTADOS;
    @SerializedName("EXISTENCIA_ACTUAL")
    @Expose
    private Object eXISTENCIAACTUAL;
    @SerializedName("INV_CLASE")
    @Expose
    private Object iNVCLASE;
    @SerializedName("INV_CODIGO")
    @Expose
    private String iNVCODIGO;
    @SerializedName("INV_EXISTENCIA")
    @Expose
    private String iNVEXISTENCIA;
    @SerializedName("INV_FOTO")
    @Expose
    private String iNVFOTO;
    @SerializedName("INV_GRUFK")
    @Expose
    private Object iNVGRUFK;
    @SerializedName("INV_MARFK")
    @Expose
    private Object iNVMARFK;
    @SerializedName("INV_NOMBRE")
    @Expose
    private String iNVNOMBRE;
    @SerializedName("INV_PK")
    @Expose
    private Integer iNVPK;
    @SerializedName("INV_PRECIO1")
    @Expose
    private String iNVPRECIO1;
    @SerializedName("INV_PRECIO3")
    @Expose
    private String iNVPRECIO3;
    @SerializedName("INV_PRECIO6")
    @Expose
    private String iNVPRECIO6;
    @SerializedName("user")
    @Expose
    private Object user;

    public String getAINALMFK() {
        return aINALMFK;
    }

    public void setAINALMFK(String aINALMFK) {
        this.aINALMFK = aINALMFK;
    }

    public String getAINPK() {
        return aINPK;
    }

    public void setAINPK(String aINPK) {
        this.aINPK = aINPK;
    }

    public String getAINUBI1() {
        return aINUBI1;
    }

    public void setAINUBI1(String aINUBI1) {
        this.aINUBI1 = aINUBI1;
    }

    public String getAINUBI2() {
        return aINUBI2;
    }

    public void setAINUBI2(String aINUBI2) {
        this.aINUBI2 = aINUBI2;
    }

    public String getAINUBI3() {
        return aINUBI3;
    }

    public void setAINUBI3(String aINUBI3) {
        this.aINUBI3 = aINUBI3;
    }

    public String getAINUBI4() {
        return aINUBI4;
    }

    public void setAINUBI4(String aINUBI4) {
        this.aINUBI4 = aINUBI4;
    }

    public String getAINUBI5() {
        return aINUBI5;
    }

    public void setAINUBI5(String aINUBI5) {
        this.aINUBI5 = aINUBI5;
    }

    public String getAINUBI6() {
        return aINUBI6;
    }

    public void setAINUBI6(String aINUBI6) {
        this.aINUBI6 = aINUBI6;
    }

    public Object getCONTADOS() {
        return cONTADOS;
    }

    public void setCONTADOS(Object cONTADOS) {
        this.cONTADOS = cONTADOS;
    }

    public Object getEXISTENCIAACTUAL() {
        return eXISTENCIAACTUAL;
    }

    public void setEXISTENCIAACTUAL(Object eXISTENCIAACTUAL) {
        this.eXISTENCIAACTUAL = eXISTENCIAACTUAL;
    }

    public Object getINVCLASE() {
        return iNVCLASE;
    }

    public void setINVCLASE(Object iNVCLASE) {
        this.iNVCLASE = iNVCLASE;
    }

    public String getINVCODIGO() {
        return iNVCODIGO;
    }

    public void setINVCODIGO(String iNVCODIGO) {
        this.iNVCODIGO = iNVCODIGO;
    }

    public String getINVEXISTENCIA() {
        return iNVEXISTENCIA;
    }

    public void setINVEXISTENCIA(String iNVEXISTENCIA) {
        this.iNVEXISTENCIA = iNVEXISTENCIA;
    }

    public String getINVFOTO() {
        return iNVFOTO;
    }

    public void setINVFOTO(String iNVFOTO) {
        this.iNVFOTO = iNVFOTO;
    }

    public Object getINVGRUFK() {
        return iNVGRUFK;
    }

    public void setINVGRUFK(Object iNVGRUFK) {
        this.iNVGRUFK = iNVGRUFK;
    }

    public Object getINVMARFK() {
        return iNVMARFK;
    }

    public void setINVMARFK(Object iNVMARFK) {
        this.iNVMARFK = iNVMARFK;
    }

    public String getINVNOMBRE() {
        return iNVNOMBRE;
    }

    public void setINVNOMBRE(String iNVNOMBRE) {
        this.iNVNOMBRE = iNVNOMBRE;
    }

    public Integer getINVPK() {
        return iNVPK;
    }

    public void setINVPK(Integer iNVPK) {
        this.iNVPK = iNVPK;
    }

    public String getINVPRECIO1() {
        return iNVPRECIO1;
    }

    public void setINVPRECIO1(String iNVPRECIO1) {
        this.iNVPRECIO1 = iNVPRECIO1;
    }

    public String getINVPRECIO3() {
        return iNVPRECIO3;
    }

    public void setINVPRECIO3(String iNVPRECIO3) {
        this.iNVPRECIO3 = iNVPRECIO3;
    }

    public String getINVPRECIO6() {
        return iNVPRECIO6;
    }

    public void setINVPRECIO6(String iNVPRECIO6) {
        this.iNVPRECIO6 = iNVPRECIO6;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

}



