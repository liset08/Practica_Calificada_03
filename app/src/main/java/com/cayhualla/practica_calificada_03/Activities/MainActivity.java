package com.cayhualla.practica_calificada_03.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cayhualla.practica_calificada_03.Adapters.DenunciasAdapter;
import com.cayhualla.practica_calificada_03.ApiService;
import com.cayhualla.practica_calificada_03.ApiServiceGenerator;
import com.cayhualla.practica_calificada_03.R;
import com.cayhualla.practica_calificada_03.models.Denuncias;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView denunciasList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        denunciasList = findViewById(R.id.recyclerview);
        denunciasList.setLayoutManager(new LinearLayoutManager(this));

        denunciasList.setAdapter(new DenunciasAdapter());

        initialize();
    }

    private void initialize() {
        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<List<Denuncias>> call = service.getDenuncias();

        call.enqueue(new Callback<List<Denuncias>>() {
            @Override
            public void onResponse(Call<List<Denuncias>> call, Response<List<Denuncias>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Denuncias> denuncias = response.body();
                        Log.d(TAG, "productos: " + denuncias);

                        DenunciasAdapter adapter = (DenunciasAdapter) denunciasList.getAdapter();
                        adapter.setDenuncias(denuncias);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Denuncias>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
    private static final int REGISTER_FORM_REQUEST = 100;

    public void showRegisterdenuncia(View view){
        startActivityForResult(new Intent(this, DenunciaRegister.class), REGISTER_FORM_REQUEST);
    }

    }


