package Connection;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import Adapters.ProductsListFragmentAdapter;
import Model.INV;
import Model.Search;
import Model.Variables;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Henry on 26/1/2018.
 */

public class ProductsList {

    public static List<INV> listProducts;

    public static void getPost(String value, String warehouse, final ListView list, final Context context, final View view, final ImageView p) {

        Search search = new Search();
        search.setValor(value);
        search.setUser(Variables.getId());
        search.setClase("");
        Factory.getIntance()
                .invPost(warehouse, search).enqueue(new Callback<List<INV>>() {
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
}
