import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha; // simples; ideal: armazenar hash
    private Perfil perfil; // ADMINISTRADOR, GERENTE, COLABORADOR

    public Usuario(int id, String nomeCompleto, String cpf, String email,
                   String cargo, String login, String senha, Perfil perfil) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nome: %s | CPF: %s | Email: %s | Cargo: %s | Perfil: %s | Login: %s",
                id, nomeCompleto, cpf, email, cargo, perfil, login);
    }
}
