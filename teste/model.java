package model;

import java.util.Comparator;
import java.util.Objects;

public class Pedido implements Comparable<Pedido> {
    private int numero; [cite: 24]
    private String cliente; [cite: 25]
    private String sabor; [cite: 26]
    private String tamanho; // P, M, G [cite: 27]
    private double valor; [cite: 27]
    private int tempoPreparoEstimado; // Para PriorityQueue [cite: 45]

    // Construtor
    public Pedido(int numero, String cliente, String sabor, String tamanho, double valor, int tempoPreparoEstimado) {
        this.numero = numero;
        this.cliente = cliente;
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.valor = valor;
        this.tempoPreparoEstimado = tempoPreparoEstimado;
    }

    // Getters
    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public double getValor() { return valor; }
    public int getTempoPreparoEstimado() { return tempoPreparoEstimado; }

    // Método toString para listagem
    @Override
    public String toString() {
        return "Pedido{" +
               "num=" + numero +
               ", cliente='" + cliente + '\'' +
               ", sabor='" + sabor + '\'' +
               ", tam='" + tamanho + '\'' +
               ", valor=" + valor +
               ", tempo=" + tempoPreparoEstimado + "min" +
               '}';
    }

    @Override
    public int compareTo(Pedido outro) {
        return Integer.compare(this.tempoPreparoEstimado, outro.tempoPreparoEstimado);
    }
}