package Model;

/**
 * Created by Henry on 23/3/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DVI {

    @SerializedName("DVI_PK")
    @Expose
    private String dVIPK;
    @SerializedName("DVI_PROFK")
    @Expose
    private String dVIPROFK;
    @SerializedName("DVI_MONTOD")
    @Expose
    private String dVIMONTOD;
    @SerializedName("DVI_MONTOB")
    @Expose
    private String dVIMONTOB;
    @SerializedName("DVI_FECHA")
    @Expose
    private String dVIFECHA;
    @SerializedName("DVI_FOTO")
    @Expose
    private String dVIFOTO;

    public String getDVIPK() {
        return dVIPK;
    }

    public void setDVIPK(String dVIPK) {
        this.dVIPK = dVIPK;
    }

    public String getDVIPROFK() {
        return dVIPROFK;
    }

    public void setDVIPROFK(String dVIPROFK) {
        this.dVIPROFK = dVIPROFK;
    }

    public String getDVIMONTOD() {
        return dVIMONTOD;
    }

    public void setDVIMONTOD(String dVIMONTOD) {
        this.dVIMONTOD = dVIMONTOD;
    }

    public String getDVIMONTOB() {
        return dVIMONTOB;
    }

    public void setDVIMONTOB(String dVIMONTOB) {
        this.dVIMONTOB = dVIMONTOB;
    }

    public String getDVIFECHA() {
        return dVIFECHA;
    }

    public void setDVIFECHA(String dVIFECHA) {
        this.dVIFECHA = dVIFECHA;
    }

    public String getDVIFOTO() {
        return dVIFOTO;
    }

    public void setDVIFOTO(String dVIFOTO) {
        this.dVIFOTO = dVIFOTO;
    }

}