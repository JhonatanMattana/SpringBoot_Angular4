package br.com.blog.repository;

import java.util.List;

import br.com.blog.model.PessoaModel;

public interface PessoaRepository {
	
	void save(PessoaModel pessoa);
	void delete(PessoaModel pessoa);
	List<PessoaModel> fildAll();
	PessoaModel findOne(Integer id);
}
