/**
 * 
 */
package edu.spring.restapi.montapi.mapping;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Account table.
 * 
 * @author abdelhakim
 *
 */
@Entity
@Table(name = "Accounts", uniqueConstraints = { @UniqueConstraint(name = "iban_unique", columnNames = { "iban" }) })
public class Account {

	/**
	 * accountID: Primary key auto-generated.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer accountID;

	/**
	 * iban: Cannot be null and must be unique.
	 */
	@NotNull
	private String iban;

	/**
	 * active: Defined if the account is active or not.
	 */
	@NotNull
	private Boolean active;

	/**
	 * amount: Account balance.
	 */
	@NotNull
	private Long amount;

	/**
	 * creationDate: Date of the creation of the account.
	 */
	private Date creationDate;

	/**
	 * updateDate: Updated date of the account. When a transfer is done, this
	 * value is updated with the current date.
	 */
	private Date updateDate;

	/**
	 * client: Link to the table CLIENT. An account <b>MUST<b> be link to a client.
	 */
	@ManyToOne
	@JoinColumn(name = "clientID", nullable = false)
	private Client client;

	/**
	 * transfers: All transfers done for this account.
	 */
	@OneToMany(mappedBy = "account")
	private Set<Transfer> transfers;

	public Integer getAccountID() {
		return accountID;
	}

	public void setAccountID(final Integer accountID) {
		this.accountID = accountID;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(final String iban) {
		this.iban = iban;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(final Long amount) {
		this.amount = amount;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date isUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(final Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setClient(final Client client) {
		this.client = client;
	}

	public Set<Transfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(final Set<Transfer> transfers) {
		this.transfers = transfers;
	}

	@PrePersist
	private void preInsert() {
		this.creationDate = new Date();
		this.updateDate = new Date();
		this.active = true;
	}

}
