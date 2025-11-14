package app;

import model.Pedido;
import service.PizzariaService;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        PizzariaService service = new PizzariaService();

        // --- 1. Cadastro de Sabores (HashSet) ---
        service.adicionarSabor("Calabresa");
        service.adicionarSabor("Mussarela");
        service.adicionarSabor("Frango com Catupiry");
        service.adicionarSabor("Calabresa"); // Deve ser ignorado [cite: 21]
        service.listarSabores();
        System.out.println("\n---------------------------------\n");

        // --- 2. e 3. Registro e Fila de Pedidos ---
        // Pedido (numero, cliente, sabor, tamanho, valor, tempoPreparoEstimado)
        Pedido p1 = new Pedido(101, "Alice", "Mussarela", "G", 75.00, 20);
        Pedido p2 = new Pedido(102, "Bruno", "Calabresa", "M", 55.00, 15);
        Pedido p3 = new Pedido(103, "Carla", "Frango com Catupiry", "G", 85.00, 25);
        
        service.adicionarPedido(p1); 
        service.adicionarPedido(p2); 
        service.adicionarPedido(p3); 

        System.out.println("Próximo da fila de entrega (Peek): " + service.proximoParaEntrega()); [cite: 40]
        System.out.println("\n--- Lista de Pedidos Abertos (Antes da Ordenação) ---");
        service.listarPedidosAbertos();

        // Busca e Ordenação
        Optional<Pedido> busca = service.buscarPedido(102); [cite: 32]
        busca.ifPresent(p -> System.out.println("\nPedido 102 encontrado: " + p));

        service.ordenarPedidosPorValor(); [cite: 33]
        System.out.println("\n--- Pedidos Abertos Ordenados por Valor ---");
        service.listarPedidosAbertos();
        System.out.println("\n---------------------------------\n");
        
        // --- 4. Pedidos Prioritários (PriorityQueue) ---
        // Tempo de preparo: p4=10 (mais rápido), p5=30 (mais lento)
        Pedido p4 = new Pedido(104, "VIP Maria", "Pepperoni", "G", 90.00, 10);
        Pedido p5 = new Pedido(105, "Expresso João", "Quatro Queijos", "M", 70.00, 30);

        service.adicionarPrioritario(p4);
        service.adicionarPrioritario(p5);
        
        System.out.println("Próximo prioritário (o mais rápido - 10 min): " + service.proximoPrioritario()); // Deve ser p4
        System.out.println("\n---------------------------------\n");

        // --- 5. Histórico de Pedidos Cancelados (Stack) ---
        service.cancelarPedido(p3); // Move p3 para a Stack [cite: 47, 48]
        System.out.println("Último cancelado (Peek): " + service.pedidosCancelados.peek()); 
        System.out.println("Recuperando último cancelado (Pop): " + service.pedidosCancelados.pop());
        System.out.println("\n---------------------------------\n");
        
        // --- 6. Registro de Vendas por Sabor (HashMap) ---
        // Simulação de entrega e registro de vendas
        Pedido entregue1 = service.entregarProximoPedido(); // Deve ser p1
        Pedido entregue2 = service.entregarProximoPedido(); // Deve ser p2

        service.registrarVenda(entregue1);
        service.registrarVenda(entregue2);
        service.registrarVenda(p4); // p4 foi despachado do prioritário

        service.exibirRankingSabores(); [cite: 54]
    }
}