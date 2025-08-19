package com.perez.LiterAlura.model;

import jakarta.persistence.*;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private int numeroDownloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(String titulo, String idioma, int numeroDownloads, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
        this.autor = autor;
    }

    // getters e toString()
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public int getNumeroDownloads() { return numeroDownloads; }
    public Autor getAutor() { return autor; }

    @Override
    public String toString() {
        return """
                
                Título:   %s
                Autor:    %s
                Idioma:   %s
                Dowloads: %d""".formatted(titulo,(autor != null ? autor.getNome() : "Desconhecido"),
                idioma, numeroDownloads);
    }
}
