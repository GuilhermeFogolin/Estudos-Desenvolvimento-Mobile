package interfaces;

import models.Alerta;
import models.AlertaResposta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/criarAlerta")
    Call<AlertaResposta> criarAlerta(@Body Alerta alerta);
}
