package br.com.fecapccp.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    double vtaca, vprato, vgarfo, vfaca, vtotal;

    EditText et_taca, et_prato, et_garfo, et_faca;

    CheckBox cb_taca, cb_prato, cb_garfo, cb_faca;

    TextView tv_resultado;

    Button btn_calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_taca = findViewById(R.id.et_tacas);
        et_prato = findViewById(R.id.et_pratos);
        et_garfo = findViewById(R.id.et_garfos);
        et_faca = findViewById(R.id.et_facas);

        cb_taca = findViewById(R.id.cb_tacas);
        cb_prato = findViewById(R.id.cb_pratos);
        cb_garfo = findViewById(R.id.cb_garfos);
        cb_faca = findViewById(R.id.cb_facas);

        btn_calcular = findViewById(R.id.btn_calcular);

        tv_resultado = findViewById(R.id.tv_resultado);

        vtaca = 0.25;
        vprato = 0.20;
        vgarfo = 0.15;
        vfaca = 0.15;
        vtotal = 0.0;

        cb_taca.setText(cb_taca.getText().toString() + vtaca); // Concatenando o texto atual mais o valor.
        cb_prato.setText(cb_prato.getText().toString() + vprato);
        cb_garfo.setText(cb_garfo.getText().toString() + vgarfo);
        cb_faca.setText(cb_faca.getText().toString() + vfaca);

    }
    public void Calcular(View v) {
        vtotal = 0.0;

        if(cb_taca.isChecked()) {
            vtotal += vtaca * Double.parseDouble(et_taca.getText().toString());
        }

        if(cb_prato.isChecked()) {
            vtotal += vprato * Double.parseDouble(et_prato.getText().toString());
        }

        if(cb_garfo.isChecked()) {
            vtotal += vgarfo * Double.parseDouble(et_garfo.getText().toString());
        }

        if(cb_faca.isChecked()) {
            vtotal += vfaca * Double.parseDouble(et_faca.getText().toString());
        }

        tv_resultado.setText("Valor total: R$" + vtotal);


        // Mensagens aos usuários
        Toast.makeText(this, "Valor da locação calculado", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);

        cxMsg.setMessage("O valor da locação está calculado!");

        cxMsg.setNeutralButton("OK", null);

        cxMsg.show();
    }

    // Para abrir uma tela nova
    public void abrir_tela_sobre(View v) {
        Intent it_telaSobre = new Intent(this, Tela_Sobre.class);

        it_telaSobre.putExtra("p_nome", "Fogolin");

        it_telaSobre.putExtra("p_vtotal", vtotal);

        // Parâmetros ANTES do startActivity
        startActivity(it_telaSobre);

        //setContentView(R.layout.activity_tela_sobre);

    }
}