package Model;

/**
 * Created by extre_000 on 9/2/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PRO {

    @SerializedName("PRO_CODIGO")
    @Expose
    private String pROCODIGO;
    @SerializedName("PRO_DVIPK")
    @Expose
    private String pRODVIPK;
    @SerializedName("PRO_EMAIL")
    @Expose
    private String pROEMAIL;
    @SerializedName("PRO_NOMBRE")
    @Expose
    private String pRONOMBRE;
    @SerializedName("PRO_PAGADO")
    @Expose
    private String pROPAGADO;
    @SerializedName("PRO_PK")
    @Expose
    private String pROPK;
    @SerializedName("PRO_SALDOB")
    @Expose
    private String pROSALDOB;
    @SerializedName("PRO_SALDOD")
    @Expose
    private String pROSALDOD;

    public String getPROCODIGO() {
        return pROCODIGO;
    }

    public void setPROCODIGO(String pROCODIGO) {
        this.pROCODIGO = pROCODIGO;
    }

    public String getPRODVIPK() {
        return pRODVIPK;
    }

    public void setPRODVIPK(String pRODVIPK) {
        this.pRODVIPK = pRODVIPK;
    }

    public String getPROEMAIL() {
        return pROEMAIL;
    }

    public void setPROEMAIL(String pROEMAIL) {
        this.pROEMAIL = pROEMAIL;
    }

    public String getPRONOMBRE() {
        return pRONOMBRE;
    }

    public void setPRONOMBRE(String pRONOMBRE) {
        this.pRONOMBRE = pRONOMBRE;
    }

    public String getPROPAGADO() {
        return pROPAGADO;
    }

    public void setPROPAGADO(String pROPAGADO) {
        this.pROPAGADO = pROPAGADO;
    }

    public String getPROPK() {
        return pROPK;
    }

    public void setPROPK(String pROPK) {
        this.pROPK = pROPK;
    }

    public String getPROSALDOB() {
        return pROSALDOB;
    }

    public void setPROSALDOB(String pROSALDOB) {
        this.pROSALDOB = pROSALDOB;
    }

    public String getPROSALDOD() {
        return pROSALDOD;
    }

    public void setPROSALDOD(String pROSALDOD) {
        this.pROSALDOD = pROSALDOD;
    }

}