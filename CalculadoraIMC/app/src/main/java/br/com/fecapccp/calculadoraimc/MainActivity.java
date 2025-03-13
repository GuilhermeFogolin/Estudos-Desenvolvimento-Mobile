package br.com.fecapccp.calculadoraimc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

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

        // Instanciamento dos objetos do app

        EditText campoPeso = findViewById(R.id.et_peso);
        EditText campoAltura = findViewById(R.id.et_altura);
        TextView resultado = findViewById(R.id.tv_resultado);

        // Variáveis para recuperar (get) e converter em String os objetos!

        String peso = campoPeso.getText().toString();
        String altura = campoAltura.getText().toString();

        // Converter os dados peso e altura em NUMÉRICO e calcular o IMC:

        double numPeso = Double.parseDouble(peso);
        double numAltura = Double.parseDouble(altura);

        double calcular = numPeso / (numAltura * numAltura);

        // Converter o calcular para String

        String imc = String.valueOf(calcular);

        // Formatar imc em três casas decimais

        DecimalFormat df = new DecimalFormat("##.###");

        imc = df.format(calcular);

        // Validação de IMC

        /* Abaixo do peso: IMC menor que 18,5
        Eutrófico (peso adequado): IMC entre 18,5 e 24,9
        Sobrepeso: IMC entre 25 e 29,9
        Obesidade grau I: IMC entre 30 e 34,9
        Obesidade grau II: IMC entre 35 e 39,9
        Obesidade grau III: IMC igual ou superior a 40 */

        if(calcular < 18.5) {
            resultado.setText(imc + " kg/m²! \nAbaixo do peso");
        } else if(calcular < 25) {
            resultado.setText(imc + " kg/m²! \nEutrófico (peso adequado)");
        } else if(calcular < 30) {
            resultado.setText(imc + " kg/m²! \nSobrepeso");
        } else if(calcular < 35) {
            resultado.setText(imc + " kg/m²! \nObesidade grau I");
        } else if(calcular < 40) {
            resultado.setText(imc + " kg/m²! \nObesidade grau II");
        } else {
            resultado.setText(imc + " kg/m²! \nObesidade grau III");
        }
    }

    // Método para Resetar informações

    public void limpar(View v) {
        TextView resultado = findViewById(R.id.tv_resultado);
        EditText campoNome = findViewById(R.id.et_peso);
        EditText campoIdade = findViewById(R.id.et_altura);
        resultado.setText("-----");
        campoNome.setText("");
        campoIdade.setText("");

    }
}