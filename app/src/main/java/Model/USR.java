package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Henry on 22/1/2018.
 */

public class USR {
    public USR(String lanid, String pk){
        setUSRLANID(lanid);
        setUSRNOTES(pk);
    }

    @SerializedName("USR_LANID")
    @Expose
    private String uSRLANID;

    @SerializedName("USR_PK")
    @Expose
    private String uSRPK;

    public String getUSRLANID() {
        return uSRLANID;
    }

    public void setUSRLANID(String uSRLANID) {
        this.uSRLANID = uSRLANID;
    }

    public String getUSRNOTES() {
        return uSRPK;
    }

    public void setUSRNOTES(String uSRNOTES) {
        this.uSRPK = uSRNOTES;
    }

    @Override
    public String toString() {
        return "USR{" +
                "USR_LANID='" + uSRLANID + '\'' +
                ", USR_PK='" + uSRPK +
                "'}";
    }
}
