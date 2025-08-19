package com.perez.LiterAlura.principal;

import com.perez.LiterAlura.model.Autor;
import com.perez.LiterAlura.model.DadosResultado;
import com.perez.LiterAlura.model.Livro;
import com.perez.LiterAlura.repository.AutorRepository;
import com.perez.LiterAlura.repository.LivroRepository;
import com.perez.LiterAlura.service.ConsumoApi;
import com.perez.LiterAlura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private LivroRepository livroRepo;
    @Autowired
    private AutorRepository autorRepo;

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exiberMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                \n------------------------ MENU ------------------------
                1- Buscar livro pelo título
                2- Listar livros registrados
                3- Listar autores registrados
                4- Listar autores vivos em um determinado ano
                5- Listar livros em um determinado idioma
                0- Sair
                """);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> buscarLivro();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivro() {
        System.out.println("Insira o nome do livro que você deseja procurar:");
        String tituloBusca = scanner.nextLine();

        String json = consumo.obterDados("https://gutendex.com/books/?search=" +
                tituloBusca.replace(" ", "%20"));
        DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

        if (resultado.livros().isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        var livroApi = resultado.livros().get(0);
        var autorApi = livroApi.autores().get(0);

        Autor autor = new Autor(autorApi.nome(), autorApi.anoNascimento(), autorApi.anoFalecimento());
        autorRepo.save(autor);

        Livro livro = new Livro(livroApi.titulo(), livroApi.idiomas().get(0), livroApi.numeroDownloads(), autor);
        livroRepo.save(livro);

        System.out.println("Livro encontrado e salvo:");
        System.out.println(livro);
    }

    private void listarLivros() {
        var livros = livroRepo.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        var autores = autorRepo.findAll();
        autores.forEach(a -> {
            System.out.println(a);
            a.getLivros().forEach(l -> System.out.println("   - " + l.getTitulo()));
        });
    }

    private void listarAutoresVivos() {
        System.out.println("Digite o ano:");
        int ano = scanner.nextInt();
        scanner.nextLine();

        var autores = autorRepo.findAll();
        autores.stream()
                .filter(a -> a.getAnoNascimento() != null && a.getAnoNascimento() <= ano)
                .filter(a -> a.getAnoFalecimento() == null || a.getAnoFalecimento() >= ano)
                .forEach(System.out::println);
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                es → espanhol
                en → ingles
                fr → frances
                pt → portugues
                """);
        System.out.println("Digite o idioma (ex: pt):");
        String idioma = scanner.nextLine();

        var livros = livroRepo.findAll().stream()
                .filter(l -> l.getIdioma().equalsIgnoreCase(idioma))
                .toList();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma: " + idioma);
            return;
        }
        livros.forEach(System.out::println);
    }
}

