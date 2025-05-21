package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Construção do banco de dados por essa classe!

    // Constantes que representam os dados do database e da tabela

    public static final String DATABASE_NAME = "dados.db";
    public static final String TABLE_NAME = "pessoas";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOME";
    public static final String COL_3 = "IDADE";

    // Construtor da classe que chama o construtor da superclasse
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // Metodo para criar o DB - Chamado automaticamente

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criar SQL como Java
        db.execSQL("CREATE TABLE " + TABLE_NAME   +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, IDADE INTEGER)");
    }

    // Metodo para atualizar o DB - Chama e atualiza para uma nova versão

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Metodo para gravar um novo registro

    public boolean inserirDados(String nome, int idade) {
        // Habilitar escrita
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nome);
        contentValues.put(COL_3, idade);

        long resultado = db.insert(TABLE_NAME, null, contentValues);

        db.close();

        // Valida se foi gravado algum resultado
        return resultado != -1;
    }

    // Metodo para consultar o registro de pessoa no DB

    public Cursor obterIdadePorNome(String nome) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COL_3};
        String selection = COL_2 + " = ?";
        String[] selectionArgs = {nome};

        return db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
    }

    // Metodo para atualizar a idade pelo nome

    public boolean atualizarDadso(String nome, int novaIdade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COL_3, novaIdade);
        int linhasAfetadas = db.update(
                TABLE_NAME,
                valores,
                COL_2 + " = ?",
                new String[]{nome}
        );

        db.close();

        return linhasAfetadas > 0;
    }

}