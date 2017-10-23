package com.edu.rafaelsaito.editoratividades.atividade_passiva;

import android.widget.EditText;

import com.edu.rafaelsaito.editoratividades.R;
import com.edu.rafaelsaito.editoratividades.modelo.AtividadePassiva;

/**
 * Created by Rafael Saito on 23/10/2017.
 */

public class AtividadePassivaHelper {
    private final EditText campoNome;
    private final EditText campoDescricao;

    private AtividadePassiva atividadePassiva;

    public AtividadePassivaHelper(OrientacaoPassivaActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.nome_atividade);
        campoDescricao = (EditText) activity.findViewById(R.id.descricao_atividade);
        atividadePassiva = new AtividadePassiva();
    }

    public AtividadePassiva getAtividadePassiva() {
        atividadePassiva.setNome(campoNome.getText().toString());
        atividadePassiva.setDescricao(campoDescricao.getText().toString());

        return atividadePassiva;
    }

    public void preencheFormulario(AtividadePassiva atividadePassiva) {
        campoNome.setText(atividadePassiva.getNome());
        campoDescricao.setText(atividadePassiva.getDescricao());
        this.atividadePassiva = atividadePassiva;
    }
}
