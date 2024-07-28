package com.example.ToDo_App.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.service.ToDoService;


@Controller
public class ToDoController {

	@Autowired
	private ToDoService service;

	@GetMapping({ "/", "viewToDoList" })
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllToDoItems());
		model.addAttribute("message", message);

		return "ViewToDoList";

	}

	@GetMapping("/updateToDoStatus/{id}")
	public String updateToDoStatus(@PathVariable Long id, RedirectAttributes redirectattributes) {
		if (service.updateStatus(id)) {
			redirectattributes.addFlashAttribute("message", "Update Success");
			return "redirect:/viewToDoList";
		}

		redirectattributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewToDoList";
	}

	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new ToDo());

		return "AddToDoItem";
	}

	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo todo, RedirectAttributes redirectattributes) {
		if (service.saveOrUpdateToDoItem(todo)) {
			redirectattributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewToDoList";
		}

		redirectattributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addToDoItem";
	}

	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo", service.getToDoItemById(id));

		return "EditToDoItem";
	}

	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectattributes) {
		if (service.saveOrUpdateToDoItem(todo)) {
			redirectattributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewToDoList";
		}

		redirectattributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editToDoItem/" + todo.getId();
	}

	@GetMapping("/deleteToDoItem/{id}")
	public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectattributes) {

		try {
			if (service.deleteToDoItem(id)) {
				redirectattributes.addFlashAttribute("message", "Delete Success");
			}
		} catch (Exception e) {
			System.out.println("Exception encountered...");
		}
		redirectattributes.addFlashAttribute("message", "Delete Success");
		return "redirect:/viewToDoList";

	}
}
