package com.example.apistudy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apistudy.api.ApiService;
import com.example.apistudy.model.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView terms, source, quotes;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        terms = findViewById(R.id.terms);
        source = findViewById(R.id.source);
        quotes = findViewById(R.id.quotes);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.convertUSDtoVND("843d4d34ae72b3882e3db642c51e28e6", "VND", "USD", 1).enqueue(new Callback<Currency>() {
                    @Override
                    public void onResponse(Call<Currency> call, Response<Currency> response) {
                        Toast.makeText(MainActivity.this, "Succes", Toast.LENGTH_LONG).show();

                        Currency currency = response.body();
                        if(currency != null && currency.isSuccess()) {
                            terms.setText(currency.getTerms());
                            source.setText(currency.getSource());
                            quotes.setText(String.valueOf(currency.getQuotes().getUSDVND()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Currency> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}