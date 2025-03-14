package br.com.fecapccp.agenda;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase; // Importe para o banco de dados
import android.database.Cursor; // Navegação entre registros
import android.content.ContextWrapper;
import static android.content.Context.MODE_PRIVATE;
public class BancoDados {

    static SQLiteDatabase db = null;
    static Cursor cursor;

    public static void abrirBanco(Activity act) {
        try {
            ContextWrapper cw = new ContextWrapper(act);
            db = cw.openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);
        } catch (Exception ex) {
            CxMsg.mensagem("Erro ao abrir ou criar banco de dados!", act);
        }
    }

    public static void abrirOuCriarTabela(Activity act) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos(" +
                    "id INTEGER PRIMARY KEY, " +
                    "nome TEXT," +
                    "fone TEXT);");
        } catch (Exception ex) {
            CxMsg.mensagem("Erro ao abrir ou criar tabela!", act);
        }
    }

    public static void fecharDB() {
        db.close();
    }

    public static void inserirRegistro(String nome, String fone, Activity act) {
        abrirBanco(act);
        try {
            db.execSQL("INSERT INTO contatos(nome, fone) VALUES ('"+nome+"', '"+fone+"')"); // Concatenação no final
        } catch (Exception ex) {
            CxMsg.mensagem("Erro ao inserir registro!", act);
        } finally {
            CxMsg.mensagem("Registro inserido com sucesso!", act);
        }
        fecharDB();
    }

    public static Cursor buscarTodosDados(Activity act) {
        abrirBanco(act);

        cursor = db.query("contatos",
                new String[]{"nome", "fone"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        return cursor;
    }
}
