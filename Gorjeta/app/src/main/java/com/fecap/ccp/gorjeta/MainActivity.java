package com.fecap.ccp.gorjeta;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editValor;
    private TextView text_porcentagem, text_gorjeta, text_total, text_resultado;
    private SeekBar seekBarGorjeta;
    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor = findViewById(R.id.editValor);
        text_gorjeta = findViewById(R.id.text_gorjeta);
        text_porcentagem = findViewById(R.id.text_porcentagem);
        text_total = findViewById(R.id.text_total);
        text_resultado = findViewById(R.id.text_resultado);
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta);

        // Manipulação sem botão: Seekbar - "ouvinte"

        seekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porcentagem = i;
                text_porcentagem.setText(Math.round(porcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO: O método calcular pode ser colocado aqui também.
            }
        });

    }

    public void calcular() {
        String valorRecuperado = editValor.getText().toString();

        if (valorRecuperado == null || valorRecuperado.equals("")) {

            text_resultado.setText("ERRO - Entrada inválida! 🚨");

        } else {

            // Apagando RESULTADO

            text_resultado.setText("");

            // Converter String para Double

            double valorDigitado = Double.parseDouble(valorRecuperado);

            // Calcular a gorjeta total:

            double gorjeta = valorDigitado * (porcentagem / 100);
            double total = gorjeta + valorDigitado;

            // Exibir a gorjeta

            DecimalFormat df = new DecimalFormat("##.##");
            text_gorjeta.setText("R$" + df.format(gorjeta));
            text_total.setText("R$" + df.format(total));
        }
    }
}