package com.example.mesuredeniveaudeglycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvage, tvresultat;
    private SeekBar sbage;
    private RadioButton rboui;
    private RadioButton rbnon;

    private EditText etvaleur;

    private Button btnConsulter;

    private void init() {
        etvaleur = (EditText) findViewById(R.id.etValeur);
        sbage = (SeekBar) findViewById(R.id.sbAge);
        tvage = (TextView) findViewById(R.id.tvAge);
        rboui = (RadioButton) findViewById(R.id.rbIsFasting);
        rbnon = (RadioButton) findViewById(R.id.rbIsNotFasting);
        btnConsulter = (Button) findViewById(R.id.btnConsulter);
        tvresultat = (TextView) findViewById(R.id.tvResult);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Information", "onProgressChanged " + progress);
                tvage.setText("Votre âge : " + progress);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer(v);
            }

            private void calculer(View v) {
                int age;
                float valeur;
                boolean verifage = false, verifvaleur = false;
                if (sbage.getProgress() != 0)
                    verifage = true;
                else
                    Toast.makeText(MainActivity.this, "veillez verifier votre age", Toast.LENGTH_SHORT).show();
                if (!etvaleur.getText().toString().isEmpty())
                    verifvaleur = true;
                else
                    Toast.makeText(MainActivity.this, "veillez verifier votre valeur mesurée", Toast.LENGTH_LONG).show();
                if (verifage && verifvaleur) {
                    age = sbage.getProgress();
                    valeur = Float.valueOf(etvaleur.getText().toString());
                    if (rboui.isChecked())
                        if (age >= 13)
                            if (valeur < 5.0)
                                tvresultat.setText("niveau de glycemie est bas ");
                            else if (valeur >= 50 && valeur <= 7.2)
                                tvresultat.setText("niveau de glycemie est normal");
                            else tvresultat.setText("niveau de glycemie est eleve");
                        else if (age >= 6 && age <= 12)
                            if (valeur < 5.0)
                                tvresultat.setText("niveau de glycemie est bas ");
                            else if (valeur > 5.0 && valeur <= 10.0)
                                tvresultat.setText("niveau de glycemie est normal");
                            else tvresultat.setText("niveau de glycemie est eleve ");
                        else if (valeur < 5.5) tvresultat.setText("niveau de glycemie est bas");
                        else if (valeur >= 5.5 && valeur <= 10.0)
                            tvresultat.setText("niveau de glycemie est normal");
                        else tvresultat.setText("niveau de glycemie est eleve ");
                    else if (valeur < 10.5) tvresultat.setText("niveau de glycemie est normal");
                    else tvresultat.setText("niveau de glycemie est eleve");

                }


            }
        });

    }
}