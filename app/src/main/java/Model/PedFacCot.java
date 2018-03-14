package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PedFacCot {

    @SerializedName("valor")
    @Expose
    private String valor;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("busqueda")
    @Expose
    private String busqueda;

    public String getValor() {return valor;}

    public void setValor(String valor) {this.valor = valor;}

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getBusqueda() {return busqueda;}

    public void setBusqueda(String busqueda) {this.busqueda = busqueda;}

}