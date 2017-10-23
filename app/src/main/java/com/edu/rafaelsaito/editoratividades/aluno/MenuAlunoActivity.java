package com.edu.rafaelsaito.editoratividades.aluno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.edu.rafaelsaito.editoratividades.R;

public class MenuAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aluno);

        ImageButton cadastrar_aluno = (ImageButton) findViewById(R.id.botao1);
        cadastrar_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProCadastrarAluno = new Intent(MenuAlunoActivity.this, AlunoActivity.class);
                startActivity(intentVaiProCadastrarAluno);
            }
        });

        ImageButton lista_alunos = (ImageButton) findViewById(R.id.botao2);
        lista_alunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiPraListaAlunos = new Intent(MenuAlunoActivity.this, ListaAlunosActivity.class);
                startActivity(intentVaiPraListaAlunos);
            }
        });
    }
}
