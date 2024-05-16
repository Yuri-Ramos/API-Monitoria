package br.org.serratec.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.org.serratec.todo.model.Category;
import br.org.serratec.todo.model.DTO.CategoryDTO;
import br.org.serratec.todo.repository.ICategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    // public List<Category> buscarTodos() {
    // return categoryRepository.findAll();
    // }
    // public List<CategoryDTO> findAll() {
    // // estamos retornando a lista do banco e salvando
    // List<Category> categories = categoryRepository.findAll();
    // // criando um arrayListDTO
    // List<CategoryDTO> categoryDTOs = new ArrayList<>();
    // // Nesse laço estamos comparando a lista do banco com a lista do DTO
    // for (Category category : categories) {
    // categoryDTOs.add(new CategoryDTO(category.getNome()));
    // }
    // return categoryDTOs;
    // }
    public List<CategoryDTO> findAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> new CategoryDTO(category.getNome()))
                .collect(Collectors.toList());
    }

    // public Category inserir(@RequestBody Category category) {

    // Category userVindo = category;
    // userVindo.setNome(category.getNome());
    // userVindo = categoryRepository.save(userVindo);
    // return userVindo;
    // }
    public CategoryDTO inserir(CategoryDTO categoryDTO) {
        Category novaCategory = new Category();
        novaCategory.setNome(categoryDTO.getNome());
        try {
            Category categorySalva = categoryRepository.save(novaCategory);
            return new CategoryDTO(categorySalva.getNome());
        } catch (Exception e) {
            return null;

        }
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
