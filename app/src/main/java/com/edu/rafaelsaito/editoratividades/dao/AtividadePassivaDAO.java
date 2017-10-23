package com.edu.rafaelsaito.editoratividades.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.edu.rafaelsaito.editoratividades.modelo.AtividadePassiva;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael Saito on 23/10/2017.
 */

public class AtividadePassivaDAO extends SQLiteOpenHelper {

    public AtividadePassivaDAO(Context context) {
        super(context, "Banco Atividades Passivas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE AtividadesPassivas (id INTEGER NOT NULL PRIMARY KEY, nome TEXT NOT NULL, descricao TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS AtividadesPassivas";
        db.execSQL(sql);
        onCreate(db);
    }

    public static class AtividadeTable{
        public static final String TABLE = "AtividadesPassivas";
        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String DESCRICAO = "descricao";
        public static final String[] COLUMS = new String[] { ID, NOME, DESCRICAO};

    }

    public void insere(AtividadePassiva atividadePassiva) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaAtividade(atividadePassiva);

        dados.put(AtividadePassivaDAO.AtividadeTable.NOME, atividadePassiva.getNome());
        dados.put(AtividadePassivaDAO.AtividadeTable.DESCRICAO, atividadePassiva.getDescricao());

        db.insert("AtividadesPassivas", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDaAtividade(AtividadePassiva atividadePassiva) {
        ContentValues dados = new ContentValues();
        dados.put("nome", atividadePassiva.getNome());
        dados.put("descricao", atividadePassiva.getDescricao());
        return dados;
    }

    public List<AtividadePassiva> buscaAtividade() {
        String sql = "SELECT * from AtividadesPassivas;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<AtividadePassiva> atividadesPassivas = new ArrayList<>();
        while(c.moveToNext()){
            AtividadePassiva atividadePassiva = new AtividadePassiva();
            atividadePassiva.setId(c.getLong(c.getColumnIndex("id")));
            atividadePassiva.setNome(c.getString(c.getColumnIndex("nome")));
            atividadePassiva.setDescricao(c.getString(c.getColumnIndex("descricao")));
            atividadesPassivas.add(atividadePassiva);
        }
        c.close();
        return atividadesPassivas;
    }

    public void deleta(AtividadePassiva atividadePassiva) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {atividadePassiva.getId().toString()};
        db.delete("AtividadesPassivas", "id = ?", params);
    }

    public void altera(AtividadePassiva atividadePassiva) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaAtividade(atividadePassiva);

        String[] params = {atividadePassiva.getId().toString()};
        db.update("AtividadesPassivas", dados, "id = ?", params);
    }
}
