package com.example.luiscaguana.appexamen;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.luiscaguana.appexamen.Adaptadores.AdaptadorSkins;
import com.example.luiscaguana.appexamen.modelo.Skin;
import com.example.luiscaguana.appexamen.retrofitUtils.APIRestCatalogo;
import com.example.luiscaguana.appexamen.retrofitUtils.RetrofitClient;

import java.net.ConnectException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText etRarezas;
    RecyclerView rv;
    AdaptadorSkins adapter;
    LinearLayoutManager llm;
    private ArrayList<Skin> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etRarezas = findViewById(R.id.edtRaze);
        rv = findViewById(R.id.rvcatagolo);

        lista = new ArrayList<Skin>();


    }
    //primera clase
    public void consultar(View v){
        String rareza = etRarezas.getText().toString().toString().trim();
        if(rareza.isEmpty()){
            Retrofit rt = RetrofitClient.getClient(APIRestCatalogo.BASE_URL);
            APIRestCatalogo arc = rt.create(APIRestCatalogo.class);
            Call<ArrayList<Skin>> call = null;
            if(rareza.isEmpty()){
                call = arc.obtenerSkins();

            } else {
                call = arc.obtenerSkins(rareza);
            }

            call.enqueue(new Callback<ArrayList<Skin>>() {
                @Override
                public void onResponse(Call<ArrayList<Skin>> call, Response<ArrayList<Skin>> response) {

                    if(!response.isSuccessful()){
                        Log.e("Fallo ve..", response.code()+"");
                    } else {
                        lista = response.body();
                    }
                    configurarRv();
                }



                @Override
                public void onFailure(Call<ArrayList<Skin>> call, Throwable t) {
                    Log.e("fallo vee", t.toString());

                }
            });

        }
    }
    //clase para si le doy  un dato  pase a la nueva clse
    private void configurarRv() {
        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        adapter = new AdaptadorSkins(lista);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Skin skin = lista.get(rv.getChildAdapterPosition(view));
                Intent i = new Intent(MainActivity.this, DetalleSkinActivity.class);
                i.putExtra("ID", skin.getIdentifier());
                startActivity(i);
            }
        });

        rv.setAdapter(adapter);

    }

    private boolean isNetworkAvailable(){
        boolean isAvilable = false;
        //GESTOR PARA LA CONECTIVIDAD
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //PARA RECUPERA DE LA RED
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //si la red esta bien configurada pues iniciamo cesios

        if(networkInfo!=null && networkInfo.isConnected()) isAvilable=true;

        return isAvilable;
    }
}

