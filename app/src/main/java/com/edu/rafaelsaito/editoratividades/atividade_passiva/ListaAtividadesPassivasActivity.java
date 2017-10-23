package com.edu.rafaelsaito.editoratividades.atividade_passiva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.rafaelsaito.editoratividades.R;
import com.edu.rafaelsaito.editoratividades.dao.AtividadePassivaDAO;
import com.edu.rafaelsaito.editoratividades.modelo.AtividadePassiva;

import java.util.List;

public class ListaAtividadesPassivasActivity extends AppCompatActivity {

    private ListView listaAtividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividades_passivas);
        listaAtividades = (ListView) findViewById(R.id.lista_atividades);

        listaAtividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AtividadePassiva atividadePassiva = (AtividadePassiva) listaAtividades.getItemAtPosition(position);
                Intent intentVaiPraOrientacao = new Intent(ListaAtividadesPassivasActivity.this, OrientacaoPassivaActivity.class);
                intentVaiPraOrientacao.putExtra("atividadePassiva", atividadePassiva);
                startActivity(intentVaiPraOrientacao);
            }
        });
        registerForContextMenu(listaAtividades);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                AtividadePassiva atividadePassiva = (AtividadePassiva) listaAtividades.getItemAtPosition(info.position);
                Toast.makeText(ListaAtividadesPassivasActivity.this, "Deletar atividade " + atividadePassiva.getNome(), Toast.LENGTH_SHORT).show();
                AtividadePassivaDAO dao = new AtividadePassivaDAO(ListaAtividadesPassivasActivity.this);
                dao.deleta(atividadePassiva);
                dao.close();
                carregaLista();

                return false;
            }
        });
    }

    private void carregaLista() {

        AtividadePassivaDAO dao = new AtividadePassivaDAO(this);
        List<AtividadePassiva> atividadesPassivas = dao.buscaAtividade();
        dao.close();

        ArrayAdapter<AtividadePassiva> adapter = new ArrayAdapter<AtividadePassiva>(this, android.R.layout.simple_list_item_1, atividadesPassivas);
        listaAtividades.setAdapter(adapter);
    }
}
