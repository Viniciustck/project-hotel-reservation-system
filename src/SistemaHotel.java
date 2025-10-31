import java.util.Scanner;
import java.util.List;
import java.util.Locale;
import java.util.InputMismatchException;

public class SistemaHotel {

    private final Scanner scanner;
    private final ReservaService reservaService;

    public SistemaHotel(ReservaService reservaService) {
        this.scanner = new Scanner(System.in);
        this.scanner.useLocale(Locale.US);
        this.reservaService = reservaService;
    }

    public void run() {
        System.out.println("Bem-vindo ao Sistema de Controle de Reservas de Hotel!");

        while (true) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1: cadastrarReserva(); break;
                case 2: listarReservas(); break;
                case 3: buscarReserva(); break;
                case 4: ordenarReservas(); break;
                case 5:
                    System.out.println("Saindo do sistema... Obrigado!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private int lerOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    private void exibirMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1 - Cadastrar nova reserva");
        System.out.println("2 - Listar reservas");
        System.out.println("3 - Buscar reserva por nome");
        System.out.println("4 - Ordenar reservas por dias (decrescente)");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarReserva() {
        System.out.println("\n--- 1. Cadastro de Nova Reserva ---");
        try {
            System.out.print("Nome do hóspede: ");
            String nome = scanner.nextLine();
            TipoQuarto tipoQuarto = lerTipoQuarto();
            System.out.print("Número de dias da estadia: ");
            int dias = scanner.nextInt();
            System.out.print("Valor da diária (use PONTO, ex: 150.50): R$");
            double diaria = scanner.nextDouble();
            scanner.nextLine();

            Reserva novaReserva = new Reserva(nome, tipoQuarto, dias, diaria);

            System.out.println("\n--- Detalhes da Reserva ---");
            System.out.println(novaReserva);

            System.out.print("Deseja confirmar a reserva? (Sim/Nao): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("Sim")) {
                reservaService.adicionarReserva(novaReserva);
                System.out.println("Reserva cadastrada com sucesso!");
            } else {
                System.out.println("Cadastro de reserva cancelado.");
            }

        } catch (IllegalArgumentException | HotelLotadoException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: Valor numérico inválido.");
            scanner.nextLine();
        }
    }

    private TipoQuarto lerTipoQuarto() {
        while (true) {
            System.out.print("Tipo do quarto (STANDARD, LUXO, PRESIDENCIAL): ");
            String tipoInput = scanner.nextLine().toUpperCase();
            try {
                return TipoQuarto.valueOf(tipoInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido. Tente novamente.");
            }
        }
    }

    private void listarReservas() {
        System.out.println("\n--- 2. Lista de Todas as Reservas ---");
        List<Reserva> todasAsReservas = reservaService.getTodasReservas();

        if (todasAsReservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }
        for (Reserva reserva : todasAsReservas) {
            System.out.println(reserva);
        }
    }

    private void buscarReserva() {
        System.out.println("\n--- 3. Busca de Reserva por Nome ---");
        if (reservaService.getTotalReservas() == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        System.out.print("Digite parte do nome do hóspede: ");
        String termoBusca = scanner.nextLine();
        List<Reserva> resultados = reservaService.buscarPorNome(termoBusca);

        if (resultados.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada.");
        } else {
            System.out.println("Reservas encontradas:");
            for (Reserva reserva : resultados) {
                System.out.println(reserva);
            }
        }
    }

    private void ordenarReservas() {
        System.out.println("\n--- 4. Ordenando Reservas por Dias ---");
        if (reservaService.getTotalReservas() < 2) {
            System.out.println("Não há reservas suficientes para ordenar.");
            return;
        }

        reservaService.ordenarPorDiasDecrescente();
        System.out.println("Reservas ordenadas com sucesso!");
        listarReservas();
    }
}