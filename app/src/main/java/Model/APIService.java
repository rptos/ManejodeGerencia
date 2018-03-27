package Model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Henry on 22/1/2018.
 */

public interface APIService {

    @POST("Servicio.svc/SignIn")
    Call<USR> savePost(@Body USR usr);

    @POST("Servicio.svc/buscar")
    Call<List<INV>> invPost(@Body Search search);

    @POST("Servicio.svc/correlativo")
    Call<ResponseBody> getCorrelative(@Body Search search);

    @POST("Servicio.svc/PedFacCot")
    Call<List<INV>> syncInvOrder(@Body PedFacCot pedFacCot);

    @POST("Servicio.svc/ActUbi")
    Call<ResponseBody> UpdateLocations(@Body INV inv);

    @POST("Servicio.svc/Imprimir")
    Call<ResponseBody> Print(@Body PrintLabel printLabel);

    @POST("Servicio.svc/compra")
    Call<List<INV>> getPurchase(@Body Purchase purchase);

    @POST("Servicio.svc/GuardarCompra")
    Call<ResponseBody> SavePurchase(@Body Purchase purchase);

    @POST("Servicio.svc/Foto")
    Call<List<PHOTO>> SavePhoto(@Body GetPhoto getPhoto);

    @Headers({
            "Accept: application/json",
            "Content-type: application/json",
            "Cache-Control: no-cache"
    })
    @POST("Servicio.svc/ImagenFacPedCotNew")
    Call<ResponseBody> ImageNew(@Body PHOTO photo);

    @POST("Servicio.svc/EliminarImagenFacPedCot/{tipo}")
    Call<ResponseBody> DeleteImage(@Path("tipo") String tipo);

    @POST("Servicio.svc/InvenatrioGeneral")
    Call<List<VN1>> GeneralInventory(@Body VN1 vn1);

    @POST("Servicio.svc/Inventario")
    Call<List<INV>> PerformInv(@Body SearchInInventory searchInInventory);

    @POST("Servicio.svc/GuardarInventario")
    Call<ResponseBody> SaveInventory(@Body VN1 vn1);

    @POST("Servicio.svc/getConsignment")
    Call<List<AUDI>> getConsignment(@Body USR usr);

    @POST("Servicio.svc/getConsignmentProduct")
    Call<List<INV>> getConsignmentProduct(@Body CONS cons);

    @POST("Servicio.svc/setConsignment")
    Call<ResponseBody> setConsignment(@Body CONS cons);

    @POST("Servicio.svc/GrupoxCliente")
    Call<List<GCL>> getGroupCxc();

    @POST("Servicio.svc/Cliente/{pk}")
    Call<List<CLI>> getlistClientsCxc(@Path("pk") String pk, @Body Search search);

    @POST("Servicio.svc/CXC/{pk}")
    Call<List<CXC>> getCxc(@Path("pk") String pk);

    @POST("Servicio.svc/CuentasPagadas")
    Call<ResponseBody> sentPayment(@Body CPA cpa);

    @POST("Servicio.svc/CPA_FOTO/{image}")
    Call<ResponseBody> sentCPAphoto(@Path("image") String image);

    @POST("Servicio.svc/EnviarCXC/{pk}")
    Call<ResponseBody> sendCxc(@Path("pk") String pk);

    @POST("Servicio.svc/ProductosGru/{gru}")
    Call<List<INV>> searchInvGroup(@Path("gru") String gru, @Body Search search);

    @POST("Servicio.svc/EnviarCorreo/{gru}")
    Call<ResponseBody> sentMail(@Path("gru") String gru, @Body SentEmail sentEmail);

    @POST("Servicio.svc/GrupoxClienteCPA")
    Call<List<GCL>> getGroupCpa();

    @POST("Servicio.svc/ClienteCPA/{pk}")
    Call<List<CLI>> getClientCpa(@Path("pk") String pk, @Body Search search);

    @POST("Servicio.svc/DetalleCPA/{pk}")
    Call<List<CPA>> getCpa(@Path("pk") String pk);

    @POST("Servicio.svc/Grupos")
    Call<List<GRU>> syncGRU();

    @POST("Servicio.svc/EliminarCPA/{pk}")
    Call<ResponseBody> DeleteCPA(@Path("pk") String pk);

    @POST("Servicio.svc/EnviarCPA/{pk}")
    Call<ResponseBody> SentCPA(@Path("pk") String pk);

    @POST("Servicio.svc/ClienteN/{pk}")
    Call<List<CLI>> syncNewClients(@Path("pk") String pk, @Body Search search);

    @POST("Servicio.svc/GuardarNuevoCliente")
    Call<ResponseBody> SaveNewClient(@Body CLN cln);

    @POST("Servicio.svc/ProveedoresDVI")
    Call<List<PRO>> syncProviders();

    @POST("Servicio.svc/Proveedores")
    Call<List<PRO>> sync_proAll();

    @POST("Servicio.svc/DivisasPC/{pk}")
    Call<List<DVI>> sync_dvi(@Path("pk") String pk);

    @POST("Servicio.svc/SetDivisas")
    Call<ResponseBody> save_dvi(@Body DVI dvi);

    @POST("Servicio.svc/SetDivisasF/{archive}/{pk}")
    Call<ResponseBody> uploadDVI(@Path("archive") String archive, @Path("pk") String pk,@Body DVI dvi);

}
