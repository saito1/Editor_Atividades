package com.edu.rafaelsaito.editoratividades.aluno;

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
import com.edu.rafaelsaito.editoratividades.dao.AlunoDAO;
import com.edu.rafaelsaito.editoratividades.modelo.Aluno;

public class AlunoActivity extends AppCompatActivity {

    private AlunoHelper helper;
    private TextInputLayout nameTextInputLayout;
    private TextInputLayout enderecoTextInputLayout;
    private TextInputLayout nome_responsavelTextInputLayout;
    private TextInputLayout telefoneTextInputLayout;
    private TextInputEditText nameEditText;
    private TextInputEditText enderecoEditText;
    private TextInputEditText nome_responsavelEditText;
    private TextInputEditText telefoneEditText;
    private Button botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        nameTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_name);
        nameEditText = (TextInputEditText) findViewById(R.id.nome_aluno);
        enderecoTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_adress);
        enderecoEditText = (TextInputEditText) findViewById(R.id.endereco_aluno);
        nome_responsavelTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_responsavel);
        nome_responsavelEditText = (TextInputEditText) findViewById(R.id.nome_responsavel);
        telefoneTextInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout_phone);
        telefoneEditText = (TextInputEditText) findViewById(R.id.telefone_aluno);
        botaoSalvar = (Button) findViewById(R.id.botao_salvar);

        helper = new AlunoHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(nameEditText.getText().toString())) {
                    nameTextInputLayout.setErrorEnabled(true);
                    nameTextInputLayout.setError("Nome inválido");
                    return;
                }

                if (TextUtils.isEmpty(enderecoEditText.getText().toString())) {
                    enderecoTextInputLayout.setErrorEnabled(true);
                    enderecoTextInputLayout.setError("Endereço inválido");
                    return;
                }
                if (TextUtils.isEmpty(nome_responsavelEditText.getText().toString())) {
                    nome_responsavelTextInputLayout.setErrorEnabled(true);
                    nome_responsavelTextInputLayout.setError("Nome inválido");
                    return;
                }
                if (TextUtils.isEmpty(telefoneEditText.getText().toString())) {
                    telefoneTextInputLayout.setErrorEnabled(true);
                    telefoneTextInputLayout.setError("Telefone inválido");
                    return;
                }
                Aluno aluno = helper.getAluno();
                AlunoDAO dao = new AlunoDAO(AlunoActivity.this);

                if (aluno.getId() != null) {
                    dao.altera(aluno);
                } else {
                    dao.insere(aluno);
                }

                dao.close();
                Toast.makeText(AlunoActivity.this, "Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
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

        enderecoEditText.addTextChangedListener(new TextWatcher() {
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

        nome_responsavelEditText.addTextChangedListener(new TextWatcher() {
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

        telefoneEditText.addTextChangedListener(new TextWatcher() {
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