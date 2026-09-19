package br.edu.ifpb.pweb2.bytewallet.config;

import br.edu.ifpb.pweb2.bytewallet.model.Categoria;
import br.edu.ifpb.pweb2.bytewallet.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InicializadorDados implements ApplicationRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Só insere se o banco estiver vazio
        if (categoriaRepository.count() == 0) {
            
            List<Categoria> categorias = Arrays.asList(
                criarCategoria("Salário", "ENTRADA", 1),
                criarCategoria("Cashback", "ENTRADA", 2),
                criarCategoria("Resgate Investimento", "ENTRADA", 3),
                criarCategoria("Outras Entradas", "ENTRADA", 4),
                
                criarCategoria("Saúde e Remédios", "SAIDA", 1),
                criarCategoria("Academia e Personal", "SAIDA", 2),
                criarCategoria("Carros e Uber", "SAIDA", 3),
                criarCategoria("Educação e Cursos", "SAIDA", 4),
                criarCategoria("Lazer e Turismo", "SAIDA", 5),
                criarCategoria("Condomínio", "SAIDA", 6),
                criarCategoria("Energia", "SAIDA", 7),
                criarCategoria("Celular", "SAIDA", 8),
                criarCategoria("Internet", "SAIDA", 9),
                criarCategoria("Itens Pessoais", "SAIDA", 10),
                criarCategoria("Feira", "SAIDA", 11),
                criarCategoria("Casa", "SAIDA", 12),
                criarCategoria("Impostos", "SAIDA", 13),
                criarCategoria("Outros gastos", "SAIDA", 14),
                
                criarCategoria("Aporte Renda Fixa", "INVESTIMENTO", 1),
                criarCategoria("Aporte Renda Variável", "INVESTIMENTO", 2),
                criarCategoria("Aporte Reserva Emergencia", "INVESTIMENTO", 3),
                criarCategoria("Aporte Previdência", "INVESTIMENTO", 4)
            );

            categoriaRepository.saveAll(categorias);
            System.out.println("Categorias iniciais populadas com sucesso!");
        }
    }

    private Categoria criarCategoria(String nome, String natureza, Integer ordem) {
        Categoria cat = new Categoria();
        cat.setNome(nome);
        cat.setNatureza(natureza);
        cat.setOrdem(ordem);
        cat.setAtivo(true);
        return cat;
    }
}