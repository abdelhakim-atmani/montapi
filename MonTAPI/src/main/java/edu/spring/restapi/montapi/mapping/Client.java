/**
 * 
 */
package edu.spring.restapi.montapi.mapping;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * @author abdelhakim
 *
 */
@Entity
@Table(name = "Clients", uniqueConstraints = { @UniqueConstraint(name = "idcard_unique", columnNames = { "idcard" }) })
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer clientID;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private Integer idCard;
	private Date creationDate;

	@OneToMany(mappedBy = "client")
	private List<Address> addresses;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private List<Account> accounts;

	public Integer getClientID() {
		return clientID;
	}

	public void setClientID(final Integer clientID) {
		this.clientID = clientID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Integer getIdCard() {
		return idCard;
	}

	public void setIdCard(final Integer idCard) {
		this.idCard = idCard;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(final List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(final List<Account> accounts) {
		this.accounts = accounts;
	}

	@PrePersist
	private void preInsert() {
		this.creationDate = new Date();
	}

}
