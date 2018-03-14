package Model;

/**
 * Created by Henry on 2/3/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CPA {

    @SerializedName("CPA_PK")
    @Expose
    private String cPAPK;
    @SerializedName("CPA_CLIFK")
    @Expose
    private String cPACLIFK;
    @SerializedName("CPA_RIF")
    @Expose
    private String cPARIF;
    @SerializedName("CPA_NOMBRE")
    @Expose
    private String cPANOMBRE;
    @SerializedName("CPA_MONTO")
    @Expose
    private String cPAMONTO;
    @SerializedName("CPA_MENSAJE")
    @Expose
    private String cPAMENSAJE;
    @SerializedName("CPA_FOTO")
    @Expose
    private String cPAFOTO;
    @SerializedName("CPA_FECHA")
    @Expose
    private String cPAFECHA;

    public String getCPAPK() {
        return cPAPK;
    }

    public void setCPAPK(String cPAPK) {
        this.cPAPK = cPAPK;
    }

    public String getCPACLIFK() {
        return cPACLIFK;
    }

    public void setCPACLIFK(String cPACLIFK) {
        this.cPACLIFK = cPACLIFK;
    }

    public String getCPARIF() {
        return cPARIF;
    }

    public void setCPARIF(String cPARIF) {
        this.cPARIF = cPARIF;
    }

    public String getCPANOMBRE() {
        return cPANOMBRE;
    }

    public void setCPANOMBRE(String cPANOMBRE) {
        this.cPANOMBRE = cPANOMBRE;
    }

    public String getCPAMONTO() {
        return cPAMONTO;
    }

    public void setCPAMONTO(String cPAMONTO) {
        this.cPAMONTO = cPAMONTO;
    }

    public String getCPAMENSAJE() {
        return cPAMENSAJE;
    }

    public void setCPAMENSAJE(String cPAMENSAJE) {
        this.cPAMENSAJE = cPAMENSAJE;
    }

    public String getCPAFOTO() {
        return cPAFOTO;
    }

    public void setCPAFOTO(String cPAFOTO) {
        this.cPAFOTO = cPAFOTO;
    }

    public String getCPAFECHA() {
        return cPAFECHA;
    }

    public void setCPAFECHA(String cPAFECHA) {
        this.cPAFECHA = cPAFECHA;
    }

}