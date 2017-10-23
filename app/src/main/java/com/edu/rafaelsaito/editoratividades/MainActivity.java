package com.edu.rafaelsaito.editoratividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import com.edu.rafaelsaito.editoratividades.aluno.MenuAlunoActivity;
import com.edu.rafaelsaito.editoratividades.atividade_ativa.OrientacaoAtivaActivity;
import com.edu.rafaelsaito.editoratividades.atividade_ativa.ListaAtividadesAtivasActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton menu_aluno = (ImageButton) findViewById(R.id.botao3);
        menu_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProMenuAluno = new Intent(MainActivity.this, MenuAlunoActivity.class);
                startActivity(intentVaiProMenuAluno);
            }
        });

        ImageButton nova_atividade = (ImageButton) findViewById(R.id.botao1);
        nova_atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiPraNovaAtividade = new Intent(MainActivity.this, MenuAtividadeActivity.class);
                startActivity(intentVaiPraNovaAtividade);
            }
        });

        ImageButton atividades = (ImageButton) findViewById(R.id.botao4);
        atividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiPraListaAtividades = new Intent(MainActivity.this, MenuListaAtividadeActivity.class);
                startActivity(intentVaiPraListaAtividades);
            }
        });
    }
}
