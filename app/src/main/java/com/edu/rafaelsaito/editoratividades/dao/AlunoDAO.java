package com.edu.rafaelsaito.editoratividades.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.view.View;

import com.edu.rafaelsaito.editoratividades.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael Saito on 24/09/2017.
 */

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "Banco Alunos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER NOT NULL PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT NOT NULL, telefone TEXT NOT NULL, nome_responsavel TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public static class AlunoTable{
        public static final String TABLE = "Alunos";
        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String ENDERECO = "endereco";
        public static final String TELEFONE = "telefone";
        public static final String NOME_RESPONSAVEL = "nome_responsavel";
        public static final String[] COLUMS = new String[] { ID, NOME, ENDERECO, TELEFONE, NOME_RESPONSAVEL};

    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);

        dados.put(AlunoTable.NOME, aluno.getNome());
        dados.put(AlunoTable.ENDERECO, aluno.getEndereco());
        dados.put(AlunoTable.TELEFONE, aluno.getTelefone());
        dados.put(AlunoTable.NOME_RESPONSAVEL, aluno.getNome_responsavel());

        db.insert("Alunos", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosDoAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("nome_responsavel", aluno.getNome_responsavel());
        return dados;
    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * from Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();
        while(c.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setNome_responsavel(c.getString(c.getColumnIndex("nome_responsavel")));
            alunos.add(aluno);
        }
        c.close();
        return alunos;
    }

    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        db.delete("Alunos", "id = ?", params);
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);

        String[] params = {aluno.getId().toString()};
        db.update("Alunos", dados, "id = ?", params);
    }
}