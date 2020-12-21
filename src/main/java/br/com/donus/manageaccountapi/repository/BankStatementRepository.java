package br.com.donus.manageaccountapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.donus.manageaccountapi.dto.response.StatementDTO;
import br.com.donus.manageaccountapi.model.BankStatement;

public interface BankStatementRepository extends PagingAndSortingRepository<BankStatement, Long> {

	
	@Query(value="select new br.com.donus.manageaccountapi.dto.response.StatementDTO(bt.transactionCode,bs.previousBalance,bt.value,bt.fee,bt.bonification, bs.currentBalance,bt.transactionType, bt.creationDate,dn.cpf,dn.name, rc.cpf, rc.name ) from BankStatement bs  "
			+ "inner join bs.bankAccount ba "
			+ "inner join bs.bankTransaction bt "
			+"left join bt.receiver rc "
			+"left join bt.donor dn "
			+ "where ba.id = :id order by bt.creationDate desc")
	public List<StatementDTO> findByIdBankAccount(@Param("id") Long id);
}
