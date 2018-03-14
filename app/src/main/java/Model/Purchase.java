package Model;

/**
 * Created by Henry on 5/2/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Purchase {

    @SerializedName("compra")
    @Expose
    private String compra;
    @SerializedName("buscar")
    @Expose
    private String buscar;
    @SerializedName("impresos")
    @Expose
    private String impresos;
    @SerializedName("cantidad")
    @Expose
    private String cantidad;

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public String getImpresos() {
        return impresos;
    }

    public void setImpresos(String impresos) {
        this.impresos = impresos;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}