package com.edu.rafaelsaito.editoratividades.aluno;

import android.widget.EditText;

import com.edu.rafaelsaito.editoratividades.R;
import com.edu.rafaelsaito.editoratividades.modelo.Aluno;

/**
 * Created by Rafael Saito on 24/09/2017.
 */

public class AlunoHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoNome_Responsavel;

    private Aluno aluno;

    public AlunoHelper(AlunoActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.nome_aluno);
        campoEndereco = (EditText) activity.findViewById(R.id.endereco_aluno);
        campoTelefone = (EditText) activity.findViewById(R.id.telefone_aluno);
        campoNome_Responsavel = (EditText) activity.findViewById(R.id.nome_responsavel);
        aluno = new Aluno();
    }

    public Aluno getAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setNome_responsavel(campoNome_Responsavel.getText().toString());

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoNome_Responsavel.setText(aluno.getNome_responsavel());
        this.aluno = aluno;
    }
}