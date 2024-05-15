package br.org.serratec.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.todo.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
