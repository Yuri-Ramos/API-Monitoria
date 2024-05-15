package br.org.serratec.todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.todo.model.Category;
import br.org.serratec.todo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Lista todas os clientes", description = "A resposta lista os dados das categorias cadastradas no banco de dados")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = Category.class), mediaType = "application/json") }, description = "Retorna todos os categorias cadastrados no banco de dados"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão paraacessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna daaplicação") })

    public List<Category> buscarTodos() {
        return categoryService.buscarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category inserir(@RequestBody Category category) {

        Category userVindo = category;
        userVindo.setNome(category.getNome());
        userVindo = categoryService.inserir(userVindo);
        return userVindo;
    }

    @GetMapping("/{id}")
    public Optional<Category> buscarPorId(@PathVariable Long id) {

        return categoryService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        categoryService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> atualizar(@PathVariable Long id, Category Category) {
        var a = categoryService.inserir(Category);
        return ResponseEntity.ok(a);

    }

}
