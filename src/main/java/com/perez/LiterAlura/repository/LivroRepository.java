package com.perez.LiterAlura.repository;

import com.perez.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdiomaContainingIgnoreCase(String idioma);

    List<Livro> findTop5ByOrderByNumeroDownloadsDesc();

    @Query("SELECT l FROM Livro l WHERE l.autor.nome ILIKE %:nome%")
    List<Livro> buscarPorNomeAutor(String nome);

    @Query("SELECT l FROM Livro l WHERE l.numeroDownloads >= :downloadsMin")
    List<Livro> livrosMaisBaixados(int downloadsMin);
}
