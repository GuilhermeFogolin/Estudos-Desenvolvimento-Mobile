package br.com.fecapccp.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_sobre);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();

        String nome = it.getStringExtra("p_nome");

        double valor = it.getDoubleExtra("p_vtotal", 0);

        TextView tv=findViewById(R.id.resultadoSobre);

        tv.setText(nome + " | " + valor);
    }

    public void abrir_tela_principal(View v) {
        this.finish(); // Processo mais correto de voltar
        //Intent it_telaPrincipal = new Intent(this, MainActivity.class);
        //startActivity(it_telaPrincipal);

    }
}