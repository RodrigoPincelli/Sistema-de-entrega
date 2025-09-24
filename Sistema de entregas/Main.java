import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Evento> eventos = new ArrayList<>();

    public static void main(String[] args) {
        usuarios.addAll(FileManager.carregarUsuarios());
        eventos.addAll(FileManager.carregarEventos());

        // Modo demonstração: java Main --demo
        if (args != null && args.length > 0 && "--demo".equalsIgnoreCase(args[0])) {
            executarDemo();
            return;
        }

        int opcao;
        do {
            mostrarMenu();
            opcao = lerInt("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> removerUsuario();
                case 4 -> cadastrarEvento();
                case 5 -> listarEventos();
                case 6 -> removerEvento();
                case 7 -> salvarTudo();
                case 0 -> {
                    salvarTudo();
                    System.out.println("Saindo... Até logo!");
                }
                default -> System.out.println("Opção inválida!");
            }
            System.out.println();
        } while (opcao != 0);
    }

    private static void executarDemo() {
        System.out.println("Executando modo demonstração (sem entrada do teclado)...\n");

        // Se não houver dados, cria alguns exemplos
        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario(1, "Ana Silva", "ana@exemplo.com", "11988887777", "Rua A, 100"));
            usuarios.add(new Usuario(2, "Bruno Lima", "bruno@exemplo.com", "11977776666", "Rua B, 200"));
        }

        if (eventos.isEmpty()) {
            eventos.add(new Evento(1, "Show da Banda X",
                    LocalDateTime.of(2025, 12, 5, 20, 0),
                    "Arena Central", Categoria.SHOW, 5000, 150.0));
            eventos.add(new Evento(2, "Corrida de Rua",
                    LocalDateTime.of(2025, 10, 20, 7, 0),
                    "Parque Municipal", Categoria.ESPORTE, 1000, 0.0));
        }

        System.out.println("=== USUÁRIOS (DEMO) ===");
        listarUsuarios();

        System.out.println("\n=== EVENTOS (DEMO) ===");
        listarEventos();

        salvarTudo();
        System.out.println("Demonstração concluída. Encerrando.");
    }

    private static void mostrarMenu() {
        System.out.println("=== SISTEMA DE ENTREGAS - MENU ===");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Listar usuários");
        System.out.println("3 - Remover usuário");
        System.out.println("4 - Cadastrar evento");
        System.out.println("5 - Listar eventos");
        System.out.println("6 - Remover evento");
        System.out.println("7 - Salvar dados");
        System.out.println("0 - Sair");
    }

    // Usuários
    private static void cadastrarUsuario() {
        int id = lerInt("ID do usuário: ");
        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");
        String telefone = lerTexto("Telefone: ");
        String endereco = lerTexto("Endereço: ");
        usuarios.add(new Usuario(id, nome, email, telefone, endereco));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void listarUsuarios() {
        System.out.println("=== USUÁRIOS ===");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        usuarios.forEach(System.out::println);
    }

    private static void removerUsuario() {
        int id = lerInt("Informe o ID do usuário a remover: ");
        boolean removed = usuarios.removeIf(u -> u.getId() == id);
        System.out.println(removed ? "Usuário removido." : "Usuário não encontrado.");
    }

    // Eventos
    private static void cadastrarEvento() {
        int id = lerInt("ID do evento: ");
        String nome = lerTexto("Nome: ");
        int ano = lerInt("Ano (ex: 2025): ");
        int mes = lerInt("Mês (1-12): ");
        int dia = lerInt("Dia (1-31): ");
        int hora = lerInt("Hora (0-23): ");
        int minuto = lerInt("Minuto (0-59): ");
        LocalDateTime dataHora = LocalDateTime.of(ano, mes, dia, hora, minuto);
        String local = lerTexto("Local: ");

        System.out.println("Categorias: ");
        for (Categoria c : Categoria.values()) {
            System.out.println("- " + c.name());
        }
        String catStr = lerTexto("Informe a categoria exatamente como acima: ");
        Categoria categoria;
        try {
            categoria = Categoria.valueOf(catStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Categoria inválida. Usando OUTROS.");
            categoria = Categoria.OUTROS;
        }

        int capacidade = lerInt("Capacidade: ");
        double preco = lerDouble("Preço: ");

        eventos.add(new Evento(id, nome, dataHora, local, categoria, capacidade, preco));
        System.out.println("Evento cadastrado com sucesso!");
    }

    private static void listarEventos() {
        System.out.println("=== EVENTOS ===");
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        eventos.forEach(System.out::println);
    }

    private static void removerEvento() {
        int id = lerInt("Informe o ID do evento a remover: ");
        boolean removed = eventos.removeIf(e -> e.getId() == id);
        System.out.println(removed ? "Evento removido." : "Evento não encontrado.");
    }

    private static void salvarTudo() {
        FileManager.salvarUsuarios(usuarios);
        FileManager.salvarEventos(eventos);
        System.out.println("Dados salvos.");
    }

    // Utils de input
    private static String nextLineOrExit() {
        if (!sc.hasNextLine()) {
            System.out.println("\nEntrada encerrada. Finalizando o programa.");
            System.exit(0);
        }
        return sc.nextLine();
    }

    private static int lerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(nextLineOrExit().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(nextLineOrExit().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    private static String lerTexto(String msg) {
        System.out.print(msg);
        return nextLineOrExit();
    }
}
