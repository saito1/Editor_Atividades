package com.edu.rafaelsaito.editoratividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.edu.rafaelsaito.editoratividades.aluno.AlunoActivity;
import com.edu.rafaelsaito.editoratividades.aluno.ListaAlunosActivity;
import com.edu.rafaelsaito.editoratividades.atividade_ativa.OrientacaoAtivaActivity;
import com.edu.rafaelsaito.editoratividades.atividade_passiva.OrientacaoPassivaActivity;

public class MenuAtividadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_atividade);

        ImageButton atividade_ativa = (ImageButton) findViewById(R.id.botao1);
        atividade_ativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiPraAtividadeAtiva = new Intent(MenuAtividadeActivity.this, OrientacaoAtivaActivity.class);
                startActivity(intentVaiPraAtividadeAtiva);
            }
        });

        ImageButton atividade_passiva = (ImageButton) findViewById(R.id.botao2);
        atividade_passiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiPraAtividadePassiva = new Intent(MenuAtividadeActivity.this, OrientacaoPassivaActivity.class);
                startActivity(intentVaiPraAtividadePassiva);
            }
        });
    }
}
