public class Main {
    public static void main(String[] args) {

        Cliente cliente1 = new Cliente("Mariana", 25, "Assis", "Centro", "Rua A");
        Cliente cliente2 = new Cliente("Carlos", 30, "Assis", "Vila Nova", "Rua B");

        Loja loja = new Loja(
                "My Plant",
                "My Plant LTDA",
                "12.345.678/0001-99",
                "Assis",
                "Centro",
                "Avenida Principal",
                new Vendedor[2],
                new Cliente[]{cliente1, cliente2}
        );

        double[] salariosVendedor1 = {2000.0, 2100.0, 2200.0};
        double[] salariosVendedor2 = {1800.0, 1850.0, 1900.0};

        Vendedor vendedor1 = new Vendedor(
                "João",
                28,
                loja,
                "Assis",
                "Centro",
                "Rua das Flores",
                2000.0,
                salariosVendedor1
        );

        Vendedor vendedor2 = new Vendedor(
                "Ana",
                32,
                loja,
                "Assis",
                "Jardim Europa",
                "Rua das Palmeiras",
                1800.0,
                salariosVendedor2
        );

        loja.vendedores[0] = vendedor1;
        loja.vendedores[1] = vendedor2;

        System.out.println("DADOS DA LOJA");
        loja.apresentarSe();
        System.out.println("Quantidade de clientes: " + loja.contarClientes());
        System.out.println("Quantidade de vendedores: " + loja.contarVendedores());

        System.out.println("\nDADOS DO VENDEDOR 1");
        vendedor1.apresentarSe();
        System.out.println("Média salarial: " + vendedor1.calcularMedia());
        System.out.println("Bônus: " + vendedor1.calcularBonus());

        System.out.println("\nDADOS DO VENDEDOR 2");
        vendedor2.apresentarSe();
        System.out.println("Média salarial: " + vendedor2.calcularMedia());
        System.out.println("Bônus: " + vendedor2.calcularBonus());

        System.out.println("\nDADOS DOS CLIENTES");
        cliente1.apresentarSe();
        cliente2.apresentarSe();
    }
}