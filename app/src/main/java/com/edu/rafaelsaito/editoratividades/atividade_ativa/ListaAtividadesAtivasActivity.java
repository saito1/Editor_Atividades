package com.edu.rafaelsaito.editoratividades.atividade_ativa;

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
import com.edu.rafaelsaito.editoratividades.dao.AtividadeAtivaDAO;
import com.edu.rafaelsaito.editoratividades.modelo.AtividadeAtiva;

import java.util.List;

public class ListaAtividadesAtivasActivity extends AppCompatActivity {

    private ListView listaAtividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividades_ativas);
        listaAtividades = (ListView) findViewById(R.id.lista_atividades);

        listaAtividades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AtividadeAtiva atividadeAtiva = (AtividadeAtiva) listaAtividades.getItemAtPosition(position);
                Intent intentVaiPraOrientacao = new Intent(ListaAtividadesAtivasActivity.this, OrientacaoAtivaActivity.class);
                intentVaiPraOrientacao.putExtra("atividadeAtiva", atividadeAtiva);
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
                AtividadeAtiva atividadeAtiva = (AtividadeAtiva) listaAtividades.getItemAtPosition(info.position);
                Toast.makeText(ListaAtividadesAtivasActivity.this, "Deletar atividade " + atividadeAtiva.getNome(), Toast.LENGTH_SHORT).show();
                AtividadeAtivaDAO dao = new AtividadeAtivaDAO(ListaAtividadesAtivasActivity.this);
                dao.deleta(atividadeAtiva);
                dao.close();
                carregaLista();

                return false;
            }
        });
    }

    private void carregaLista() {

        AtividadeAtivaDAO dao = new AtividadeAtivaDAO(this);
        List<AtividadeAtiva> atividadeAtivas = dao.buscaAtividade();
        dao.close();

        ArrayAdapter<AtividadeAtiva> adapter = new ArrayAdapter<AtividadeAtiva>(this, android.R.layout.simple_list_item_1, atividadeAtivas);
        listaAtividades.setAdapter(adapter);
    }
}
