package com.cayhualla.practica_calificada_03.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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

public class DenunciacUserActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView denunciasList2;
    private SharedPreferences sharedPreferences;
    private Integer id;
    private TextView user_actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denunciac_user);
        getSupportActionBar().setTitle("Registro de Vecinos");


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        id = sharedPreferences.getInt("id_user", 1);
        //ShowUser(id);
        String user_act = sharedPreferences.getString("fullname",null);
        user_actual = findViewById(R.id.nameuser);
        user_actual.setText(user_act);

        denunciasList2 = findViewById(R.id.recyclerview);
        denunciasList2.setLayoutManager(new LinearLayoutManager(this));

        denunciasList2.setAdapter(new DenunciasAdapter(this));

        initialize();
    }

    private void initialize() {
        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<List<Denuncias>> call = service.DenunciasPropias(id);

        call.enqueue(new Callback<List<Denuncias>>() {
            @Override
            public void onResponse(Call<List<Denuncias>> call, Response<List<Denuncias>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Denuncias> denuncias = response.body();
                        Log.d(TAG, "denuncias: " + denuncias);

                        DenunciasAdapter adapter = (DenunciasAdapter) denunciasList2.getAdapter();
                        adapter.setDenuncias(denuncias);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());

                          throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DenunciacUserActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Denuncias>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DenunciacUserActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
    public void callLogout( ) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("islogged").apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    private static final int REGISTER_FORM_REQUEST = 100;

    public void showRegisterdenuncia(View view){
        startActivityForResult(new Intent(this, DenunciaRegister.class), REGISTER_FORM_REQUEST);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_takepicture:
                callLogout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
