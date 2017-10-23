package com.edu.rafaelsaito.editoratividades.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.edu.rafaelsaito.editoratividades.modelo.AtividadeAtiva;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael Saito on 16/10/2017.
 */

public class AtividadeAtivaDAO extends SQLiteOpenHelper {

    public AtividadeAtivaDAO(Context context) {
        super(context, "Banco Atividades Ativas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE AtividadesAtivas (id INTEGER NOT NULL PRIMARY KEY, nome TEXT NOT NULL, descricao TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS AtividadesAtivas";
        db.execSQL(sql);
        onCreate(db);
    }

    public static class AtividadeTable{
        public static final String TABLE = "AtividadesAtivas";
        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String DESCRICAO = "descricao";
        public static final String[] COLUMS = new String[] { ID, NOME, DESCRICAO};

    }

    public void insere(AtividadeAtiva atividadeAtiva) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaAtividade(atividadeAtiva);

        dados.put(AtividadeAtivaDAO.AtividadeTable.NOME, atividadeAtiva.getNome());
        dados.put(AtividadeAtivaDAO.AtividadeTable.DESCRICAO, atividadeAtiva.getDescricao());

        db.insert("AtividadesAtivas", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDaAtividade(AtividadeAtiva atividadeAtiva) {
        ContentValues dados = new ContentValues();
        dados.put("nome", atividadeAtiva.getNome());
        dados.put("descricao", atividadeAtiva.getDescricao());
        return dados;
    }

    public List<AtividadeAtiva> buscaAtividade() {
        String sql = "SELECT * from AtividadesAtivas;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<AtividadeAtiva> atividadesAtivas = new ArrayList<>();
        while(c.moveToNext()){
            AtividadeAtiva atividadeAtiva = new AtividadeAtiva();
            atividadeAtiva.setId(c.getLong(c.getColumnIndex("id")));
            atividadeAtiva.setNome(c.getString(c.getColumnIndex("nome")));
            atividadeAtiva.setDescricao(c.getString(c.getColumnIndex("descricao")));
            atividadesAtivas.add(atividadeAtiva);
        }
        c.close();
        return atividadesAtivas;
    }

    public void deleta(AtividadeAtiva atividadeAtiva) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {atividadeAtiva.getId().toString()};
        db.delete("AtividadesAtivas", "id = ?", params);
    }

    public void altera(AtividadeAtiva atividadeAtiva) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDaAtividade(atividadeAtiva);

        String[] params = {atividadeAtiva.getId().toString()};
        db.update("AtividadesAtivas", dados, "id = ?", params);
    }
}
