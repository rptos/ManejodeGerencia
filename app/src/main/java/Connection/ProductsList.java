package Connection;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import Adapters.ProductsListFragmentAdapter;
import Model.GetPhoto;
import Model.INV;
import Model.PHOTO;
import Model.PedFacCot;
import Model.PrintLabel;
import Model.Purchase;
import Model.Search;
import Model.SearchInInventory;
import Model.VN1;
import Model.Variables;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Henry on 26/1/2018.
 */

public class ProductsList {
    public static List<INV> listProducts;
    public static List<PHOTO> listPhotos;
    public static List<VN1> listVN1;
    public static void getPost(String value, final ListView list, final Context context, final View view, final ImageView p) {

        Search search = new Search();
        search.setValor(value);
        search.setUser(Variables.getId());
        search.setClase("");
        Factory.getIntance()
                .invPost(search).enqueue(new Callback<List<INV>>() {
            @Override
            public void onResponse(Call<List<INV>> call, Response<List<INV>> response) {
                if(response.isSuccessful()) {
                    try {
                        listProducts = response.body();
                        list.setAdapter(new ProductsListFragmentAdapter(context,listProducts));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<INV>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void getPostOrder(String value, String field, String search, final ListView list, final Context context, final View view, final ImageView p) {

        PedFacCot PFC = new PedFacCot();
        PFC.setValor(value);
        PFC.setTipo(field);
        PFC.setBusqueda(search);
        Factory.getIntance()
                .syncInvOrder(PFC).enqueue(new Callback<List<INV>>() {
            @Override
            public void onResponse(Call<List<INV>> call, Response<List<INV>> response) {
                if(response.isSuccessful()) {
                    try {
                        listProducts = response.body();
                        list.setAdapter(new ProductsListFragmentAdapter(context,listProducts));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<List<INV>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void UpdateImage(INV inv, final View view)
    {
        Factory.getIntance()
                .UpdateLocations(inv).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Snackbar.make(view, response.body().string().trim().replace("\"",""), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                catch (Exception x){
                    Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void PrintLabels(PrintLabel printLabel, final View view){
        Factory.getIntance()
                .Print(printLabel).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Snackbar.make(view, response.body().string().trim().replace("\"",""), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                catch (Exception x){
                    Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void BrowserLastPurchase(String value, String search, final ListView list, final Context context, final View view, final ImageView p){
        Purchase purchase = new Purchase();
        purchase.setCompra(value);
        purchase.setBuscar(search);
        Factory.getIntance()
                .getPurchase(purchase).enqueue(new Callback<List<INV>>() {
            @Override
            public void onResponse(Call<List<INV>> call, Response<List<INV>> response) {
                if(response.isSuccessful()) {
                    try {
                        listProducts = response.body();
                        list.setAdapter(new ProductsListFragmentAdapter(context,listProducts));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        /*Toast.makeText(context, "Error de conexion " + x.getMessage(),
                                Toast.LENGTH_LONG).show();*/
                    }
                }
            }

            @Override
            public void onFailure(Call<List<INV>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                /*Toast.makeText(context, "Error de conexion ",
                        Toast.LENGTH_LONG).show();*/
            }
        });

    }

    public static void SaveAudit(Purchase purchase, final View view){
        Factory.getIntance()
                .SavePurchase(purchase).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Snackbar.make(view, response.body().string().trim().replace("\"",""), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                catch (Exception x){
                    Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void getPHOTO(GetPhoto getphoto, final ListView list, final Context context, final View view){
        Factory.getIntance()
                .SavePhoto(getphoto).enqueue(new Callback<List<PHOTO>>() {
            @Override
            public void onResponse(Call<List<PHOTO>> call, Response<List<PHOTO>> response) {
                if(response.isSuccessful()) {
                    try {
                        listPhotos = response.body();
                        //list.setAdapter(new TakePhotoFragmentAdapter(context,listPhotos));
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<List<PHOTO>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
    }

    public static void  saveTakePhoto(PHOTO photo, final GetPhoto getPhoto, final ListView list, final Context context, final View view){
        Factory.getIntance()
                .ImageNew(photo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        getPHOTO(getPhoto, list, context, view);
                        Snackbar.make(view, response.body().string().trim(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void delete_photo(String photo, final GetPhoto getPhoto, final ListView list, final Context context, final View view) {
        Factory.getIntance()
                .DeleteImage(photo).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        getPHOTO(getPhoto, list, context, view);
                        Snackbar.make(view, response.body().string().trim().replace("\"",""), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Snackbar.make(view, "Error al eliminar la imagen", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public static void General_inventory(VN1 vn1, final Spinner sp, final Context context, final View view){
        Factory.getIntance()
                .GeneralInventory(vn1).enqueue(new Callback<List<VN1>>() {

            @Override
            public void onResponse(Call<List<VN1>> call, Response<List<VN1>> response) {
                if(response.isSuccessful()) {
                    try {
                        listVN1 = response.body();
                        String array_spinner[]=new String[ProductsList.listVN1.size()+1];
                        array_spinner[0] = "Sin Auditoria";
                        for (int i = 0; i< ProductsList.listVN1.size(); i++){
                            array_spinner[i+1] = ProductsList.listVN1.get(i).getVN1NOMBRE();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, array_spinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp.setAdapter(adapter);
                        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                //Variables.setAudi(auditoria.getSelectedItem().toString().trim());

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                                // TODO Auto-generated method stub

                            }
                        });
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VN1>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void getPerformInventory(SearchInInventory search, final ListView list, final Context context, final View view) {

        Factory.getIntance()
                .PerformInv(search).enqueue(new Callback<List<INV>>() {
            @Override
            public void onResponse(Call<List<INV>> call, Response<List<INV>> response) {
                if(response.isSuccessful()) {
                    try {
                        listProducts = response.body();
                        list.setAdapter(new ProductsListFragmentAdapter(context,listProducts));
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<INV>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void Saveinventory(VN1 vn1, final View view ){
        Factory.getIntance()
                .SaveInventory(vn1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Snackbar.make(view, response.body().string().trim().replace("\"",""), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                catch (Exception x){
                    Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
