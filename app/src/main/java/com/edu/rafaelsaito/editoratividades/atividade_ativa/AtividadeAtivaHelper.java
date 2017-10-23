package com.edu.rafaelsaito.editoratividades.atividade_ativa;

import android.widget.EditText;

import com.edu.rafaelsaito.editoratividades.R;
import com.edu.rafaelsaito.editoratividades.modelo.AtividadeAtiva;

/**
 * Created by Rafael Saito on 16/10/2017.
 */

public class AtividadeAtivaHelper {
    private final EditText campoNome;
    private final EditText campoDescricao;

    private AtividadeAtiva atividadeAtiva;

    public AtividadeAtivaHelper(OrientacaoAtivaActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.nome_atividade);
        campoDescricao = (EditText) activity.findViewById(R.id.descricao_atividade);
        atividadeAtiva = new AtividadeAtiva();
    }

    public AtividadeAtiva getAtividadeAtiva() {
        atividadeAtiva.setNome(campoNome.getText().toString());
        atividadeAtiva.setDescricao(campoDescricao.getText().toString());

        return atividadeAtiva;
    }

    public void preencheFormulario(AtividadeAtiva atividadeAtiva) {
        campoNome.setText(atividadeAtiva.getNome());
        campoDescricao.setText(atividadeAtiva.getDescricao());
        this.atividadeAtiva = atividadeAtiva;
    }
}

