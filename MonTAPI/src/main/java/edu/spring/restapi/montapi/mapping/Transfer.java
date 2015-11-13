/**
 * 
 */
package edu.spring.restapi.montapi.mapping;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author abdelhakim
 *
 */
@Entity
@Table(name = "Transfers")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transferID;
	
	@NotNull
	private Long amount;

	@NotNull
	private char type;
	
	@NotNull
	private Date creationDate;

	@ManyToOne
	@NotNull
	private Account account;

	public Integer getTransferID() {
		return transferID;
	}

	public void setTransferID(final Integer transferID) {
		this.transferID = transferID;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(final Long amount) {
		this.amount = amount;
	}

	public char getType() {
		return type;
	}

	public void setType(final char type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setAccount(final Account account) {
		this.account = account;
	}
	
	@PrePersist
	private void preInsert() {
		this.creationDate = new Date();
	}

}
