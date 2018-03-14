package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Henry on 2/2/2018.
 */

public class PrintLabel {

    @SerializedName("pk")
    @Expose
    private String pk;
    @SerializedName("tamanio")
    @Expose
    private String tamanio;
    @SerializedName("cantidad")
    @Expose
    private String cantidad;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}