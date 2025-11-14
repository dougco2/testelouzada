package app;

import model.Pedido;
import service.PizzariaService;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        PizzariaService service = new PizzariaService();

        service.adicionarSabor("Calabresa");
        service.adicionarSabor("Mussarela");
        service.adicionarSabor("Frango com Catupiry");
        service.adicionarSabor("Calabresa"); 
        service.listarSabores();
        System.out.println("\n---------------------------------\n");

        Pedido p1 = new Pedido(101, "Alice", "Mussarela", "G", 75.00, 20);
        Pedido p2 = new Pedido(102, "Bruno", "Calabresa", "M", 55.00, 15);
        Pedido p3 = new Pedido(103, "Carla", "Frango com Catupiry", "G", 85.00, 25);
        
        service.adicionarPedido(p1); 
        service.adicionarPedido(p2); 
        service.adicionarPedido(p3); 

        System.out.println("Próximo da fila de entrega (Peek): " + service.proximoParaEntrega()); [cite: 40]
        System.out.println("\n--- Lista de Pedidos Abertos (Antes da Ordenação) ---");
        service.listarPedidosAbertos();

      
        Optional<Pedido> busca = service.buscarPedido(102); [cite: 32]
        busca.ifPresent(p -> System.out.println("\nPedido 102 encontrado: " + p));

        service.ordenarPedidosPorValor(); [cite: 33]
        System.out.println("\n--- Pedidos Abertos Ordenados por Valor ---");
        service.listarPedidosAbertos();
        System.out.println("\n---------------------------------\n");
  
        Pedido p4 = new Pedido(104, "VIP Maria", "Pepperoni", "G", 90.00, 10);
        Pedido p5 = new Pedido(105, "Expresso João", "Quatro Queijos", "M", 70.00, 30);

        service.adicionarPrioritario(p4);
        service.adicionarPrioritario(p5);
        
        System.out.println("Próximo prioritário (o mais rápido - 10 min): " + service.proximoPrioritario()); 
        System.out.println("\n---------------------------------\n");

        service.cancelarPedido(p3); 
        System.out.println("Último cancelado (Peek): " + service.pedidosCancelados.peek()); 
        System.out.println("Recuperando último cancelado (Pop): " + service.pedidosCancelados.pop());
        System.out.println("\n---------------------------------\n");
        
        Pedido entregue1 = service.entregarProximoPedido(); 
        Pedido entregue2 = service.entregarProximoPedido(); 

        service.registrarVenda(entregue1);
        service.registrarVenda(entregue2);
        service.registrarVenda(p4); 

        service.exibirRankingSabores(); [cite: 54]
    }
}