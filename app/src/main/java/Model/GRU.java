package Model;

/**
 * Created by Henry on 9/3/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GRU {

    @SerializedName("GRU_CODIGO")
    @Expose
    private String gRUCODIGO;
    @SerializedName("GRU_NOMBRE")
    @Expose
    private String gRUNOMBRE;
    @SerializedName("GRU_PK")
    @Expose
    private String gRUPK;

    public String getGRUCODIGO() {
        return gRUCODIGO;
    }

    public void setGRUCODIGO(String gRUCODIGO) {
        this.gRUCODIGO = gRUCODIGO;
    }

    public String getGRUNOMBRE() {
        return gRUNOMBRE;
    }

    public void setGRUNOMBRE(String gRUNOMBRE) {
        this.gRUNOMBRE = gRUNOMBRE;
    }

    public String getGRUPK() {
        return gRUPK;
    }

    public void setGRUPK(String gRUPK) {
        this.gRUPK = gRUPK;
    }

}