package br.com.fecapccp.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaConsulta extends AppCompatActivity {

    EditText et_nome, et_telefone;
    Button btn_anterior, btn_voltar, btn_proximo;
    SQLiteDatabase db = null;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_consulta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_nome = (EditText) findViewById(R.id.et_nome_consulta);
        et_telefone = (EditText) findViewById(R.id.et_telefone_consulta);
        btn_anterior = (Button) findViewById(R.id.btn_anterior);
        btn_proximo = (Button) findViewById(R.id.btn_proximo);
        btn_voltar = (Button) findViewById(R.id.btn_voltar_consulta);

        cursor = BancoDados.buscarTodosDados(this);

        if(cursor.getCount() != 0) {
            mostrarDados();
        } else {
            CxMsg.mensagem("Nenhum registro encontrado!", this);
        }
    }

    public void fechar_tela(View v) { this.finish(); }

   public void proximoRegistro(View v) {
      try {
          cursor.moveToNext();
          mostrarDados();
      } catch (Exception ex) {
          if(cursor.isAfterLast()) {
              CxMsg.mensagem("Não existem mais registros!", this);
          } else {
              CxMsg.mensagem("Erro ao navegar pelos registros!", this);
          }
      }
   }

    public void registroAnterior(View v) {
        try {
            cursor.moveToPrevious();
            mostrarDados();
        } catch (Exception ex) {
            if(cursor.isBeforeFirst()) {
                CxMsg.mensagem("Não existem mais registros!", this);
            } else {
                CxMsg.mensagem("Erro ao navegar pelos registros!", this);
            }
        }
    }

    @SuppressLint("Range")
    public void mostrarDados() {
        et_nome.setText(cursor.getString(cursor.getColumnIndex("nome")));
        et_telefone.setText(cursor.getString(cursor.getColumnIndex("fone")));
    }
}