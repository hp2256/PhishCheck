package com.example.phishchecker.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phishchecker.R;
import com.example.phishchecker.data.UrlData;
import com.example.phishchecker.data.ValidData;
import com.example.phishchecker.network.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText urlText;
    Button submit;
    String submitURL;
    private ApiService ap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.resultText);
        urlText = findViewById(R.id.urlEditText);
        submit = findViewById(R.id.submit);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://checkurl.phishtank.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ap = retrofit.create(ApiService.class);
        /* ValidData d = new ValidData("http://zonasegurabcp.vkp-pe.com/","json");

       sendRequest(d);
        fetchUrData("http://zonasegurabcp.vkp-pe.com/");
        fetchUrlData("http://zonasegurabcp.vkp-pe.com/");*/
        fetchU("http://zonasegurabcp.vkp-pe.com/");
        urlText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validURL(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                if (submitURL != null) {
                    fetchUrlData(submitURL);
                }
            }
        });
    }

    private void validURL(CharSequence cs) {
        String u = cs.toString();
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(u.toLowerCase());
        if (!m.matches()) {
            urlText.setError("Enter a valid URL");
        } else {
            submitURL = u;
        }
    }

    void fetchU(String ur) {
        UrlData x = new UrlData(ur, "json");

        Call<String> call = ap.fetchU(x);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.e("CODE", String.valueOf(response.code()));
                    result.setText("Code: " + response.code());
                    return;
                }
                String dataResponse = response.body();
                Log.e("response fetch u", String.valueOf(response.body()));
                //  result.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("failure: ", String.valueOf(t));

            }
        });
    }

    private void fetchUrlData(String submitURL) {

        Call<String> call = ap.fetchUrl(submitURL, "json");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.e("CODE", String.valueOf(response.code()));
                    result.setText("Code: " + response.code());
                    return;
                }
                String dataResponse = response.body();
                Log.e("response", String.valueOf(response.body()));
                result.setText(response.body());
                // result.setText("Valid: " + dataResponse.getValid() + "\n" + "In database:" + dataResponse.getIndb() + "\n" + "Verified:" + dataResponse.getVerified());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                result.setText(String.valueOf(t));
                Log.e("failure: ", String.valueOf(t));

            }
        });
    }

}