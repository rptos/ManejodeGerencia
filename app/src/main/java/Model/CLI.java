package Model;

/**
 * Created by Henry on 26/2/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CLI {

    @SerializedName("CLI_CODIGO")
    @Expose
    private String cLICODIGO;
    @SerializedName("CLI_DIR")
    @Expose
    private Object cLIDIR;
    @SerializedName("CLI_EMAIL")
    @Expose
    private Object cLIEMAIL;
    @SerializedName("CLI_GCLFK")
    @Expose
    private String cLIGCLFK;
    @SerializedName("CLI_NOMBRE")
    @Expose
    private String cLINOMBRE;
    @SerializedName("CLI_PK")
    @Expose
    private String cLIPK;
    @SerializedName("CLI_SALDO")
    @Expose
    private String cLISALDO;
    @SerializedName("CLI_TEL")
    @Expose
    private Object cLITEL;
    @SerializedName("CLI_VDDFK")
    @Expose
    private Object cLIVDDFK;

    public String getCLICODIGO() {
        return cLICODIGO;
    }

    public void setCLICODIGO(String cLICODIGO) {
        this.cLICODIGO = cLICODIGO;
    }

    public Object getCLIDIR() {
        return cLIDIR;
    }

    public void setCLIDIR(Object cLIDIR) {
        this.cLIDIR = cLIDIR;
    }

    public Object getCLIEMAIL() {
        return cLIEMAIL;
    }

    public void setCLIEMAIL(Object cLIEMAIL) {
        this.cLIEMAIL = cLIEMAIL;
    }

    public String getCLIGCLFK() {
        return cLIGCLFK;
    }

    public void setCLIGCLFK(String cLIGCLFK) {
        this.cLIGCLFK = cLIGCLFK;
    }

    public String getCLINOMBRE() {
        return cLINOMBRE;
    }

    public void setCLINOMBRE(String cLINOMBRE) {
        this.cLINOMBRE = cLINOMBRE;
    }

    public String getCLIPK() {
        return cLIPK;
    }

    public void setCLIPK(String cLIPK) {
        this.cLIPK = cLIPK;
    }

    public String getCLISALDO() {
        return cLISALDO;
    }

    public void setCLISALDO(String cLISALDO) {
        this.cLISALDO = cLISALDO;
    }

    public Object getCLITEL() {
        return cLITEL;
    }

    public void setCLITEL(Object cLITEL) {
        this.cLITEL = cLITEL;
    }

    public Object getCLIVDDFK() {
        return cLIVDDFK;
    }

    public void setCLIVDDFK(Object cLIVDDFK) {
        this.cLIVDDFK = cLIVDDFK;
    }

}