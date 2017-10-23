package com.edu.rafaelsaito.editoratividades.atividade_passiva;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edu.rafaelsaito.editoratividades.R;
import com.edu.rafaelsaito.editoratividades.dao.AtividadePassivaDAO;
import com.edu.rafaelsaito.editoratividades.modelo.AtividadePassiva;

public class OrientacaoPassivaActivity extends AppCompatActivity {

    private AtividadePassivaHelper helper;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout descricaoTextInputLayout;
    private TextInputEditText nameEditText;
    private TextInputEditText descricaoEditText;
    private Button botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientacao_passiva);

        nameTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_name);
        nameEditText = (TextInputEditText) findViewById(R.id.nome_atividade);
        descricaoTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_description);
        descricaoEditText = (TextInputEditText) findViewById(R.id.descricao_atividade);
        botaoSalvar = (Button) findViewById(R.id.botao_salvar);

        helper = new AtividadePassivaHelper(this);

        Intent intent = getIntent();
        final AtividadePassiva atividadePassiva = (AtividadePassiva) intent.getSerializableExtra("atividadePassiva");
        if (atividadePassiva != null) {
            helper.preencheFormulario(atividadePassiva);
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
                AtividadePassiva atividadePassiva = helper.getAtividadePassiva();
                AtividadePassivaDAO dao = new AtividadePassivaDAO(OrientacaoPassivaActivity.this);

                if (atividadePassiva.getId() != null) {
                    dao.altera(atividadePassiva);
                } else {
                    dao.insere(atividadePassiva);
                }

                dao.close();
                Toast.makeText(OrientacaoPassivaActivity.this, "Atividade " + atividadePassiva.getNome() + " criada!", Toast.LENGTH_SHORT).show();
                finish();
                Intent intentVaiPraNovaAtividade = new Intent(OrientacaoPassivaActivity.this, NovaAtividadePassivaActivity.class);
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
