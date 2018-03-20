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

import Adapters.AccountsReceivableGroupFragmentAdapter;
import Adapters.ClientDetailActivityAdapter;
import Adapters.ClientListFragmentAdapter;
import Adapters.ListCpaAdapter;
import Adapters.ProductsListFragmentAdapter;
import Adapters.ProviderDVIFragmentAdapter;
import Model.CLI;
import Model.CLN;
import Model.CPA;
import Model.CXC;
import Model.GCL;
import Model.GRU;
import Model.INV;
import Model.PRO;
import Model.Search;
import Model.SentEmail;
import Model.Variables;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Henry on 26/2/2018.
 */

public class Accounts {
    public static List<GCL> listGroup;
    public static List<CLI> listClient;
    public static List<CXC> listCXC;
    public static List<CPA> listCPA;
    public static List<GRU> listGRU;
    public static List<PRO> listPRO;

    public static void getGroupsCxc(final ListView list, final Context context, final View view, final ImageView p) {

        Factory.getIntance()
                .getGroupCxc().enqueue(new Callback<List<GCL>>() {
            @Override
            public void onResponse(Call<List<GCL>> call, Response<List<GCL>> response) {
                if(response.isSuccessful()) {
                    try {
                        listGroup = response.body();
                        list.setAdapter(new AccountsReceivableGroupFragmentAdapter(context,listGroup));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GCL>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void getClientsCxc(String pk, final Search search, final ListView list, final Context context, final View view, final ImageView p) {

        Factory.getIntance()
                .getlistClientsCxc(pk, search).enqueue(new Callback<List<CLI>>() {
            @Override
            public void onResponse(Call<List<CLI>> call, Response<List<CLI>> response) {
                if(response.isSuccessful()) {
                    try {
                        listClient = response.body();
                        list.setAdapter(new ClientListFragmentAdapter(context,listClient));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CLI>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void getAccountsReceivable(String pk, final ListView list, final Context context, final View view) {

        Factory.getIntance()
                .getCxc(pk).enqueue(new Callback<List<CXC>>() {
            @Override
            public void onResponse(Call<List<CXC>> call, Response<List<CXC>> response) {
                if(response.isSuccessful()) {
                    try {
                        listCXC = response.body();
                        list.setAdapter(new ClientDetailActivityAdapter(context,listCXC));
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CXC>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void sent_payment(CPA cpa, final View view){
        Factory.getIntance()
                .sentPayment(cpa).enqueue(new Callback<ResponseBody>() {
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

    public static void sent_CPA_photo(String photo, final View view){
        Factory.getIntance()
                .sentCPAphoto(photo).enqueue(new Callback<ResponseBody>() {
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

    public static void sent_AccountsPayment(String pk, final View view){
        Factory.getIntance()
                .sendCxc(pk).enqueue(new Callback<ResponseBody>() {
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

    public static void Search_INV_group(Search search, String gru, final ListView list, final Context context, final View view) {
        Factory.getIntance()
                .searchInvGroup(gru, search).enqueue(new Callback<List<INV>>() {
            @Override
            public void onResponse(Call<List<INV>> call, Response<List<INV>> response) {
                if(response.isSuccessful()) {
                    try {
                        ProductsList.listProducts = response.body();
                        list.setAdapter(new ProductsListFragmentAdapter(context,ProductsList.listProducts));
                        //listProducts = response.body();
                        //list.setAdapter(new ProductsListFragmentAdapter(context,listProducts));
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
    public static void Sent_Email(SentEmail sentEmail, String gru, final View view) {
        Factory.getIntance()
                .sentMail(gru, sentEmail).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
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
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void getGroupsCpa(final ListView list, final Context context, final View view, final ImageView p) {

        Factory.getIntance()
                .getGroupCpa().enqueue(new Callback<List<GCL>>() {
            @Override
            public void onResponse(Call<List<GCL>> call, Response<List<GCL>> response) {
                if(response.isSuccessful()) {
                    try {
                        listGroup = response.body();
                        list.setAdapter(new AccountsReceivableGroupFragmentAdapter(context,listGroup));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GCL>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void getClientsCpa(String pk, final Search search, final ListView list, final Context context, final View view, final ImageView p) {

        Factory.getIntance()
                .getClientCpa(pk, search).enqueue(new Callback<List<CLI>>() {
            @Override
            public void onResponse(Call<List<CLI>> call, Response<List<CLI>> response) {
                if(response.isSuccessful()) {
                    try {
                        listClient = response.body();
                        list.setAdapter(new ClientListFragmentAdapter(context,listClient));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CLI>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void Sync_CPA(String pk, final ListView list, final Context context, final View view) {

        Factory.getIntance()
                .getCpa(pk).enqueue(new Callback<List<CPA>>() {
            @Override
            public void onResponse(Call<List<CPA>> call, Response<List<CPA>> response) {
                if(response.isSuccessful()) {
                    try {
                        listCPA = response.body();
                        list.setAdapter(new ListCpaAdapter(context,listCPA));
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CPA>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void Sync_Group(final Spinner sp, final Context context, final View view){
        Factory.getIntance()
                .syncGRU().enqueue(new Callback<List<GRU>>() {

            @Override
            public void onResponse(Call<List<GRU>> call, Response<List<GRU>> response) {
                if(response.isSuccessful()) {
                    try {
                        listGRU = response.body();
                        String array_spinner[]=new String[Accounts.listGRU.size()+1];
                        array_spinner[0] = "Seleccione una Marca";
                        for (int i = 0; i< Accounts.listGRU.size(); i++){
                            array_spinner[i+1] = Accounts.listGRU.get(i).getGRUNOMBRE();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, array_spinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp.setAdapter(adapter);
                        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                Variables.setGruPK(String.valueOf(Accounts.listGRU.get(sp.getSelectedItemPosition()).getGRUPK()));

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
            public void onFailure(Call<List<GRU>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void DeleteCPA(String pk, final View view){
        Factory.getIntance()
                .DeleteCPA(pk).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
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
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void SentCPA(String pk, final View view){
        Factory.getIntance()
                .SentCPA(pk).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
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
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void Sync_ListNewClients(String pk, final Search search, final ListView list, final Context context, final View view, final ImageView p) {

        Factory.getIntance()
                .syncNewClients(pk, search).enqueue(new Callback<List<CLI>>() {
            @Override
            public void onResponse(Call<List<CLI>> call, Response<List<CLI>> response) {
                if(response.isSuccessful()) {
                    try {
                        listClient = response.body();
                        list.setAdapter(new ClientListFragmentAdapter(context,listClient));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CLI>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void getGroupsbyEstate(final Spinner sp, final Context context, final View view) {
        Factory.getIntance()
                .getGroupCxc().enqueue(new Callback<List<GCL>>() {
            @Override
            public void onResponse(Call<List<GCL>> call, Response<List<GCL>> response) {
                if(response.isSuccessful()) {
                    try {
                        listGroup = response.body();
                        String array_spinner[]=new String[Accounts.listGroup.size()];
                        for (int i = 0; i< Accounts.listGroup.size(); i++){
                            array_spinner[i] = Accounts.listGroup.get(i).getGCLNOMBRE();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, array_spinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp.setAdapter(adapter);
                        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                Variables.setGruPK(String.valueOf(Accounts.listGroup.get(sp.getSelectedItemPosition()).getGCLPK()));

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
            public void onFailure(Call<List<GCL>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void SaveClient(CLN cln, final View view){
        Factory.getIntance()
                .SaveNewClient(cln).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
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
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void sync_providers(final ListView list, final Context context, final View view, final ImageView p) {

        Factory.getIntance()
                .syncProviders().enqueue(new Callback<List<PRO>>() {
            @Override
            public void onResponse(Call<List<PRO>> call, Response<List<PRO>> response) {
                if(response.isSuccessful()) {
                    try {
                        listPRO = response.body();
                        list.setAdapter(new ProviderDVIFragmentAdapter(context,listPRO));
                        p.setVisibility(View.GONE);
                    }
                    catch (Exception x){
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PRO>> call, Throwable t) {
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
