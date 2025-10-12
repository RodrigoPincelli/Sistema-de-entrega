import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projeto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private StatusProjeto status;
    private int gerenteId; // id do usuário gerente responsável
    private List<Integer> equipeIds = new ArrayList<>(); // ids de equipes atuantes

    public Projeto(int id, String nome, String descricao, LocalDate dataInicio,
                   LocalDate dataTerminoPrevista, StatusProjeto status, int gerenteId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = status;
        this.gerenteId = gerenteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataTerminoPrevista() { return dataTerminoPrevista; }
    public void setDataTerminoPrevista(LocalDate dataTerminoPrevista) { this.dataTerminoPrevista = dataTerminoPrevista; }
    public StatusProjeto getStatus() { return status; }
    public void setStatus(StatusProjeto status) { this.status = status; }
    public int getGerenteId() { return gerenteId; }
    public void setGerenteId(int gerenteId) { this.gerenteId = gerenteId; }
    public List<Integer> getEquipeIds() { return equipeIds; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Início: %s | Término prev.: %s | Status: %s | GerenteId: %d | Equipes: %s",
                id, nome, dataInicio, dataTerminoPrevista, status, gerenteId, equipeIds);
    }
}
