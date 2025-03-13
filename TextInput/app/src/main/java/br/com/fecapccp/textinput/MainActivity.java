package br.com.fecapccp.textinput;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    // Mé-todo para Setar informações:

    public void enviar(View v) {

        // Instanciamento dos objetos do app:

        EditText campoNome = findViewById(R.id.et_nome);
        EditText campoIdade = findViewById(R.id.et_idade);
        TextView resultado = findViewById(R.id.tv_resultado);

        // Criar variáveis para recuperar (get) o seu conteúdo e converter em String

        String nome = campoNome.getText().toString();
        String idade = campoIdade.getText().toString();

        // Apresentar o resultado

        resultado.setText(nome + " tem " + idade + " anos!");
    }

    // Método para Resetar informações

    public void limpar(View v) {
        TextView resultado = findViewById(R.id.tv_resultado);
        EditText campoNome = findViewById(R.id.et_nome);
        EditText campoIdade = findViewById(R.id.et_idade);
        resultado.setText("-----");
        campoNome.setText("");
        campoIdade.setText("");

    }
}