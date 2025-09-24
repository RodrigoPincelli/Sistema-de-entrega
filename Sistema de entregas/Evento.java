import java.io.Serializable;
import java.time.LocalDateTime;

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private LocalDateTime dataHora;
    private String local;
    private Categoria categoria;
    private int capacidade;
    private double preco;

    public Evento(int id, String nome, LocalDateTime dataHora, String local,
                  Categoria categoria, int capacidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.dataHora = dataHora;
        this.local = local;
        this.categoria = categoria;
        this.capacidade = capacidade;
        this.preco = preco;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | %s | Local: %s | Categoria: %s | Cap: %d | Preço: R$ %.2f",
                id, nome, dataHora, local, categoria, capacidade, preco);
    }
}
