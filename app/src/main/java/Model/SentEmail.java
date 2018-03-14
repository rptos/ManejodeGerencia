package Model;

/**
 * Created by Henry on 6/3/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SentEmail {

    @SerializedName("valor")
    @Expose
    private String valor;
    @SerializedName("pocentaje")
    @Expose
    private String pocentaje;
    @SerializedName("cliente")
    @Expose
    private String cliente;
    @SerializedName("asunto")
    @Expose
    private String asunto;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("clase")
    @Expose
    private String clase;
    @SerializedName("masVendido")
    @Expose
    private String masVendido;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPocentaje() {
        return pocentaje;
    }

    public void setPocentaje(String pocentaje) {
        this.pocentaje = pocentaje;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMasVendido() {
        return masVendido;
    }

    public void setMasVendido(String masVendido) {
        this.masVendido = masVendido;
    }

}