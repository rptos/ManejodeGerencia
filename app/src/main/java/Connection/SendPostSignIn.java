package Connection;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.henry.clientesnuevos.LoginActivity;

import Model.USR;
import Model.Variables;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Henry on 22/1/2018.
 */

public class SendPostSignIn {
    //public  static APIService singIn = ApiUtils.getAPIService();
    public static void sendPost(String lanid, String notes, final Context c, final View v, final LoginActivity loginActivity) {
        USR requestPost = new USR(lanid, notes);
        Factory.getIntance()
                .savePost(requestPost).enqueue(new Callback<USR>() {
            @Override
            public void onResponse(Call<USR> call, Response<USR> response) {
                if(response.isSuccessful()) {
                    //showResponse(response.body().toString());
                    Log.i("Valores", "post submitted to API." + response.body().toString());
                    SharedPreferences sp = c.getSharedPreferences("profile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("USR_LANID", String.valueOf(response.body().getUSRLANID()));
                    editor.putString("USR_PK", String.valueOf(response.body().getUSRNOTES()));
                    editor.putString("Conection", Variables.getUrl_local());
                    editor.commit();
                    Variables.setId(response.body().getUSRNOTES());
                    Variables.setLanid(response.body().getUSRLANID());
                    Variables.setUrl(Variables.getUrl_local());
                    loginActivity.goMain();
                }
            }

            @Override
            public void onFailure(Call<USR> call, Throwable t) {
                Log.e("Error", "Unable to submit post to API." + call.toString());
                /*Toast.makeText(c, "Error de usuario y/o contraseña ",
                        Toast.LENGTH_LONG).show();*/
                Snackbar.make(v, "Error de usuario y/o contraseña ", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                loginActivity.showProgress(false);
            }
        });
    }
}
