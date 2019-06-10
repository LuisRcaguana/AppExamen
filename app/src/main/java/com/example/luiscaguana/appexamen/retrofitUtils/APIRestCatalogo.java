package com.example.luiscaguana.appexamen.retrofitUtils;

import com.example.luiscaguana.appexamen.modelo.Skin;
import com.example.luiscaguana.appexamen.modelo.SkinsDetalle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRestCatalogo {
    public static final  String BASE_URL = "http://10.0.2.2:3000/";
                        //esta parte no es igual que lo desmas depende de la estructura
    //obtenermos los Skins
    @GET("skins")
    Call<ArrayList<Skin>> obtenerSkins();

    //obtenemos  a partir de las razas
    @GET("skins")
    Call<ArrayList<Skin>> obtenerSkins(@Query("rarity") String rareza);

    //vemos los detalles de un sking a partir de un seleccionado
    @GET("skinsDetalles/{id_skin}")
    Call<SkinsDetalle> obtenerSkinDetalle(@Path("id_skin") String id);






}
