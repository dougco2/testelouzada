package service;

import model.Pedido;
import java.util.*;

public class PizzariaService {
    private Set<String> sabores = new HashSet<>();

    private List<Pedido> pedidosAbertos = new ArrayList<>();

    private Queue<Pedido> filaEntregas = new LinkedList<>();

    private PriorityQueue<Pedido> pedidosPrioritarios; 

    private Stack<Pedido> pedidosCancelados = new Stack<>();

    private Map<String, Integer> vendasPorSabor = new HashMap<>();

    public PizzariaService() {

        this.pedidosPrioritarios = new PriorityQueue<>();
    }

    public void adicionarSabor(String sabor) {
        if (sabores.add(sabor)) { 
            System.out.println("Sabor '" + sabor + "' cadastrado com sucesso.");
        } else {
            System.out.println("Sabor '" + sabor + "' já existe e foi ignorado."); [cite: 21]
        }
    }
    public void listarSabores() {
        System.out.println("--- Sabores Disponíveis: ---");
        sabores.forEach(System.out::println);
    }

    
    public void adicionarPedido(Pedido pedido) { [cite: 30]
        pedidosAbertos.add(pedido);
        filaEntregas.offer(pedido); [cite: 38]
        System.out.println("Pedido " + pedido.getNumero() + " adicionado.");
    }

    public void listarPedidosAbertos() { [cite: 31]
        pedidosAbertos.forEach(System.out::println);
    }

    public Optional<Pedido> buscarPedido(int numero) { [cite: 32]
        return pedidosAbertos.stream()
                .filter(p -> p.getNumero() == numero)
                .findFirst();
    }

    public void ordenarPedidosPorValor() { [cite: 33]
        Collections.sort(pedidosAbertos, Comparator.comparing(Pedido::getValor));
    }
    
    
    public Pedido entregarProximoPedido() { [cite: 39]
        return filaEntregas.poll(); 
    }

    public Pedido proximoParaEntrega() { [cite: 40]
        return filaEntregas.peek(); 
    }
    
    
    public void adicionarPrioritario(Pedido pedido) { [cite: 43]
        pedidosPrioritarios.offer(pedido); 
    }

    public Pedido proximoPrioritario() {
        return pedidosPrioritarios.poll(); 
    }

    
    public void cancelarPedido(Pedido pedido) {
        pedidosAbertos.remove(pedido); 
        pedidosCancelados.push(pedido); [cite: 48]
        System.out.println("Pedido " + pedido.getNumero() + " cancelado e movido para o histórico.");
    }
    

    public void registrarVenda(Pedido pedido) {
        String sabor = pedido.sabor;
        vendasPorSabor.put(sabor, vendasPorSabor.getOrDefault(sabor, 0) + 1);
    }

    public void exibirRankingSabores() { [cite: 54]
        System.out.println("--- Ranking de Sabores Mais Vendidos ---");
        vendasPorSabor.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " vendas"));
    }
}
