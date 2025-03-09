package br.com.fecapccp.app.googlemapsapp;

// Bibliotecas
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import interfaces.ApiInterface;
import models.Alerta;
import models.AlertaResposta;

public class MainActivity extends AppCompatActivity {

    // Constante para o código de solicitação de permissão de localização
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    // Lcalização do dispositivo
    private FusedLocationProviderClient fusedLocationClient;

    // Campos de entrada para os dados do alerta
    private EditText editTextNome, editTextDataHora, editTextLatitude, editTextLongitude, editTextFkIdUser;

    // Spinner para o tipo de alerta
    private Spinner spinnerTipoAlerta;

    // Botão para criar o alerta
    private Button buttonCriarAlerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos campos de entrada e do botão
        editTextNome = findViewById(R.id.editTextNome);
        editTextDataHora = findViewById(R.id.editTextDataHora);
        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLongitude = findViewById(R.id.editTextLongitude);
        editTextFkIdUser = findViewById(R.id.editTextFkIdUser);
        spinnerTipoAlerta = findViewById(R.id.spinnerTipoAlerta);
        buttonCriarAlerta = findViewById(R.id.buttonCriarAlerta);

        // Configuração do listener do botão criar alerta
        buttonCriarAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarAlerta();
            }
        });

        // Preenchimento automático do campo de data e hora
        preencherDataHoraAtual();

        // Verifica e solicita permissões de localização
        checkLocationPermission();
    }

    // Método para preencher o campo de data e hora com a hora atual
    private void preencherDataHoraAtual() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String dataHoraAtual = dateFormat.format(calendar.getTime());
        editTextDataHora.setText(dataHoraAtual);
    }

    // Método para criar um alerta
    private void criarAlerta() {
        // Obtenção dos dados do formulário
        String nome = editTextNome.getText().toString();
        String dataHora = editTextDataHora.getText().toString();
        String tipoAlerta = spinnerTipoAlerta.getSelectedItem().toString();
        double latitude = Double.parseDouble(editTextLatitude.getText().toString());
        double longitude = Double.parseDouble(editTextLongitude.getText().toString());
        int fkIdUser = Integer.parseInt(editTextFkIdUser.getText().toString());

        // Criação do objeto Alerta
        Alerta alerta = new Alerta();
        alerta.setNome(nome);
        alerta.setDataHora(dataHora);
        alerta.setTipoAlerta(tipoAlerta);
        alerta.setLatitude(latitude);
        alerta.setLongitude(longitude);
        alerta.setFk_idUser(fkIdUser);

        // Configuração do Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lwjy86-3000.csb.app") // URL DO CODESANDBOX! IMPORTANTE.
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Criação da interface ApiInterface
        ApiInterface api = retrofit.create(ApiInterface.class);

        // Criação da chamada para a API
        Call<AlertaResposta> call = api.criarAlerta(alerta);

        call.enqueue(new Callback<AlertaResposta>() {
            @Override
            public void onResponse(Call<AlertaResposta> call, Response<AlertaResposta> response) {
                if (response.isSuccessful()) {
                    // Resposta bem-sucedida do servidor
                    AlertaResposta alertaResposta = response.body();
                    Toast.makeText(MainActivity.this, alertaResposta.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    // Resposta com erro do servidor
                    Toast.makeText(MainActivity.this, "Erro ao criar alerta: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AlertaResposta> call, Throwable t) {
                // Falha na requisição (erro de rede, etc.)
                Toast.makeText(MainActivity.this, "Erro na rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para verificar e solicitar permissões de localização
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getLocation();
        }
    }

    // Método para lidar com a resposta da solicitação de permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                // Permissão negada, lidar com a situação (ex: exibir mensagem ao usuário)
                Toast.makeText(this, "Permissão de localização negada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para obter a localização do dispositivo
    private void getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Verifica se as permissões foram concedidas
            // Caso contrário, não tenta obter a localização
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Preenche os campos de latitude e longitude
                    editTextLatitude.setText(String.valueOf(location.getLatitude()));
                    editTextLongitude.setText(String.valueOf(location.getLongitude()));
                }
            }
        });
    }
}