package Model;

/**
 * Created by Henry on 20/3/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CLN {

    @SerializedName("CLN_RIF")
    @Expose
    private String cLNRIF;
    @SerializedName("CLN_NOMBRE")
    @Expose
    private String cLNNOMBRE;
    @SerializedName("CLN_GCLFK")
    @Expose
    private String cLNGCLFK;
    @SerializedName("CLN_TEL")
    @Expose
    private String cLNTEL;
    @SerializedName("CLN_DIR")
    @Expose
    private String cLNDIR;
    @SerializedName("CLN_OBS")
    @Expose
    private String cLNOBS;
    @SerializedName("CLN_EMAIL")
    @Expose
    private String cLNEMAIL;

    public String getCLNRIF() {
        return cLNRIF;
    }

    public void setCLNRIF(String cLNRIF) {
        this.cLNRIF = cLNRIF;
    }

    public String getCLNNOMBRE() {
        return cLNNOMBRE;
    }

    public void setCLNNOMBRE(String cLNNOMBRE) {
        this.cLNNOMBRE = cLNNOMBRE;
    }

    public String getCLNGCLFK() {
        return cLNGCLFK;
    }

    public void setCLNGCLFK(String cLNGCLFK) {
        this.cLNGCLFK = cLNGCLFK;
    }

    public String getCLNTEL() {
        return cLNTEL;
    }

    public void setCLNTEL(String cLNTEL) {
        this.cLNTEL = cLNTEL;
    }

    public String getCLNDIR() {
        return cLNDIR;
    }

    public void setCLNDIR(String cLNDIR) {
        this.cLNDIR = cLNDIR;
    }

    public String getCLNOBS() {
        return cLNOBS;
    }

    public void setCLNOBS(String cLNOBS) {
        this.cLNOBS = cLNOBS;
    }

    public String getCLNEMAIL() {
        return cLNEMAIL;
    }

    public void setCLNEMAIL(String cLNEMAIL) {
        this.cLNEMAIL = cLNEMAIL;
    }

}