package br.org.serratec.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.org.serratec.todo.model.Category;
import br.org.serratec.todo.repository.ICategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> buscarTodos() {
        return categoryRepository.findAll();
    }

    public Category inserir(@RequestBody Category category) {

        Category userVindo = category;
        userVindo.setNome(category.getNome());
        userVindo = categoryRepository.save(userVindo);
        return userVindo;
    }

    public Optional<Category> buscarPorId(@PathVariable Long id) {

        return categoryRepository.findById(id);
    }

    public Category atualizar(Long id, Category categoria) {
        categoria.setId(id);
        return categoryRepository.save(categoria);
    }

    public void deletar(Long id) {
        categoryRepository.deleteById(id);
    }

}
