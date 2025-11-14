package service;

import model.Pedido;
import java.util.*;

public class PizzariaService {
    // 1. Cadastro de Sabores: HashSet para evitar duplicação [cite: 18, 19]
    private Set<String> sabores = new HashSet<>();

    // 2. Registro de Pedidos Abertos: ArrayList para pedidos e ordenação [cite: 28]
    private List<Pedido> pedidosAbertos = new ArrayList<>();

    // 3. Fila de Entregas: LinkedList como Queue para FIFO [cite: 35, 36]
    private Queue<Pedido> filaEntregas = new LinkedList<>();

    // 4. Pedidos Prioritários: PriorityQueue com Comparator [cite: 43, 44]
    // O Comparator usa o método compareTo da classe Pedido (menor tempo tem prioridade)
    private PriorityQueue<Pedido> pedidosPrioritarios; 

    // 5. Histórico de Cancelados: Stack para LIFO [cite: 47]
    private Stack<Pedido> pedidosCancelados = new Stack<>();

    // 6. Registro de Vendas: HashMap para associar Sabor -> Total Vendido [cite: 50]
    private Map<String, Integer> vendasPorSabor = new HashMap<>();

    public PizzariaService() {
        // Inicializa a PriorityQueue. Ela usará a ordenação natural (compareTo) do Pedido.
        this.pedidosPrioritarios = new PriorityQueue<>();
    }

    // --- 1. Operações com Sabores (HashSet) ---
    public void adicionarSabor(String sabor) {
        if (sabores.add(sabor)) { // add retorna true se o sabor não existia [cite: 19]
            System.out.println("Sabor '" + sabor + "' cadastrado com sucesso.");
        } else {
            System.out.println("Sabor '" + sabor + "' já existe e foi ignorado."); [cite: 21]
        }
    }
    public void listarSabores() {
        System.out.println("--- Sabores Disponíveis: ---");
        sabores.forEach(System.out::println);
    }

    // --- 2. Operações com Pedidos Abertos (ArrayList) ---
    public void adicionarPedido(Pedido pedido) { [cite: 30]
        pedidosAbertos.add(pedido);
        // Também adiciona à fila de entregas normal
        filaEntregas.offer(pedido); [cite: 38]
        System.out.println("Pedido " + pedido.getNumero() + " adicionado.");
    }

    public void listarPedidosAbertos() { [cite: 31]
        pedidosAbertos.forEach(System.out::println);
    }

    public Optional<Pedido> buscarPedido(int numero) { [cite: 32]
        // Pesquisa linear
        return pedidosAbertos.stream()
                .filter(p -> p.getNumero() == numero)
                .findFirst();
    }

    public void ordenarPedidosPorValor() { [cite: 33]
        Collections.sort(pedidosAbertos, Comparator.comparing(Pedido::getValor));
    }
    
    // --- 3. Operações com Fila de Entregas (Queue/LinkedList) ---
    public Pedido entregarProximoPedido() { [cite: 39]
        return filaEntregas.poll(); // remove e retorna o elemento principal
    }

    public Pedido proximoParaEntrega() { [cite: 40]
        return filaEntregas.peek(); // consulta sem remover
    }
    
    // --- 4. Operações com Pedidos Prioritários (PriorityQueue) ---
    public void adicionarPrioritario(Pedido pedido) { [cite: 43]
        pedidosPrioritarios.offer(pedido); // Adiciona e organiza automaticamente
    }

    public Pedido proximoPrioritario() {
        return pedidosPrioritarios.poll(); // Remove o pedido de maior prioridade (menor tempo)
    }

    // --- 5. Histórico de Pedidos Cancelados (Stack) ---
    public void cancelarPedido(Pedido pedido) {
        pedidosAbertos.remove(pedido); // Remove da lista de abertos
        pedidosCancelados.push(pedido); [cite: 48]
        System.out.println("Pedido " + pedido.getNumero() + " cancelado e movido para o histórico.");
    }
    
    // --- 6. Operações com Vendas (HashMap) ---
    public void registrarVenda(Pedido pedido) {
        String sabor = pedido.sabor;
        // Incrementa a contagem de vendas para o sabor, usando 0 como default se não existir [cite: 52]
        vendasPorSabor.put(sabor, vendasPorSabor.getOrDefault(sabor, 0) + 1);
    }

    public void exibirRankingSabores() { [cite: 54]
        System.out.println("--- Ranking de Sabores Mais Vendidos ---");
        // Cria uma lista de Map.Entry para ordenação
        vendasPorSabor.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " vendas"));
    }
}