package Model;

/**
 * Created by Henry on 22/1/2018.
 */

public class Variables {
    private static String bd = "Principal";
    private static String direccion;
    private static String direccion_fotos;
    private static String url_local = "192.168.1.250";
    private static String url_remote = "200.44.165.222";
    private static String url = "192.168.1.250";//default local
    private static String puerto = "4043";
    private static String id = "";
    private static String lanid = "";
    private static String fragment = "";
    private static String cliPK = "";
    private static String gruPK = "";
    private static String type_gruPK = "";
    private static String position;
    private static boolean masVendido = false;
    private static String emailCliN = "";
    private static String idDVI = "";
    private static String idDetalleDVI = "";
    private static int pro_dvi = -1;
    private static String iD="";
    private static String idpro="";
    private static String Position="";
    private static String OptionTypeMenu = "";




    public static String getDireccion() {
        return direccion= "http://"+ Variables.getUrl()+":"+ Variables.getPuerto()+"/";
    }

    public static void setDireccion(String direccion) {
        Variables.direccion = direccion;
    }

    public static String getDireccion_fotos() {
        return direccion_fotos = "http://"+ Variables.getUrl()+":8080/catalogo_smith/image.php?image=";
    }

    public static void setDireccion_fotos(String direccion_fotos) {
        Variables.direccion_fotos = direccion_fotos;
    }

    public static String getUrl() {return url;}

    public static void setUrl(String url) {
        Variables.url = url;}

    public static String getPuerto() {return puerto;}

    public static void setPuerto(String puerto) {
        Variables.puerto = puerto;}

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Variables.id = id;
    }

    public static String getLanid() {
        return lanid;
    }

    public static void setLanid(String lanid) {
        Variables.lanid = lanid;
    }

    public static String getFragment() {
        return fragment;
    }

    public static void setFragment(String fragment) {
        Variables.fragment = fragment;
    }

    public static String getGruPK() {return gruPK;}

    public static void setGruPK(String gruPK) {
        Variables.gruPK = gruPK;
    }

    public static boolean getMasVendido() {
        return masVendido;
    }

    public static void setMasVendido(boolean masVendido) {
        Variables.masVendido = masVendido;
    }

    public static String getType_GruPK() {return type_gruPK;}

    public static void setType_GruPK(String type_gruPK) {
        Variables.type_gruPK = type_gruPK;}

    public static String getPositionGru() {return position;}

    public static void sePositionGru(String pos) { Variables.position = pos; }

    public static String getCliPK() {return cliPK;}

    public static void setCliPK(String cliPK) {
        Variables.cliPK = cliPK;
    }

    public static String getEmailCliN() {
        return emailCliN;
    }

    public static void setEmailCliN(String emailCliN) {
        Variables.emailCliN = emailCliN;
    }

    public static String getIdDVI() {return idDVI; }

    public static void setIdDVI(String idDVI) {
        Variables.idDVI = idDVI;
    }

    public static String getUrl_remote() {return url_remote; }

    public static String getUrl_local() {return url_local; }

    public static int get_pro_dvi() {return pro_dvi; }

    public static void set_pro_dvi(int pro_dvi) {
        Variables.pro_dvi = pro_dvi;
    }

    public static String getIdDetalleDVI() {
        return idDetalleDVI;
    }

    public static void setIdDetalleDVI(String idDetalleDVI) {
        Variables.idDetalleDVI = idDetalleDVI;
    }

    public static String addZero(int valor){
        if(valor<10){
            return "0"+valor;
        }
        else {
            return String.valueOf(valor);
        }
    }

    public static String getiD() {return iD;}

    public static void setiD(String iD) {
        Variables.iD = iD;
    }

    public static String getidpro() {return idpro;}

    public static void setidpro(String idpro) {
        Variables.idpro = idpro;
    }

    public static String getPosition() {return Position;}

    public static void setPosition(String Position) {
        Variables.Position = Position;
    }

    public static String getTypeMenu() {return OptionTypeMenu;}

    public static void setTypeMenu(String OptionTypeMenu) {
        Variables.OptionTypeMenu = OptionTypeMenu;
    }

}
