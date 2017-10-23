package com.edu.rafaelsaito.editoratividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.edu.rafaelsaito.editoratividades.atividade_ativa.ListaAtividadesAtivasActivity;
import com.edu.rafaelsaito.editoratividades.atividade_ativa.OrientacaoAtivaActivity;
import com.edu.rafaelsaito.editoratividades.atividade_passiva.ListaAtividadesPassivasActivity;
import com.edu.rafaelsaito.editoratividades.atividade_passiva.OrientacaoPassivaActivity;

public class MenuListaAtividadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lista_atividade);

        ImageButton atividade_ativa = (ImageButton) findViewById(R.id.botao1);
        atividade_ativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiPraAtividadeAtiva = new Intent(MenuListaAtividadeActivity.this, ListaAtividadesAtivasActivity.class);
                startActivity(intentVaiPraAtividadeAtiva);
            }
        });

        ImageButton atividade_passiva = (ImageButton) findViewById(R.id.botao2);
        atividade_passiva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiPraAtividadePassiva = new Intent(MenuListaAtividadeActivity.this, ListaAtividadesPassivasActivity.class);
                startActivity(intentVaiPraAtividadePassiva);
            }
        });
    }
}
