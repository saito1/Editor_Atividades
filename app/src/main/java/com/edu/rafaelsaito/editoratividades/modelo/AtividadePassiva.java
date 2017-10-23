package com.edu.rafaelsaito.editoratividades.modelo;

import java.io.Serializable;

/**
 * Created by Rafael Saito on 23/10/2017.
 */

public class AtividadePassiva implements Serializable {
    private Long id;
    private String nome;
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
