package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.Tarefa;
import com.example.repository.TarefaRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	public ResponseEntity<Tarefa> criar(Tarefa tarefa) {
		// Verificando se a descriçao da tarefa nao e vazia ou nula
		if (tarefa.getDescription() == null || tarefa.getDescription().isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		// Salvando a tarefa no repositorio
		Tarefa tarefaCriada = tarefaRepository.save(tarefa);

		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
	}

	public ResponseEntity<Tarefa> atualizar(Long id, Tarefa tarefaAtualizada) {
		if (!tarefaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Tarefa tarefaAtualizadaSalva = tarefaRepository.save(tarefaAtualizada);
		return ResponseEntity.ok().body(tarefaAtualizadaSalva);
	}

	public ResponseEntity<Tarefa> pegarPorId(Long id) {
		Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);

		return tarefaOptional.map(tarefa -> ResponseEntity.ok().body(tarefa))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	public ResponseEntity<Tarefa> deletar(Long id) {
		if (!tarefaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		tarefaRepository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	public List<Tarefa> listar() {
		return tarefaRepository.findAll();
	}

}
