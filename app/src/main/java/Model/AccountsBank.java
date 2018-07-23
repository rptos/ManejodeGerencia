package Model;

public class AccountsBank {
    /**    FACTURA     **/
    String Mercantil_Factura = "\n\n Banco MERCANTIL: solo depósitos y transferencias\n" +
                                    "Tipo de cuenta: Corriente\n" +
                                    "RIF: J-31103080-8\n" +
                                    "De: Repuestos Coreanos Smith Rodríguez C.A.\n" +
                                    "N° 0105-0075-31-1075322812\n" +
                                    "Correo: administracion@rptos.com\n" +
                                    "Teléfono: 0414-8546014";

    String BNC_Factura  =  "\n\n Banco BNC: solo depósitos y transferencias\n" +
                                "Tipo de cuenta: Corriente\n" +
                                "RIF: J-31103080-8\n" +
                                "De: Repuestos Coreanos Smith Rodríguez C.A.\n" +
                                "N° 0191-0118-52-2100009489\n" +
                                "Correo: administracion@rptos.com\n" +
                                "Teléfono: 0414-8546014";

    String Exterior_Factura  = "\n\n Banco EXTERIOR: solo depósitos y transferencias\n" +
                                    "Tipo de cuenta: Corriente\n" +
                                    "RIF: J-31103080-8\n" +
                                    "De: Repuestos Coreanos Smith Rodríguez C.A.\n" +
                                    "N° 0115-0070-69-1001711009\n" +
                                    "Correo: administracion@rptos.com\n" +
                                    "Teléfono: 0414-8546014";
    /** NOTA DE ENTREGA **/
    String Mercantil_Nota   =  "\n\n Banco MERCANTIL: solo depósitos y transferencias\n" +
                                    "Tipo de cuenta: Corriente\n" +
                                    "C.I: 12.360.365 \n" +
                                    "De: Smith Rodríguez\n" +
                                    "N° 0105-0075-30-1075322987\n" +
                                    "Correo: Repuestoscoreanos85@gmail.com\n" +
                                    "Teléfono: 0424-9734593 / 0414-9064235";

    public String getMercantil_Factura(){return Mercantil_Factura;}
    public String getBNC_Factura(){return BNC_Factura;}
    public String getExterior_Factura(){return Exterior_Factura;}
    public String getMercantil_Nota(){return Mercantil_Nota;}
}
