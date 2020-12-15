package br.com.donus.manageaccountapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.dto.ContaBancariaDTO;
import br.com.donus.manageaccountapi.model.ContaBancaria;
import br.com.donus.manageaccountapi.repository.ContaBancariaRepository;
import br.com.donus.manageaccountapi.service.ContaBancariaService;

@Service
public class ContaBancariaServiceImpl implements ContaBancariaService{

	Logger logger = LoggerFactory.getLogger(ContaBancariaServiceImpl.class);
	
	@Autowired
	ContaBancariaRepository cbRepository;
	
	@Override
	public ContaBancariaDTO criar(ContaBancariaDTO cbDTO) {
		ContaBancaria cb = parseDtoToEntity(cbDTO);
		try {			
			return parseEntityToDTO(cbRepository.save(cb));
		} catch (DataIntegrityViolationException e) {
			logger.error("erro de violação de campo único");
			throw e;
		} catch (Exception e) {
			logger.error("erro genérico");
			throw e;
		}
	
	}

	private ContaBancaria parseDtoToEntity(ContaBancariaDTO cbDTO) {
		ContaBancaria cb = new ContaBancaria();
		cb.setNome(cbDTO.getNome());
		cb.setCpf(cbDTO.getCpf());
		return cb;
	}
	
	private ContaBancariaDTO parseEntityToDTO(ContaBancaria entity) {
		ContaBancariaDTO cbDTO = new ContaBancariaDTO();
		cbDTO.setCpf(entity.getCpf());
		cbDTO.setNome(entity.getNome());
		cbDTO.setId(entity.getId());
		return cbDTO;
	}
}
