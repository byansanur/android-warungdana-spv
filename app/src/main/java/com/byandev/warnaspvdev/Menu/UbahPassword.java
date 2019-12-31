package com.byandev.warnaspvdev.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.byandev.warnaspvdev.Api.ApiEndPoint;
import com.byandev.warnaspvdev.Api.SharedPrefManager;
import com.byandev.warnaspvdev.Api.UtilsApi;
import com.byandev.warnaspvdev.R;

public class UbahPassword extends AppCompatActivity {

    EditText passLama, passBaru, rePass;
    Button btnUbahPass;
    Context context;
    SharedPrefManager sharedPrefManager;
    ApiEndPoint mApiService;
    String npmUser;
    Integer idUpdate;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        passLama = findViewById(R.id.passwordLama);
        passBaru = findViewById(R.id.passwordBaru);
        rePass = findViewById(R.id.rePassword);
        btnUbahPass = findViewById(R.id.btnUbahPassword);
        context = this;
        sharedPrefManager = new SharedPrefManager(this);
        // get api endpoint
        mApiService = UtilsApi.getAPIService();
        // mengganti password lama ke baru berdasarkan npm pada sharePrefManager
        npmUser = sharedPrefManager.getSPNpm();
    }

    @Override
    protected void onStart(){
        super.onStart();
        btnUbahPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(passLama.getText())) {
                    passLama.setError("Required");
                } else if (TextUtils.isEmpty(passBaru.getText())) {
                    passBaru.setError("Required");
                } else if (TextUtils.isEmpty(rePass.getText())) {
                    rePass.setError("Required");
                } else if (passBaru.getText().toString().length() < 6) {
                    Toast.makeText(context, "Password Baru Minimal 6 Karakter", Toast.LENGTH_SHORT).show();
                } else if (rePass.getText().toString().equalsIgnoreCase(rePass.getText().toString())){
                    Toast.makeText(context, "Password tidak sesuai", Toast.LENGTH_SHORT).show();
                } else {
                    loading = ProgressDialog.show(context, null, "Wait a minute", true, false);
//                    ubahPass();
                }
            }
        });
    }

//    private void ubahPass(){
//        mApiService.ubahPassword(sharedPrefManager.getSpId(), passLama.getText().toString(),rePass.getText().toString())
//                .enqueue(new Callback<RespPost>(){
//                    @Override
//                    public void onResponse(Call<RespPost> call, Response<RespPost> response) {
//                        if (response.isSuccessful()) {
//                            if (response.body().getApiStatus() != 0){
//                                sharedPrefManager.clearSharedPreferences();
//                                Intent intent = new Intent(context, HomeActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                Toast.makeText(context, response.body().getApiMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(context, "Kesalahan server", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<RespPost> call, Throwable t) {
//                        loading.dismiss();
//                    }
//                });
//    }
}
