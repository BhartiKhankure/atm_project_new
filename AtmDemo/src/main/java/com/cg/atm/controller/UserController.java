package com.cg.atm.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.atm.dto.AccountDto;
import com.cg.atm.dto.UserDto;
import com.cg.atm.entity.Account;
import com.cg.atm.entity.Transaction;
import com.cg.atm.entity.User;
import com.cg.atm.exception.InsufficientAccBalanceException;
import com.cg.atm.repository.AccountRepository;
import com.cg.atm.repository.TransactionRepository;
import com.cg.atm.repository.UserRepository;
import com.cg.atm.service.TransactionService;
import com.cg.atm.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {

	//private static final long User = 0;

	@Autowired
	private UserService userService;
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/create")
	public User createUser(@RequestBody UserDto userDto) {
		User userRequest = modelMapper.map(userDto, User.class);
		User user = userService.createUser(userRequest);
		return user;
	
	}

	@GetMapping("/getUsers")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@DeleteMapping("/delete/user/{userId}")
	public User deleteUser(@PathVariable("userId") long userId) {
		User user = userService.deleteUser(userId);
		return user;
	}

	@PutMapping(value = "/deposit/{accNo}/{amount}")
	public Account deposit(@PathVariable("accNo") long accNo, @PathVariable("amount") double amount) {
		Account account = userService.deposit(accNo, amount);
		return account;
	}

	@PutMapping(value = "/withdraw/{accNo}/{amount}")
	public void withdraw(@PathVariable("accNo") long accNo, @PathVariable("amount") double amount)
			throws InsufficientAccBalanceException {
		userService.withdraw(accNo, amount);
	} 
 
	@PostMapping("/transfer/{senderAccNo}/{receiverAccNo}/{amount}")
	public double transferMoney(@PathVariable("senderAccNo") long senderAccNo,
			@PathVariable("receiverAccNo") long receiverAccNo, @PathVariable("amount") double amount,
			@RequestBody Account account) {
		transactionService.transferMoney(senderAccNo, receiverAccNo, amount);    
		// AccountDto accountDto = new AccountDto();
		return amount;
	}

	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}
	
	@GetMapping("/accounts")
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

}
