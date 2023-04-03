package io.github.da20web.springthymeleaf.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	@SequenceGenerator(name = "id", sequenceName = "tarefa_id_seq", allocationSize = 1)
	private Long id;
	private String nome;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date data_execucao;

	public Tarefa(Long id, String nome, Date data_execucao) {
		this.id = id;
		this.nome = nome;
		this.data_execucao = data_execucao;
	}

	public Tarefa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_execucao() {
		return data_execucao;
	}

	public void setData_execucao(Date data_execucao) {
		this.data_execucao = data_execucao;
	}

}
