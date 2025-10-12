import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Evento> eventos = new ArrayList<>();
    private static final List<Projeto> projetos = new ArrayList<>();
    private static final List<Equipe> equipes = new ArrayList<>();

    public static void main(String[] args) {
        usuarios.addAll(FileManager.carregarUsuarios());
        eventos.addAll(FileManager.carregarEventos());
        projetos.addAll(FileManager.carregarProjetos());
        equipes.addAll(FileManager.carregarEquipes());

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
                case 7 -> menuProjetos();
                case 8 -> menuEquipes();
                case 9 -> salvarTudo();
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
            usuarios.add(new Usuario(1, "Ana Silva", "111.111.111-11", "ana@exemplo.com",
                    "Analista", "ana", "123456", Perfil.COLABORADOR));
            usuarios.add(new Usuario(2, "Bruno Lima", "222.222.222-22", "bruno@exemplo.com",
                    "Gerente", "bruno", "123456", Perfil.GERENTE));
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
        System.out.println("7 - Projetos (submenu)");
        System.out.println("8 - Equipes (submenu)");
        System.out.println("9 - Salvar dados");
        System.out.println("0 - Sair");
    }

    // Usuários
    private static void cadastrarUsuario() {
        int id;
        while (true) {
            id = lerInt("ID do usuário: ");
            final int idCheck = id;
            boolean existe = usuarios.stream().anyMatch(u -> u.getId() == idCheck);
            if (existe) {
                System.out.println("ID já existente para usuário. Informe outro.");
            } else break;
        }
        String nomeCompleto = lerTexto("Nome completo: ");
        String cpf;
        while (true) {
            cpf = lerTexto("CPF: ");
            if (cpfValido(cpf)) break;
            System.out.println("CPF inválido. Digite novamente (formato livre, ex.: 123.456.789-09).");
        }
        String email = lerTexto("Email: ");
        String cargo = lerTexto("Cargo: ");
        String login = lerTexto("Login: ");
        String senha = lerTexto("Senha: ");
        System.out.println("Perfis disponíveis: ADMINISTRADOR, GERENTE, COLABORADOR");
        Perfil perfil;
        try {
            perfil = Perfil.valueOf(lerTexto("Perfil: ").trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Perfil inválido. Usando COLABORADOR.");
            perfil = Perfil.COLABORADOR;
        }
        usuarios.add(new Usuario(id, nomeCompleto, cpf, email, cargo, login, senha, perfil));
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
        int id;
        while (true) {
            id = lerInt("ID do evento: ");
            final int idCheck = id;
            boolean existe = eventos.stream().anyMatch(e -> e.getId() == idCheck);
            if (existe) {
                System.out.println("ID já existente para evento. Informe outro.");
            } else break;
        }
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

    // ==== PROJETOS ====
    private static void menuProjetos() {
        int op;
        do {
            System.out.println("\n=== PROJETOS ===");
            System.out.println("1 - Cadastrar projeto");
            System.out.println("2 - Listar projetos");
            System.out.println("3 - Remover projeto");
            System.out.println("4 - Vincular equipe a projeto");
            System.out.println("0 - Voltar");
            op = lerInt("Opção: ");
            switch (op) {
                case 1 -> cadastrarProjeto();
                case 2 -> listarProjetos();
                case 3 -> removerProjeto();
                case 4 -> vincularEquipeAoProjeto();
                case 0 -> { /* voltar */ }
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private static void cadastrarProjeto() {
        int id;
        while (true) {
            id = lerInt("ID do projeto: ");
            final int idCheck = id;
            boolean existe = projetos.stream().anyMatch(p -> p.getId() == idCheck);
            if (existe) {
                System.out.println("ID já existente para projeto. Informe outro.");
            } else break;
        }
        String nome = lerTexto("Nome do projeto: ");
        String descricao = lerTexto("Descrição: ");
        System.out.println("Data de início");
        LocalDate inicio = lerData();
        System.out.println("Data de término prevista");
        LocalDate termino = lerData();

        System.out.println("Status disponíveis: PLANEJADO, EM_ANDAMENTO, CONCLUIDO, CANCELADO");
        StatusProjeto status;
        try { status = StatusProjeto.valueOf(lerTexto("Status: ").trim().toUpperCase()); }
        catch (IllegalArgumentException e) { status = StatusProjeto.PLANEJADO; }

        int gerenteId = lerInt("ID do gerente responsável: ");
        // validação simples: gerente existe e é GERENTE
        Usuario gerente = usuarios.stream().filter(u -> u.getId() == gerenteId).findFirst().orElse(null);
        if (gerente == null || gerente.getPerfil() != Perfil.GERENTE) {
            System.out.println("Atenção: gerente inválido ou não possui perfil GERENTE. Cadastro seguirá assim mesmo.");
        }

        projetos.add(new Projeto(id, nome, descricao, inicio, termino, status, gerenteId));
        System.out.println("Projeto cadastrado!");
    }

    private static void listarProjetos() {
        System.out.println("=== PROJETOS ===");
        if (projetos.isEmpty()) { System.out.println("Nenhum projeto cadastrado."); return; }
        projetos.forEach(System.out::println);
    }

    private static void removerProjeto() {
        int id = lerInt("ID do projeto a remover: ");
        boolean removed = projetos.removeIf(p -> p.getId() == id);
        System.out.println(removed ? "Projeto removido." : "Projeto não encontrado.");
    }

    private static void vincularEquipeAoProjeto() {
        int projId = lerInt("ID do projeto: ");
        Projeto p = projetos.stream().filter(x -> x.getId() == projId).findFirst().orElse(null);
        if (p == null) { System.out.println("Projeto não encontrado."); return; }
        int equipeId = lerInt("ID da equipe: ");
        Equipe e = equipes.stream().filter(x -> x.getId() == equipeId).findFirst().orElse(null);
        if (e == null) { System.out.println("Equipe não encontrada."); return; }
        if (!p.getEquipeIds().contains(equipeId)) {
            p.getEquipeIds().add(equipeId);
            System.out.println("Equipe vinculada ao projeto.");
        } else {
            System.out.println("Equipe já vinculada a este projeto.");
        }
    }

    // ==== EQUIPES ====
    private static void menuEquipes() {
        int op;
        do {
            System.out.println("\n=== EQUIPES ===");
            System.out.println("1 - Cadastrar equipe");
            System.out.println("2 - Listar equipes");
            System.out.println("3 - Remover equipe");
            System.out.println("4 - Adicionar membro (usuário) à equipe");
            System.out.println("5 - Remover membro (usuário) da equipe");
            System.out.println("0 - Voltar");
            op = lerInt("Opção: ");
            switch (op) {
                case 1 -> cadastrarEquipe();
                case 2 -> listarEquipes();
                case 3 -> removerEquipe();
                case 4 -> adicionarMembroEquipe();
                case 5 -> removerMembroEquipe();
                case 0 -> { /* voltar */ }
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private static void cadastrarEquipe() {
        int id;
        while (true) {
            id = lerInt("ID da equipe: ");
            final int idCheck = id;
            boolean existe = equipes.stream().anyMatch(eq -> eq.getId() == idCheck);
            if (existe) {
                System.out.println("ID já existente para equipe. Informe outro.");
            } else break;
        }
        String nome = lerTexto("Nome da equipe: ");
        String desc = lerTexto("Descrição: ");
        equipes.add(new Equipe(id, nome, desc));
        System.out.println("Equipe cadastrada!");
    }

    private static void listarEquipes() {
        System.out.println("=== EQUIPES ===");
        if (equipes.isEmpty()) { System.out.println("Nenhuma equipe cadastrada."); return; }
        equipes.forEach(System.out::println);
    }

    private static void removerEquipe() {
        int id = lerInt("ID da equipe a remover: ");
        boolean removed = equipes.removeIf(eq -> eq.getId() == id);
        // também remover o vínculo nos projetos
        if (removed) {
            projetos.forEach(p -> p.getEquipeIds().removeIf(eid -> eid == id));
        }
        System.out.println(removed ? "Equipe removida." : "Equipe não encontrada.");
    }

    private static void adicionarMembroEquipe() {
        int equipeId = lerInt("ID da equipe: ");
        Equipe eq = equipes.stream().filter(x -> x.getId() == equipeId).findFirst().orElse(null);
        if (eq == null) { System.out.println("Equipe não encontrada."); return; }
        int usuarioId = lerInt("ID do usuário a adicionar: ");
        if (usuarios.stream().noneMatch(u -> u.getId() == usuarioId)) {
            System.out.println("Usuário não encontrado."); return;
        }
        if (!eq.getMembroUsuarioIds().contains(usuarioId)) {
            eq.getMembroUsuarioIds().add(usuarioId);
            System.out.println("Usuário adicionado à equipe.");
        } else {
            System.out.println("Usuário já é membro da equipe.");
        }
    }

    private static void removerMembroEquipe() {
        int equipeId = lerInt("ID da equipe: ");
        Equipe eq = equipes.stream().filter(x -> x.getId() == equipeId).findFirst().orElse(null);
        if (eq == null) { System.out.println("Equipe não encontrada."); return; }
        int usuarioId = lerInt("ID do usuário a remover: ");
        boolean removed = eq.getMembroUsuarioIds().removeIf(id -> id == usuarioId);
        System.out.println(removed ? "Usuário removido da equipe." : "Usuário não era membro.");
    }

    private static void salvarTudo() {
        FileManager.salvarUsuarios(usuarios);
        FileManager.salvarEventos(eventos);
        FileManager.salvarProjetos(projetos);
        FileManager.salvarEquipes(equipes);
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

    private static LocalDate lerData() {
        int ano = lerInt("Ano (ex: 2025): ");
        int mes = lerInt("Mês (1-12): ");
        int dia = lerInt("Dia (1-31): ");
        return LocalDate.of(ano, mes, dia);
    }

    // Validação de CPF (remove não dígitos, checa tamanho, repetição e dígitos verificadores)
    private static boolean cpfValido(String cpf) {
        if (cpf == null) return false;
        String num = cpf.replaceAll("\\D", "");
        if (num.length() != 11) return false;
        // descartar sequências repetidas (ex.: 000... 111...)
        boolean todosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (num.charAt(i) != num.charAt(0)) { todosIguais = false; break; }
        }
        if (todosIguais) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                int d = num.charAt(i) - '0';
                soma += d * (10 - i);
            }
            int r = soma % 11;
            int dv1 = (r < 2) ? 0 : 11 - r;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                int d = num.charAt(i) - '0';
                soma += d * (11 - i);
            }
            r = soma % 11;
            int dv2 = (r < 2) ? 0 : 11 - r;

            return dv1 == (num.charAt(9) - '0') && dv2 == (num.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }
}
