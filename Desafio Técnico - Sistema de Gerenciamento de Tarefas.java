import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static class Item {
        private String titulo;
        private String descricao;
        private String estado;
        private String prioridade;

        public Item(String titulo, String descricao, String estado, String prioridade) {
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new IllegalArgumentException("Título não pode ser vazio.");
            }
            if (prioridade == null || prioridade.trim().isEmpty()) {
                throw new IllegalArgumentException("Prioridade não pode ser vazia.");
            }
            this.titulo = titulo;
            this.descricao = descricao;
            this.estado = estado;
            this.prioridade = prioridade;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getPrioridade() {
            return prioridade;
        }

        public void setPrioridade(String prioridade) {
            this.prioridade = prioridade;
        }

        @Override
        public String toString() {
            return String.format("Item{titulo='%s', descricao='%s', estado='%s', prioridade='%s'}", 
                                 titulo, descricao, estado, prioridade);
        }
    }

    static class Lista {
        private String nome;
        private String descricao;
        private List<Item> itens;

        public Lista(String nome, String descricao) {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome da lista não pode ser vazio.");
            }
            this.nome = nome;
            this.descricao = descricao;
            this.itens = new ArrayList<>();
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

        public List<Item> getItens() {
            return itens;
        }

        public void adicionarItem(Item item) {
            itens.add(item);
        }

        public void removerItem(Item item) {
            itens.remove(item);
        }

        public List<Item> filtrarPorEstado(String estado) {
            return itens.stream()
                        .filter(item -> item.getEstado().equalsIgnoreCase(estado))
                        .collect(Collectors.toList());
        }

        public List<Item> ordenarPorPrioridade() {
            return itens.stream()
                        .sorted(Comparator.comparing(Item::getPrioridade))
                        .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return String.format("Lista{nome='%s', descricao='%s', itens=%s}", 
                                 nome, descricao, itens);
        }
    }

    public static void main(String[] args) {
        Lista listaDeTarefas = new Lista("Tarefas do Dia", "Lista de tarefas para hoje");

        listaDeTarefas.adicionarItem(new Item("Comprar leite", "Comprar leite no supermercado", "Pendente", "Alta"));
        listaDeTarefas.adicionarItem(new Item("Estudar Java", "Estudar tópicos de programação em Java", "Em progresso", "Média"));
        listaDeTarefas.adicionarItem(new Item("Fazer exercícios", "Fazer exercícios físicos", "Pendente", "Baixa"));

        System.out.println("Lista de Tarefas:");
        System.out.println(listaDeTarefas);

        System.out.println("\nItens Pendentes:");
        listaDeTarefas.filtrarPorEstado("Pendente").forEach(System.out::println);

        System.out.println("\nItens Ordenados por Prioridade:");
        listaDeTarefas.ordenarPorPrioridade().forEach(System.out::println);

        Item itemParaRemover = listaDeTarefas.getItens().get(0);
        listaDeTarefas.removerItem(itemParaRemover);

        System.out.println("\nLista de Tarefas após remoção:");
        System.out.println(listaDeTarefas);
    }
}
