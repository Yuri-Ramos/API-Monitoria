package br.org.serratec.todo.model.DTO;

public class CategoryDTO {

    private String nome;

    public CategoryDTO() {
    }

    public CategoryDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
