package io.github.da20web.springthymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.da20web.springthymeleaf.model.Tarefa;
import io.github.da20web.springthymeleaf.repository.TarefaRepository;

@Controller
public class TarefaController {

	List<Tarefa> tarefas = new ArrayList<>(); // Array de Objetos 'Tarefa' para simular banco de dados

	private final TarefaRepository repository;

	public TarefaController(TarefaRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public ModelAndView initial() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("titulo", "Central de Tarefas");
		return mv;
	}

	@GetMapping("/create")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("create");
		mv.addObject("tarefa", new Tarefa());
		return mv;
	}

	@PostMapping("/create")
	public String create(Tarefa tarefa) {
		/*
		 * if (tarefa.getId() != null) { Tarefa tarefaFind =
		 * tarefas.stream().filter(tarefaItem ->
		 * tarefa.getId().equals(tarefaItem.getId())) .findFirst().get();
		 * tarefas.set(tarefas.indexOf(tarefaFind), tarefa); } else { Long id =
		 * tarefas.size() + 1L; // Incrementador de id tarefas.add(new Tarefa(id,
		 * tarefa.getNome(), tarefa.getData_execucao())); }
		 */
		/*
		 * if (tarefa.getId() != null) { repository.save(tarefa); } else {
		 * System.out.println("### ###" + tarefa.getNome()); }
		 */
		repository.save(tarefa); // Função 'save' salva se for novo ou atualiza se já existir
		return "redirect:/list";
	}

	/*
	 * @GetMapping("/list") public ModelAndView list() { ModelAndView mv = new
	 * ModelAndView("list"); mv.addObject("tarefas", tarefas); return mv; }
	 */

	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("list");
		mv.addObject("tarefas", repository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		return mv;
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		// ModelAndView mv = new ModelAndView("create");
		// Tarefa tarefaFind = tarefas.stream().filter(tarefa ->
		// id.equals(tarefa.getId())).findFirst().get();

		// Optional<Tarefa> tarefaId = repository.findById(id);

		Tarefa tarefa = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		model.addAttribute("tarefa", tarefa);

		/*
		 * System.out.println(" ### " + tarefa.getId()); System.out.println(" ### " +
		 * tarefa.getNome()); System.out.println(" ### " + tarefa.getData_execucao());
		 */

		return "create";

		// mv.addObject("tarefa", tarefaId);
		// return mv;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		/*
		 * Tarefa tarefaFind = tarefas.stream().filter(tarefaItem ->
		 * tarefa.getId().equals(tarefaItem.getId())).findFirst() .get();
		 * 
		 * tarefas.remove(tarefaFind);
		 */

		Tarefa tarefa = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		repository.delete(tarefa);
		return "redirect:/list";
	}
}