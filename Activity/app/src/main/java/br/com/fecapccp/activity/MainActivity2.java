package br.com.fecapccp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    // Instanciamento dos elementos:

    private Button btnT2Set;
    private Button btnT2Reset;
    private TextView textT2Resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        Log.i("Ciclo de Vida", "Tela 2 - onCreate");

        textT2Resultado = findViewById(R.id.textT2Resultado);
        btnT2Set = findViewById(R.id.btnT2Set);
        btnT2Reset = findViewById(R.id.btnT2Reset);

        // Recebendo os dados: Recebe intenção e os itens extras.

        Bundle bundle = getIntent().getExtras();

        String nome             = bundle.getString("nome");
        String nacionalidade    = bundle.getString("nacionalidade");
        int idade               = bundle.getInt("idade");
        double altura           = bundle.getDouble("altura");

        // Mostrar o resultado:

        String resultado = "Nome: " + nome + "\nNacionalidade: " + nacionalidade + "\nIdade: " + idade
                + "\nAltura: " + altura + "\n";

        textT2Resultado.setText(resultado);

        // Chamar a tela 3:

        btnT2Set.setOnClickListener(view -> {

            Intent intent = new Intent(this, MainActivity3.class);
                // Aqui podem ser inseridos dados para transferir
                // ...
                // ...
            startActivity(intent);
        });

        // Fechar a tela 2:

        btnT2Reset.setOnClickListener(view -> {
            finish(); // "Mata" a tela 2. Ou seja, sem ficar abrindo telas e consumindo memória
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo de Vida", "Tela 2 - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo de Vida", "Tela 2 - onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo de Vida", "Tela 2 - onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo de Vida", "Tela 2 - onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ciclo de Vida", "Tela 2 - onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ciclo de Vida", "Tela 2 - onStart");
    }
}