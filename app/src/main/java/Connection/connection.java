package Connection;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;

import Model.IMPORT;
import Model.Search;
import Model.Variables;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Henry on 29/1/2018.
 */

public class connection {
    static String code;

    public static String BrowserLastCorrelative(String t, final Context context, final View view, final EditText number) {
        Search search = new Search();
        search.setValor(t);
        search.setUser(Variables.getId());
        search.setClase("");
        Factory.getIntance().getCorrelative(search).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        number.setText(response.body().string().trim().replace("\"",""));
                    }
                    catch (Exception x){
                        /*Toast.makeText(context, "Error de conexion " + x.getMessage(),
                                Toast.LENGTH_LONG).show();*/
                        Snackbar.make(view, "Error de conexion " + x.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                /*Toast.makeText(context, "Error de conexion ",
                        Toast.LENGTH_LONG).show();*/
                Snackbar.make(view, "Error de conexion ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return code;
    }

    public static void ImportWeb(String DateI, String DateF, String order, final View view) {

        IMPORT Import = new IMPORT();
        Import.setDateI(DateI);
        Import.setDateF(DateF);
        Import.setOrder(order);

        Factory.getIntance()
                .Importweb(Import).enqueue(new Callback<ResponseBody>() {
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
                Snackbar.make(view, "Error de conexion al Importar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public static void ExportWeb(String id, final View view) {

        IMPORT Import = new IMPORT();
        Import.setId(id);

        Factory.getIntance()
                .Exportweb(Import).enqueue(new Callback<ResponseBody>() {
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
                Snackbar.make(view, "Error de conexion al Exportar ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

