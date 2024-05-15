package br.org.serratec.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.todo.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
