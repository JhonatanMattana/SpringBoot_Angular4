package br.com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.model.PessoaModel;
import br.com.blog.model.ResponseModel;
import br.com.blog.repository.PessoaRepository;

@RestController
@RequestMapping("/service")
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	/*
	 Salvar um novo registro
	 @param pessoa
	 @return
	 */
	
	@RequestMapping(value="/pessoa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody PessoaModel pessoa) {
		try {
			this.pessoaRepository.save(pessoa);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
		} catch (Exception e) {
			return new ResponseModel(0,e.getMessage());
		}
	}
	
	/*
	 *Atualizar o registro de uma pessoa
	 *@param pessoa
	 *return 
	 */
	
	@RequestMapping(value="/pessoa", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody PessoaModel pessoa) {
		try {
			this.pessoaRepository.save(pessoa);
			return new ResponseModel(1,"Registro atualizado com sucesso!");
		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}
	
	/*
	 *Consultar todos as pessos
	 * @return
	 */
	
	@RequestMapping(value="/pessoa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<PessoaModel> consultar(){
		return this.pessoaRepository.fildAll();
	}
	
	/*
	 *Buscar uma pessoa pelo código
	 *@param codigo
	 *@return 
	 */
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody PessoaModel buscar(@PathVariable("codigo") Integer codigo) {
		return this.pessoaRepository.findOne(codigo);
	}

	/*
	 *Excluir um registro pelo código
	 *@param codigo
	 *@return 
	 */
	
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Integer codigo) {
		
		PessoaModel pessoaModel = pessoaRepository.findOne(codigo);
		
		try {
			pessoaRepository.delete(pessoaModel);
			return new ResponseModel(1, "Registro excluido com sucesso!");
		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}
}
