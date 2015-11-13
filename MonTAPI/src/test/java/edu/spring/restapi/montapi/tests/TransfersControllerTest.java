package edu.spring.restapi.montapi.tests;

import static edu.spring.restapi.montapi.Messages.ACCOUNT_NOT_EXISTS;
import static edu.spring.restapi.montapi.Messages.NO_ENOUGH_MONEY;
import static edu.spring.restapi.montapi.Messages.SUCCESS;
import static edu.spring.restapi.montapi.Messages.FAILED;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.spring.restapi.montapi.Application;
import edu.spring.restapi.montapi.mapping.Account;
import edu.spring.restapi.montapi.mapping.Client;
import edu.spring.restapi.montapi.repositories.AccountRepository;
import edu.spring.restapi.montapi.repositories.ClientRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TransfersControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AccountRepository accountRepository;

	private static URI transferURI;

	private static String NON_EXISTING_IBAN = "FR_FAKE";
	private static String SOURCE_IBAN = "FR1234567";
	private static String TARGET_IBAN = "FR2222222";
	private static Long SOURCE_BALANCE = 200L;
	private static Long TARGET_BALANCE = 300L;
	private static Long AMOUNT_TRANSFER_OK = 20L;
	private static Long AMOUNT_TRANSFER_FAILED = SOURCE_BALANCE + 20L;

	@Before
	public void setUp() throws Exception {
		transferURI = new URI("/montapi/transfer");
		// To be sure that the initialisation of the database will run only once.
		if (accountRepository.getAccountByIban(SOURCE_IBAN) == null) {
			Client client = new Client();
			client.setFirstName("Test");
			client.setLastName("Toto");
			client.setIdCard(1111111);

			client = clientRepository.save(client);

			Account account = new Account();
			account.setActive(true);
			account.setClient(client);
			account.setAmount(SOURCE_BALANCE);
			account.setIban(SOURCE_IBAN);

			accountRepository.save(account);

			Account account2 = new Account();
			account2.setActive(true);
			account2.setAmount(TARGET_BALANCE);
			account2.setClient(client);
			account2.setIban(TARGET_IBAN);

			accountRepository.save(account2);
		}

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void sourceIbanNotExisting() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(transferURI).param("iban_source", NON_EXISTING_IBAN)
				.param("iban_target", TARGET_IBAN).param("amount", String.valueOf(AMOUNT_TRANSFER_OK)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("status").value(FAILED))
				.andExpect(MockMvcResultMatchers.jsonPath("message").value(ACCOUNT_NOT_EXISTS + NON_EXISTING_IBAN));
	}

	@Test
	public void targetIbanNotExisting() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(transferURI).param("iban_source", SOURCE_IBAN)
				.param("iban_target", NON_EXISTING_IBAN).param("amount", String.valueOf(AMOUNT_TRANSFER_OK)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("status").value(FAILED))
				.andExpect(MockMvcResultMatchers.jsonPath("message").value(ACCOUNT_NOT_EXISTS + NON_EXISTING_IBAN));
	}

	@Test
	public void balanceTooLow() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(transferURI).param("iban_source", SOURCE_IBAN)
				.param("iban_target", TARGET_IBAN).param("amount", String.valueOf(AMOUNT_TRANSFER_FAILED)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("status").value(FAILED))
				.andExpect(MockMvcResultMatchers.jsonPath("message").value(NO_ENOUGH_MONEY));
	}

	@Test
	public void successfull() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(transferURI).param("iban_source", SOURCE_IBAN)
				.param("iban_target", TARGET_IBAN).param("amount", String.valueOf(AMOUNT_TRANSFER_OK)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("status").value(SUCCESS))
				.andExpect(MockMvcResultMatchers.jsonPath("message").value("Operation done successfully."));
	}
}
