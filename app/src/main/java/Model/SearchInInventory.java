package Model;

/**
 * Created by Henry on 19/2/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchInInventory {

    @SerializedName("fechai")
    @Expose
    private String fechai;
    @SerializedName("fechaf")
    @Expose
    private String fechaf;
    @SerializedName("buscar")
    @Expose
    private String buscar;
    @SerializedName("user")
    @Expose
    private String user;

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}