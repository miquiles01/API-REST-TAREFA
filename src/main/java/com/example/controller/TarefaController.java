package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Tarefa;
import com.example.service.TarefaService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/tarefas")
@Log4j2
public class TarefaController {

	@Autowired
	TarefaService tarefaService;

	@PostMapping
	public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
		return tarefaService.criar(tarefa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
		return tarefaService.atualizar(id, tarefaAtualizada);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> pegarTarefaPorId(@PathVariable Long id) {
		return tarefaService.pegarPorId(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tarefa> deletarTarefa(@PathVariable Long id) {
		return tarefaService.deletar(id);
	}

	@GetMapping
	public List<Tarefa> listarTarefas() {
		return tarefaService.listar();
	}

}
