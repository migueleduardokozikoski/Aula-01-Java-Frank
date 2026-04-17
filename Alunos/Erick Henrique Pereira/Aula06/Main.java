import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final static Scanner scan = new Scanner(System.in);
    private final static LocalDate data = LocalDate.now();
    private final static List<Calculadora> vendas = new ArrayList<>();
    private static List<Vendedor> vendedores;
    private static List<Cliente> clientes;
    private static List<Loja> lojas;

    public static void main(String[] args) {

        
        Endereco endereco1 = new Endereco("São Paulo", "Jardins", "Av. Paulista, 1000");
        Endereco endereco2 = new Endereco("São Paulo", "Vila Madalena", "Rua Harmonia, 250");
        Endereco endereco3 = new Endereco("São Paulo", "Pinheiros", "Rua dos Pinheiros, 500");

        Cliente cliente1 = new Cliente("Carlos", 28, endereco1);
        Cliente cliente2 = new Cliente("Mariana", 32, endereco2);
        Cliente cliente3 = new Cliente("João", 24, endereco3);
        List<Cliente> clientes = List.of(cliente1, cliente2, cliente3);
        Main.clientes = clientes;

        Vendedor vendedor1 = new Vendedor("Ana", 30, "Loja A", endereco1, 2500.0);
        Vendedor vendedor2 = new Vendedor("Bruno", 27, "Loja B", endereco2, 2600.0);
        Vendedor vendedor3 = new Vendedor("Fernanda", 35, "Loja C", endereco3, 2700.0);
        List<Vendedor> vendedores = List.of(vendedor1, vendedor2, vendedor3);
        Main.vendedores = vendedores;

        Loja loja1 = new Loja("Loja 1", "Razão Social 1", "12345678000100", endereco1, vendedores, clientes);
        Loja loja2 = new Loja("Loja 2", "Razão Social 2", "22345678000100", endereco2, vendedores, clientes);
        Loja loja3 = new Loja("Loja 3", "Razão Social 3", "32345678000100", endereco3, vendedores, clientes);
        List<Loja> lojas = List.of(loja1, loja2, loja3);
        Main.lojas = lojas;

        System.out.println("Bem vindo ao sistema de gerencia de negócio");

        int opcao;
        do {
            opcao = mostrarMenu();
            switch (opcao) {
                case 1 -> menuVendedores();
                case 2 -> menuClientes();
                case 3 -> menuLojas();
                case 4 -> menuCalculadora();
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 5);

    }

    private static void menuCalculadora() {
        int opcao;
        do {
            System.out.println("Menu Calculadora");
            System.out.println("1 - Registrar venda");
            System.out.println("2 - Calcular troco");
            System.out.println("3 - Registro de vendas");
            System.out.println("4 - Voltar ao menu principal");
            opcao = scan.nextInt();
            do {
    switch (opcao) {
        case 1 -> {
            System.out.println("Digite a quantidade do produto:");
            int quantidade = scan.nextInt();
            System.out.println("Digite o preço do produto:");
            double preco = scan.nextDouble();
            double total = preco * quantidade;
            double totalcomdesc = (quantidade > 10) ? total * 0.95 : total;

            System.out.printf("O preço total é: %.2f\n", totalcomdesc);

            System.out.println("Digite a data da venda (dd/MM/yyyy):");
            String dataTexto = scan.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataTexto, formatter);

            Calculadora venda = new Calculadora();
            venda.setQuantidade(quantidade);
            venda.setPreco(preco);
            venda.setDescontoAplicado(totalcomdesc);
            venda.setSaleDate(data);

            vendas.add(venda);
        }
        case 2 -> {
            System.out.println("Digite o valor pago:");
            double valorPago = scan.nextDouble();
            System.out.println("Digite o preço total da compra:");
            double precoTotal = scan.nextDouble();
            double troco = valorPago - precoTotal;
            System.out.println("O troco é: " + troco);
        }
        case 3 -> {
            if (vendas.isEmpty()) {
                System.out.println("Nenhuma venda registrada.");
            } else {
                System.out.println("Digite a operação: ");
                System.out.println("1 - Exibir vendas de um dia específico");
                System.out.println("2 - Exibir todas as vendas de um mês específico");
                System.out.println("3 - Exibir todas as vendas");
                int option = scan.nextInt();
                scan.nextLine();
                switch (option) {
                    case 1 -> consultationDay(vendas);
                    case 2 -> consultationMonth(vendas);
                    case 3 -> consultationAll(vendas);
                    default -> System.out.println("Opção inválida.");
                }
            }
        }

    }

    private static void consultationAll(List<Calculadora> vendas) {
     
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        System.out.println("Todas as vendas:");
        for (Calculadora venda : vendas) {
            System.out.println("Quantidade: " + venda.getQuantidade()
                    + ", Data: " + venda.getSaleDate()
                    + ", Total com desconto: " + venda.getDescontoAplicado());
        }
    }

    private static void consultationDay(List<Calculadora> vendas) {
        System.out.println("Digite a data da venda (dd/MM/yyyy):");
        String dataConsulta = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataConsulta, formatter);
        System.out.println("Vendas do dia " + dataConsulta + ":");
        for (Calculadora venda : vendas) {
            if (venda.getSaleDate().isEqual(data)) {
                System.out.println("venda: " + venda.getQuantidade()
                 + ", data: " + venda.getSaleDate() + ", total: " + venda.getDescontoAplicado());
            }
        }
    }

    private static void consultationMonth(List<Calculadora> vendas) {
        System.out.println("Digite o mês e ano da venda (MM/yyyy):");
        String dataConsulta = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        LocalDate data = LocalDate.parse("01/" + dataConsulta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Vendas do mês " + dataConsulta + ":");
        for (Calculadora venda : vendas) {
            if (venda.getSaleDate().getMonth() == data.getMonth() && venda.getSaleDate().getYear() == data.getYear()) {
                System.out.println(venda);
            }
        }
    }
    
    private static void menuLojas() {
        int opcao;
        do {
            System.out.println("Menu Lojas");
            System.out.println("1 - Apresentar lojas");
            System.out.println("2 - Contar clientes");
            System.out.println("3 - Contar vendedores");
            System.out.println("4 - Voltar ao menu principal");
            opcao = scan.nextInt();
            switch (opcao) {
                case 1 -> {
                    System.out.println("Apresentando lojas:");
                    for (Loja l : lojas) {
                        l.apresentarse();
                        System.out.println();
                    }
                }
                case 2 -> {
                    System.out.println("Contando clientes:");
                    for (Loja l : lojas) {
                        int count = l.contarClientes();
                        System.out.println("Número de clientes na " + l.getNomeFantasia() + ": " + count);
                    }
                }
                case 3 -> {
                    System.out.println("Contando vendedores:");
                    for (Loja l : lojas) {
                        int count = l.contarVendedores();
                        System.out.println("Número de vendedores na " + l.getNomeFantasia() + ": " + count);
                    }
                }
                case 4 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida, tente novamente."); 
            }   
        } while (opcao != 4);
    }
    private static void menuClientes() {
        int opcao;
        do {
            System.out.println("Menu Clientes");
            System.out.println("1 - Apresentar clientes");
            System.out.println("2 - Voltar ao menu principal");
            opcao = scan.nextInt();
            switch (opcao) {
                case 1 -> {
                    System.out.println("Apresentando clientes:");
                    for (Cliente c : clientes) {
                        c.apresentarse();
                        System.out.println();
                    }
                }
                case 2 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 2);
    }


    private static void menuVendedores() {
        int opcao;
        do {
            System.out.println("Menu Vendedores");
            System.out.println("1 - Apresentar vendedores");
            System.out.println("2 - Calcular média salarial");
            System.out.println("3 - Calcular bônus");
            System.out.println("4 - Voltar ao menu principal");
            opcao = scan.nextInt();
            switch (opcao) {
                case 1 -> {
                    System.out.println("Apresentando vendedores:");
                    for (Vendedor v : vendedores) {
                        v.apresentarse();
                        System.out.println();
                    }
                }
                case 2 -> {
                    System.out.println("Calculando média salarial:");
                    for (Vendedor v : vendedores) {
                        double media = v.calcularMedia();
                        System.out.println("Média salarial de " + v.getName() + ": " + media);
                    }
                }
                case 3 -> {
                    System.out.println("Calculando bônus:");
                    for (Vendedor v : vendedores) {
                        double bonus = v.calcularBonus();
                        System.out.println("Bônus de " + v.getName() + ": " + bonus);
                    }
                }
                case 4 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 4);
    }
    private static int mostrarMenu(){
        System.out.println("1 - Menu vendedores");
        System.out.println("2 - Menu clientes");
        System.out.println("3 - Menu lojas");
        System.out.println("4 - Calculadora");

        return scan.nextInt();
    }
    }
}