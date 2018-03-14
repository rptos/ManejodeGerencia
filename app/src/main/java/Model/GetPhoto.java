package Model;

/**
 * Created by Henry on 7/2/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPhoto {

    @SerializedName("IMP_NUMERO")
    @Expose
    private String iMPNUMERO;
    @SerializedName("IMP_TIPO")
    @Expose
    private String iMPTIPO;

    public String getIMPNUMERO() {
        return iMPNUMERO;
    }

    public void setIMPNUMERO(String iMPNUMERO) {
        this.iMPNUMERO = iMPNUMERO;
    }

    public String getIMPTIPO() {
        return iMPTIPO;
    }

    public void setIMPTIPO(String iMPTIPO) {
        this.iMPTIPO = iMPTIPO;
    }

}