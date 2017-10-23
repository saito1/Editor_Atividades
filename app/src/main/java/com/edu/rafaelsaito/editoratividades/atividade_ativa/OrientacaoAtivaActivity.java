package com.edu.rafaelsaito.editoratividades.atividade_ativa;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edu.rafaelsaito.editoratividades.R;
import com.edu.rafaelsaito.editoratividades.dao.AtividadeAtivaDAO;
import com.edu.rafaelsaito.editoratividades.modelo.AtividadeAtiva;

public class OrientacaoAtivaActivity extends AppCompatActivity {

    private AtividadeAtivaHelper helper;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout descricaoTextInputLayout;
    private TextInputEditText nameEditText;
    private TextInputEditText descricaoEditText;
    private Button botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientacao_ativa);

        nameTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_name);
        nameEditText = (TextInputEditText) findViewById(R.id.nome_atividade);
        descricaoTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_description);
        descricaoEditText = (TextInputEditText) findViewById(R.id.descricao_atividade);
        botaoSalvar = (Button) findViewById(R.id.botao_salvar);

        helper = new AtividadeAtivaHelper(this);

        Intent intent = getIntent();
        final AtividadeAtiva atividadeAtiva = (AtividadeAtiva) intent.getSerializableExtra("atividadeAtiva");
        if (atividadeAtiva != null) {
            helper.preencheFormulario(atividadeAtiva);
        }

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(nameEditText.getText().toString())) {
                    nameTextInputLayout.setErrorEnabled(true);
                    nameTextInputLayout.setError("Nome inválido");
                    return;
                }

                if (TextUtils.isEmpty(descricaoEditText.getText().toString())) {
                    descricaoTextInputLayout.setErrorEnabled(true);
                    descricaoTextInputLayout.setError("Descrição inválida");
                    return;
                }
                AtividadeAtiva atividadeAtiva = helper.getAtividadeAtiva();
                AtividadeAtivaDAO dao = new AtividadeAtivaDAO(OrientacaoAtivaActivity.this);

                if (atividadeAtiva.getId() != null) {
                    dao.altera(atividadeAtiva);
                } else {
                    dao.insere(atividadeAtiva);
                }

                dao.close();
                Toast.makeText(OrientacaoAtivaActivity.this, "AtividadeAtiva " + atividadeAtiva.getNome() + " criada!", Toast.LENGTH_SHORT).show();
                finish();
                Intent intentVaiPraNovaAtividade = new Intent(OrientacaoAtivaActivity.this, NovaAtividadeAtivaActivity.class);
                startActivity(intentVaiPraNovaAtividade);
            }
        });

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vazio
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameTextInputLayout.setErrorEnabled(false);
                nameTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //vazio
            }
        });

        descricaoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //vazio
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameTextInputLayout.setErrorEnabled(false);
                nameTextInputLayout.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //vazio
            }
        });
    }
}
