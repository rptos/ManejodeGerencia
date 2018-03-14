package Model;

/**
 * Created by Henry on 26/2/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CXC {

    @SerializedName("CXC_CLIPK")
    @Expose
    private String cXCCLIPK;
    @SerializedName("CXC_CODIGO")
    @Expose
    private String cXCCODIGO;
    @SerializedName("CXC_EMAIL")
    @Expose
    private String cXCEMAIL;
    @SerializedName("CXC_FACTURA")
    @Expose
    private String cXCFACTURA;
    @SerializedName("CXC_FECHA")
    @Expose
    private String cXCFECHA;
    @SerializedName("CXC_GCLFK")
    @Expose
    private String cXCGCLFK;
    @SerializedName("CXC_NOMBRE")
    @Expose
    private String cXCNOMBRE;
    @SerializedName("CXC_PK")
    @Expose
    private String cXCPK;
    @SerializedName("CXC_SALDO")
    @Expose
    private String cXCSALDO;
    @SerializedName("CXC_VENCE")
    @Expose
    private String cXCVENCE;

    public String getCXCCLIPK() {
        return cXCCLIPK;
    }

    public void setCXCCLIPK(String cXCCLIPK) {
        this.cXCCLIPK = cXCCLIPK;
    }

    public String getCXCCODIGO() {
        return cXCCODIGO;
    }

    public void setCXCCODIGO(String cXCCODIGO) {
        this.cXCCODIGO = cXCCODIGO;
    }

    public String getCXCEMAIL() {
        return cXCEMAIL;
    }

    public void setCXCEMAIL(String cXCEMAIL) {
        this.cXCEMAIL = cXCEMAIL;
    }

    public String getCXCFACTURA() {
        return cXCFACTURA;
    }

    public void setCXCFACTURA(String cXCFACTURA) {
        this.cXCFACTURA = cXCFACTURA;
    }

    public String getCXCFECHA() {
        return cXCFECHA;
    }

    public void setCXCFECHA(String cXCFECHA) {
        this.cXCFECHA = cXCFECHA;
    }

    public String getCXCGCLFK() {
        return cXCGCLFK;
    }

    public void setCXCGCLFK(String cXCGCLFK) {
        this.cXCGCLFK = cXCGCLFK;
    }

    public String getCXCNOMBRE() {
        return cXCNOMBRE;
    }

    public void setCXCNOMBRE(String cXCNOMBRE) {
        this.cXCNOMBRE = cXCNOMBRE;
    }

    public String getCXCPK() {
        return cXCPK;
    }

    public void setCXCPK(String cXCPK) {
        this.cXCPK = cXCPK;
    }

    public String getCXCSALDO() {
        return cXCSALDO;
    }

    public void setCXCSALDO(String cXCSALDO) {
        this.cXCSALDO = cXCSALDO;
    }

    public String getCXCVENCE() {
        return cXCVENCE;
    }

    public void setCXCVENCE(String cXCVENCE) {
        this.cXCVENCE = cXCVENCE;
    }

}