package com.example.ToDo_App.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ToDo_App.model.ToDo;

public interface IToDoRepo extends JpaRepository<ToDo, Long> {

}
