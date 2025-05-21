package com.example.database;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextView textResultado;
    TextInputEditText textNome;
    TextInputEditText textIdade;
    Button btnGravar;
    Button btnConsultar;
    Button btnAtualizar;
    Button btnDeletar;

    // Database também é um OBJETO
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textResultado = findViewById(R.id.textResultado);
        textIdade = findViewById(R.id.textIdade);
        textNome = findViewById(R.id.textNome);
        btnGravar = findViewById(R.id.btnGravar);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnDeletar = findViewById(R.id.btnDeletar);
        dbHelper = new DatabaseHelper(this);

        btnGravar.setOnClickListener(v -> {

            // Entrada de dados

            String nome = textNome.getText().toString();
            String idade = textIdade.getText().toString();

            // Validação dos dados

            if(nome.isEmpty() || idade.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "Preencha todos os dados!",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // Dados validos

            // Implementação após criação do banco de dados

            try{ // Chamadas assíncronas - Se a chama retornou e foi correta

                // Converter idade de String -> int

                int numIdade = Integer.parseInt(idade);

                // Chamar o metodo gravar do DB

                if(dbHelper.inserirDados(nome, numIdade)) {
                    Toast.makeText(MainActivity.this,
                            "Dados inseridos com sucesso!",
                            Toast.LENGTH_LONG).show();
                }

            } catch (NumberFormatException e) { // Aqui vai exceção
                Toast.makeText(MainActivity.this,
                        "Erro ao inserir dados! 🤒",
                        Toast.LENGTH_LONG).show();
            }
        });

        btnConsultar.setOnClickListener(v -> {

            // Entrada de dados

            String nome = textNome.getText().toString();

            // Validação dos dados

            if(nome.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "Digite o seu nome...",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // Dados validados

            // Implementação após criação do banco de dados
        });

        btnAtualizar.setOnClickListener(v -> {

            // Entrada de dados

            String nome = textNome.getText().toString();
            String idade = textIdade.getText().toString();

            // Validação dos dados

            if(nome.isEmpty() || idade.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "Preencha todos os dados!",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // Dados validos

            // Implementação após criação do banco de dados
        });

        btnDeletar.setOnClickListener(v -> {

            // Entrada de dados

            String nome = textNome.getText().toString();

            // Validação dos dados

            if(nome.isEmpty()) {
                Toast.makeText(MainActivity.this,
                        "Digite o seu nome...",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // Dados validados

            // Implementação após criação do banco de dados
        });
    }
}