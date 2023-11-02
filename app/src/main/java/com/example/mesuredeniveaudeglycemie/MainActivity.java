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

public class MainActivity extends AppCompatActivity
{
    private TextView tvage , tvresultat;
    private SeekBar sbage ;
    private RadioButton rboui ;
    private RadioButton rbnon ;

    private EditText etvaleur;

    private Button btnConsulter ;

    private void init()
    {
        etvaleur = (EditText) findViewById(R.id.etValeur);
        sbage = (SeekBar) findViewById(R.id.sbAge);
        tvage = (TextView)findViewById(R.id.tvAge);
        rboui = (RadioButton) findViewById(R.id.rbIsFasting);
        rbnon = (RadioButton) findViewById(R.id.rbIsNotFasting);
        btnConsulter=(Button) findViewById(R.id.btnConsulter);
        tvresultat = (TextView) findViewById(R.id.tvResult);
    }


    protected void onCreate(Bundle savedInstanceState)
    {
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
            public void onClick(View v)
            {
                calculer(v);
            }

            private void calculer(View v)
            {
                String contenuTexte = etvaleur.getText().toString();
                double niveauGlycemie = Double.parseDouble(contenuTexte);
                boolean estAJean = rboui.isChecked();
                String message;

                if (estAJean)
                {
                    if (niveauGlycemie >= 5.0 && niveauGlycemie <= 7.2)
                    {
                        message = "Le niveau de glycémie est normal avant le repas ou à jeun.";
                    }
                    else if (niveauGlycemie < 5.0)
                    {
                        message = "Le niveau de glycémie est trop bas avant le repas ou à jeun.";
                    }
                    else
                    {
                        message = "Le niveau de glycémie est trop élevé avant le repas ou à jeun.";
                    }
                }
                else
                {
                    if (niveauGlycemie < 10.5)
                    {
                        message = "Le niveau de glycémie est normal après le repas.";
                    }
                    else
                    {
                        message = "Le niveau de glycémie est trop élevé après le repas.";
                    }
                }

                tvresultat.setText(message);
            }
        });


    }
}
