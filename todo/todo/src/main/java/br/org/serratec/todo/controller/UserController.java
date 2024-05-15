package br.org.serratec.todo.controller;

import java.util.List;
import java.util.Optional; // Add this import statement

import org.springframework.beans.BeanUtils;
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

import br.org.serratec.todo.model.User;
import br.org.serratec.todo.repository.IUserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @GetMapping
    public List<User> buscarTodos() {
        return userRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User inserir(@RequestBody @Valid User user) {
        User userVindo = userRepository.save(user);

        return userVindo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable @Valid Long id) {
        if (userRepository.existsById(id)) {
            User uservindo = userRepository.findById(id).orElse(null);
            return ResponseEntity.ok(uservindo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<User> atualizar(@PathVariable Long id, User user) {
    // if (!userRepository.existsById(id)) {
    // return ResponseEntity.notFound().build();
    // }
    // user.setId(id);
    // User userVindo = userRepository.save(user);
    // return ResponseEntity.ok(userVindo);

    // }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody User userAtualizado) {
        // Verificar se o usuário existe no banco de dados
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userExistente = userOptional.get();

        // Copiar apenas os atributos não nulos do usuário atualizado para o usuário
        // existente
        BeanUtils.copyProperties(userAtualizado, userExistente, "id");

        // Salvar as alterações no banco de dados
        User userAtualizadoNoBanco = userRepository.save(userExistente);

        return ResponseEntity.ok(userAtualizadoNoBanco);
    }
}
