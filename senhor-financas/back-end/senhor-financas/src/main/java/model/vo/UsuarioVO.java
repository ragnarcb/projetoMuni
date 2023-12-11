package model.vo;

import java.time.LocalDateTime;

public class UsuarioVO {

    private int idUsuario;
    private String nome;
    private String cpf;
    private String email;
    private LocalDateTime datanascimento;
    private String login;
    private String senha;

    public UsuarioVO(int idUsuario, String nome, String cpf, String email, LocalDateTime datanascimento, String login, String senha) {
        super();
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.datanascimento = datanascimento;
        this.login = login;
        this.senha = senha;
    }

    public UsuarioVO() {
        super();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(LocalDateTime datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Código do Usuario:" + idUsuario +
                "\n Nome: " + nome +
                "\n CPF: " + cpf +
                "\n Email :" + email +
                "\n Data de Nascimento: " + datanascimento +
                "\n Login: " + login +
                "\n Senha: " + senha;
    }
}
