package Model;

/**
 * Created by Henry on 16/2/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VN1 {

    @SerializedName("VN1_INVFK")
    @Expose
    private String vN1INVFK;
    @SerializedName("VN1_PK")
    @Expose
    private String vN1PK;
    @SerializedName("VN1_NOMBRE")
    @Expose
    private String vN1NOMBRE;
    @SerializedName("VN1_FECHA")
    @Expose
    private String vN1FECHA;
    @SerializedName("CONTADOS")
    @Expose
    private String cONTADOS;
    @SerializedName("EXISTENCIA")
    @Expose
    private String eXISTENCIA;

    public String getVN1INVFK() {
        return vN1INVFK;
    }

    public void setVN1INVFK(String vN1INVFK) {
        this.vN1INVFK = vN1INVFK;
    }

    public String getVN1PK() {
        return vN1PK;
    }

    public void setVN1PK(String vN1PK) {
        this.vN1PK = vN1PK;
    }

    public String getVN1NOMBRE() {
        return vN1NOMBRE;
    }

    public void setVN1NOMBRE(String vN1NOMBRE) {
        this.vN1NOMBRE = vN1NOMBRE;
    }

    public String getVN1FECHA() {
        return vN1FECHA;
    }

    public void setVN1FECHA(String vN1FECHA) {
        this.vN1FECHA = vN1FECHA;
    }

    public String getCONTADOS() {
        return cONTADOS;
    }

    public void setCONTADOS(String cONTADOS) {
        this.cONTADOS = cONTADOS;
    }

    public String getEXISTENCIA() {
        return eXISTENCIA;
    }

    public void setEXISTENCIA(String eXISTENCIA) {
        this.eXISTENCIA = eXISTENCIA;
    }

}