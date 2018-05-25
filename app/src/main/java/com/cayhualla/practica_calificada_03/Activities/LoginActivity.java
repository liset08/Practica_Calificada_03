package com.cayhualla.practica_calificada_03.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cayhualla.practica_calificada_03.ApiService;
import com.cayhualla.practica_calificada_03.ApiServiceGenerator;
import com.cayhualla.practica_calificada_03.R;
import com.cayhualla.practica_calificada_03.models.Usuarios;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final int REGISTER_FORM_REQUEST = 100;
    private SharedPreferences sharedPreferences;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String username = sharedPreferences.getString("username", null);
        if(username != null){
            password.requestFocus();
        }
        if(sharedPreferences.getBoolean("islogged", false)){
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String tipo = sharedPreferences.getString("tipo", "3");
            if(tipo.equals("1")){
                Vecino();
                finish();
            }else{
                Serenazgo();
                finish();
            }
        }
    }


    public void showRegister(View view) {
        startActivityForResult(new Intent(this, RegisterUusarioActivity.class), REGISTER_FORM_REQUEST);
    }
    public void Vecino( ) {
        startActivityForResult(new Intent(this, DenunciacUserActivity.class), REGISTER_FORM_REQUEST);
    }
    public void Serenazgo( ) {
        startActivityForResult(new Intent(this, MainActivity.class), REGISTER_FORM_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REGISTER_FORM_REQUEST) {
            // refresh data
            initialize();
        }
    }

    public void callLogin(View view) {
        final String user = username.getText().toString();
        String pass = password.getText().toString();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<Usuarios> call;
        call = service.login(user, pass);

        call.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Usuarios usuario = response.body();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        boolean success = editor
                                .putString("username", usuario.getUsername())
                                .putString("fullname", usuario.getApellido())
                                .putInt("id", usuario.getId())
                                .putString("tipo", usuario.getTipoUser())
                                .putBoolean("islogged", true)
                                .commit();

                        if (usuario.getTipoUser().equals("1")) {
                            Vecino();
                            finish();
                        } else {
                            Serenazgo();
                            finish();
                        }

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Usuario o Clave incorrectos");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {

            }


        });
    }

    public void callLogout(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("islogged").apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void initialize() {
    }


}

