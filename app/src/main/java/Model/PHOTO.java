package Model;

/**
 * Created by extre_000 on 25-06-2015.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PHOTO {

    @SerializedName("IMP_NUMERO")
    @Expose
    private String iMPNUMERO;
    @SerializedName("IMP_PK")
    @Expose
    private String iMPPK;
    @SerializedName("IMP_TIPO")
    @Expose
    private String iMPTIPO;
    @SerializedName("IMP_FOTO")
    @Expose
    private String iMPFOTO;

    public String getIMPFOTO() {
        return iMPFOTO;
    }

    public void setIMPFOTO(String iMPFOTO) {
        this.iMPFOTO = iMPFOTO;
    }

    public String getIMPNUMERO() {
        return iMPNUMERO;
    }

    public void setIMPNUMERO(String iMPNUMERO) {
        this.iMPNUMERO = iMPNUMERO;
    }

    public String getIMPPK() {
        return iMPPK;
    }

    public void setIMPPK(String iMPPK) {
        this.iMPPK = iMPPK;
    }

    public String getIMPTIPO() {
        return iMPTIPO;
    }

    public void setIMPTIPO(String iMPTIPO) {
        this.iMPTIPO = iMPTIPO;
    }

}