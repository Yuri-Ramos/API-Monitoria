package br.org.serratec.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.todo.model.Task;

public interface ITaskRepository extends JpaRepository<Task, Long> {

}
