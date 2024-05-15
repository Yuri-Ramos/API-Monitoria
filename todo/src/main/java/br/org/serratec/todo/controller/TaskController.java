package br.org.serratec.todo.controller;

import java.util.List;

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

import br.org.serratec.todo.model.Task;
import br.org.serratec.todo.repository.ITaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @GetMapping
    // @Operation(summary = "Lista todos os clientes", description = "A resposta
    // lista os dados dos clientes id, nome, cpf e email.")
    // @ApiResponses(value = {
    // @ApiResponse(responseCode = "200", content = {
    // @Content(schema = @Schema(implementation = Task.class), mediaType =
    // "application/json") }, description = "Retorna todos os clientes"),
    // @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
    // @ApiResponse(responseCode = "403", description = "Não há permissão para
    // acessar o recurso"),
    // @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
    // @ApiResponse(responseCode = "505", description = "Exceção interna da
    // aplicação") })

    public List<Task> buscarTodos() {
        return taskRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task inserir(@RequestBody @Valid Task task) {
        Task taskVindo = taskRepository.save(task);

        return taskVindo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> buscarPorId(@PathVariable Long id) {
        if (taskRepository.existsById(id)) {
            Task taskvindo = taskRepository.findById(id).orElse(null);
            return ResponseEntity.ok(taskvindo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> atualizar(@PathVariable Long id, Task task) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        task.setId(id);
        Task taskVindo = taskRepository.save(task);
        return ResponseEntity.ok(taskVindo);

    }

}
