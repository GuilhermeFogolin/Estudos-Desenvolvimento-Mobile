package models;

public class Alerta {
    private String nome;
    private String dataHora;
    private String tipoAlerta;
    private double latitude;
    private double longitude;
    private int fk_idUser;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getFk_idUser() {
        return fk_idUser;
    }

    public void setFk_idUser(int fk_idUser) {
        this.fk_idUser = fk_idUser;
    }
// Getters e setters
}
