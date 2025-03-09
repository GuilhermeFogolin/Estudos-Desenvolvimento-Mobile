package br.com.fecapccp.app.view;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    double num1, num2, res;

    TextView tv_resultado;

    EditText ete_valor1;
    EditText ete_valor2;


    @Override // Classe já criada. Apenas importando ela com sobrescrita.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_somar = findViewById(R.id.btn_soma);
        Button btn_subtrair = findViewById(R.id.btn_subtrair);
        Button btn_multiplicar = findViewById(R.id.btn_multiplicar);
        Button btn_dividr = findViewById(R.id.btn_dividir);

        tv_resultado = findViewById(R.id.tv_resultado);

        ete_valor1 = findViewById(R.id.ete_valor1);
        ete_valor2 = findViewById(R.id.ete_valor2);


        /*btn_somar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = Double.parseDouble(ete_valor1.getText().toString());
                num2 = Double.parseDouble(ete_valor2.getText().toString());
                res = num1 + num2;
                tv_resultado.setText(String.valueOf(res));
            }
        });

        btn_subtrair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = Double.parseDouble(ete_valor1.getText().toString());
                num2 = Double.parseDouble(ete_valor2.getText().toString());
                res = num1 - num2;
                tv_resultado.setText(String.valueOf(res));
            }
        });

        btn_multiplicar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = Double.parseDouble(ete_valor1.getText().toString());
                num2 = Double.parseDouble(ete_valor2.getText().toString());
                res = num1 * num2;
                tv_resultado.setText(String.valueOf(res));
            }
        });

        btn_dividr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                num1 = Double.parseDouble(ete_valor1.getText().toString());
                num2 = Double.parseDouble(ete_valor2.getText().toString());
                res = num1 / num2;
                tv_resultado.setText(String.valueOf(res));
            }
        }); */
    }

    public void somar() {
        num1 = Double.parseDouble(ete_valor1.getText().toString());
        num2 = Double.parseDouble(ete_valor2.getText().toString());
        res = num1 + num2;
        tv_resultado.setText(String.valueOf(res));
    }

    public void subtrair() {
        num1 = Double.parseDouble(ete_valor1.getText().toString());
        num2 = Double.parseDouble(ete_valor2.getText().toString());
        res = num1 - num2;
        tv_resultado.setText(String.valueOf(res));
    }

    public void multiplicar() {
        num1 = Double.parseDouble(ete_valor1.getText().toString());
        num2 = Double.parseDouble(ete_valor2.getText().toString());
        res = num1 * num2;
        tv_resultado.setText(String.valueOf(res));
    }

    public void dividir() {
        num1 = Double.parseDouble(ete_valor1.getText().toString());
        num2 = Double.parseDouble(ete_valor2.getText().toString());
        res = num1 / num2;
        tv_resultado.setText(String.valueOf(res));
    }

    public void operacao(View v) {
        if(v.getId() == R.id.btn_soma) {
            somar();
        } else if(v.getId() == R.id.btn_subtrair) {
            subtrair();
        } else if(v.getId() == R.id.btn_multiplicar) {
            multiplicar();
        } else {
            dividir();
        }
    }
}