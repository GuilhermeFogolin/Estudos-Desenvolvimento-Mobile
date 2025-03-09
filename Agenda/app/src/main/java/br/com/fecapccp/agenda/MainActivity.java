package br.com.fecapccp.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.database.sqlite.SQLiteDatabase; // Importe para o banco de dados
import android.database.Cursor; // Importe para a navegação de registros
import android.widget.*; // Importe de todos os componentes para trabalhar com programação

public class MainActivity extends AppCompatActivity {

    EditText et_nome, et_telefone;

    Button btn_gravar, btn_consultar, btn_fechar;

    SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tv_titulo_consulta), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_nome = (EditText) findViewById(R.id.et_nome);
        et_telefone = (EditText) findViewById(R.id.et_telefone);
        btn_gravar = (Button) findViewById(R.id.btn_gravar);
        btn_consultar = (Button) findViewById(R.id.btn_consultar);
        btn_fechar = (Button) findViewById(R.id.btn_fechar);

        abrirBanco();
        abrirOuCriarTabela();
        fecharDB();
    }

    public void abrirBanco() {
        try {
            db = openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);
        } catch (Exception ex) {
            CxMsg.mensagem("Erro ao abrir ou criar banco de dados!", this);
        }
    }

    public void fecharDB() {
        db.close();
    }

    public void abrirOuCriarTabela() {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos(" +
                    "id INTEGER PRIMARY KEY, " +
                    "nome TEXT," +
                    "fone TEXT);");
        } catch (Exception ex) {
            CxMsg.mensagem("Erro ao abrir ou criar tabela!", this);
        }
    }

    public void inserirRegistro(View v) {

        String st_nome, st_fone;
        st_nome = et_nome.getText().toString();
        st_fone = et_telefone.getText().toString();
        if(st_nome == "" || st_fone == "") {
            CxMsg.mensagem("Campos não podem estar vazios!", this);
            return;
        }
        abrirBanco();
       try {
            db.execSQL("INSERT INTO contatos(nome, fone) VALUES ('"+st_nome+"', '"+st_fone+"')"); // Concatenação no final
       } catch (Exception ex) {
           CxMsg.mensagem("Erro ao inserir registro!", this);
       } finally {
           CxMsg.mensagem("Registro inserido com sucesso!", this);
       }
       fecharDB();

       et_nome.setText(null);
       et_telefone.setText(null);
    }
    public void abrir_tela_consulta(View v) {
        Intent it_tela_consulta = new Intent(this, TelaConsulta.class);
        startActivity(it_tela_consulta);
        CxMsg.mensagem("Tela consulta aberta!", this);
    }

    public void fechar_tela(View v) {
        this.finish();
    }
}