package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CONS {

    @SerializedName("V02_PK")
    @Expose
    private Integer v02PK;
    @SerializedName("CODIGO")
    @Expose
    private String cODIGO;
    @SerializedName("NOMBRE")
    @Expose
    private String nOMBRE;
    @SerializedName("NUMERO")
    @Expose
    private String nUMERO;
    @SerializedName("CANTIDAD")
    @Expose
    private String cANTIDAD;

    public Integer getV02PK() {
        return v02PK;
    }

    public void setV02PK(Integer v02PK) {
        this.v02PK = v02PK;
    }

    public String getCODIGO() {
        return cODIGO;
    }

    public void setCODIGO(String cODIGO) {
        this.cODIGO = cODIGO;
    }

    public String getNOMBRE() {
        return nOMBRE;
    }

    public void setNOMBRE(String nOMBRE) {
        this.nOMBRE = nOMBRE;
    }

    public String getNUMERO() {
        return nUMERO;
    }

    public void setNUMERO(String nUMERO) {
        this.nUMERO = nUMERO;
    }

    public String getCANTIDAD() {
        return cANTIDAD;
    }

    public void setCANTIDAD(String cANTIDAD) {
        this.cANTIDAD = cANTIDAD;
    }

}

