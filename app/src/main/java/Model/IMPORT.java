package Model;

/**
 * Created by Henry on 6/3/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IMPORT {

    @SerializedName("dateI")
    @Expose
    private String dateI;
    @SerializedName("dateF")
    @Expose
    private String dateF;
    @SerializedName("order")
    @Expose
    private String order;
    @SerializedName("id")
    @Expose
    private String id;

    public String getDateI() {
        return dateI;
    }

    public void setDateI(String dateI) {
        this.dateI = dateI;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}