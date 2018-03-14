package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AUDI {

    @SerializedName("V02_PK")
    @Expose
    private Integer v02PK;
    @SerializedName("V02_NUMERO")
    @Expose
    private String v02NUMERO;
    @SerializedName("V02_FECHA")
    @Expose
    private String v02FECHA;

    public Integer getV02PK() {
        return v02PK;
    }

    public void setV02PK(Integer v02PK) {
        this.v02PK = v02PK;
    }

    public String getV02NUMERO() {
        return v02NUMERO;
    }

    public void setV02NUMERO(String v02NUMERO) {
        this.v02NUMERO = v02NUMERO;
    }

    public String getV02FECHA() {
        return v02FECHA;
    }

    public void setV02FECHA(String v02FECHA) {
        this.v02FECHA = v02FECHA;
    }

}
