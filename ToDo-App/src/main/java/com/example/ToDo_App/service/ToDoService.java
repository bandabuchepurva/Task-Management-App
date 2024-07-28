package com.example.ToDo_App.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.repo.IToDoRepo;


@Service
public class ToDoService {

	@Autowired
	IToDoRepo repo;

	public List<ToDo> getAllToDoItems() {
		ArrayList<ToDo> todolist = new ArrayList<ToDo>();
		repo.findAll().forEach(todo -> todolist.add(todo));

		return todolist;
	}

	public ToDo getToDoItemById(Long id) {
		return repo.findById(id).get();
	}

	public boolean updateStatus(Long id) {
		ToDo todo = getToDoItemById(id);
		todo.setStatus("Completed");

		return saveOrUpdateToDoItem(todo);
	}

	public boolean saveOrUpdateToDoItem(ToDo todo) {
		ToDo updatedobj = repo.save(todo);

		if (getToDoItemById(updatedobj.getId()) != null)
			return true;

		return false;
	}

	public boolean deleteToDoItem(Long id) {
		repo.deleteById(id);

		if (getToDoItemById(id) == null)
			return true;

		return false;
	}

}

