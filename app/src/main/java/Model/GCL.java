package Model;

/**
 * Created by Henry on 26/2/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GCL {

    @SerializedName("GCL_PK")
    @Expose
    private String gCLPK;
    @SerializedName("GCL_NOMBRE")
    @Expose
    private String gCLNOMBRE;
    @SerializedName("GCL_CODIGO")
    @Expose
    private String gCLCODIGO;

    public String getGCLPK() {
        return gCLPK;
    }

    public void setGCLPK(String gCLPK) {
        this.gCLPK = gCLPK;
    }

    public String getGCLNOMBRE() {
        return gCLNOMBRE;
    }

    public void setGCLNOMBRE(String gCLNOMBRE) {
        this.gCLNOMBRE = gCLNOMBRE;
    }

    public String getGCLCODIGO() {
        return gCLCODIGO;
    }

    public void setGCLCODIGO(String gCLCODIGO) {
        this.gCLCODIGO = gCLCODIGO;
    }

}