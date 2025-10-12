import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipe implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String descricao;
    private List<Integer> membroUsuarioIds = new ArrayList<>(); // ids de Usuario

    public Equipe(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public List<Integer> getMembroUsuarioIds() { return membroUsuarioIds; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Membros(IDs): %s", id, nome, membroUsuarioIds);
    }
}
