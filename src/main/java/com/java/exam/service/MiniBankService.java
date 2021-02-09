package com.java.exam.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.exam.component.BankAccountNumber;
import com.java.exam.component.CustomException;
import com.java.exam.enums.ActivityEnum;
import com.java.exam.enums.RoleName;
import com.java.exam.model.BankAccount;
import com.java.exam.model.History;
import com.java.exam.model.User;
import com.java.exam.repository.BankAccountRepository;
import com.java.exam.repository.HistoryRepository;
import com.java.exam.repository.RoleRepository;
import com.java.exam.repository.UserRepository;
import com.java.exam.request.DepositRequest;
import com.java.exam.request.UserRequest;
import com.java.exam.security.AppUserDetails;

@Service
public class MiniBankService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BankAccountRepository bankAccountRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private HistoryRepository historyRepository;
	
	public Map<String, Object> createAccount(UserRequest request) {
		User user = new User();
		user.setName(request.getName());
		if(userRepository.existsByUsername(request.getUsername())) {
			throw new CustomException("username already exists",10);
		}
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles(Arrays.asList(roleRepository.findByAuthority(RoleName.ROLE_USER)
                .orElseThrow(() -> new CustomException("role not found", 10))));
		userRepository.save(user);
		
		BankAccountNumber ban = new BankAccountNumber();
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountNumber(ban.generateBankAccountNumber());
		bankAccount.setUser(user);
		bankAccountRepo.save(bankAccount);
		
		Map<String,Object> responseMap = new HashMap<>();
		responseMap.put("name", user.getName());
		responseMap.put("username", user.getUsername());
		responseMap.put("password", user.getPassword());
		return responseMap;
	}
	
	public Map<String, Object> deposit(AppUserDetails currentUser, DepositRequest request){
		User user = userRepository.findById(currentUser.getId()).orElseThrow(()-> new CustomException("user not found", 10));
		BankAccount ba = bankAccountRepo.findByAccountNumberAndUserId(Long.parseLong(request.getAccounNumber()), currentUser.getId());
		if(ba == null) {
			throw new CustomException("no bank account", 10);
		}
		ba.setAmount(ba.getAmount() + request.getAmount());
		bankAccountRepo.save(ba);
		logHistory(ActivityEnum.DEPOSIT, request.getAmount(), ba, user, null, null);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("bank account", ba);
		return responseMap;
	}
	
	public Map<String, Object> withdrew(AppUserDetails currentUser, DepositRequest request){
		User user = userRepository.findById(currentUser.getId()).orElseThrow(()-> new CustomException("user not found", 10));
		BankAccount ba = bankAccountRepo.findByAccountNumberAndUserId(Long.parseLong(request.getAccounNumber()), currentUser.getId());
		if(ba == null) {
			throw new CustomException("no bank account", 10);
		}
		ba.setAmount(ba.getAmount() - request.getAmount());
		bankAccountRepo.save(ba);
		logHistory(ActivityEnum.WITHDREW, request.getAmount(), ba, user, null, null);
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("bank account", ba);
		return responseMap;
	}
	public Map<String, Object> transfer(AppUserDetails currentUser, DepositRequest request){
		User user = userRepository.findById(currentUser.getId()).orElseThrow(()-> new CustomException("user not found", 10));
		BankAccount ba = bankAccountRepo.findByAccountNumberAndUserId(Long.parseLong(request.getAccounNumber()), currentUser.getId());
		if(ba == null) {
			throw new CustomException("no bank account", 10);
		}
		BankAccount rba = bankAccountRepo.findByAccountNumber(Long.parseLong(request.getRecieverAccountNumber()));
		if(rba == null) {
			throw new CustomException("no reciever bank account", 10);
		}
		List<BankAccount> baList = new ArrayList<>();
		Map<String, Object> responseMap = new HashMap<>();
		if(ba.getAmount() > request.getAmount()) {
			ba.setAmount(ba.getAmount() - request.getAmount());
			rba.setAmount(request.getAmount());
			baList.add(ba);
			baList.add(rba);
			bankAccountRepo.saveAll(baList);
			responseMap.put("reciever bank account", rba);
			responseMap.put("bank account", ba);
			logHistory(ActivityEnum.TRANSFER, request.getAmount(), ba, user, rba.getAccountNumber(),null);
			logHistory(ActivityEnum.TRANSFER, request.getAmount(), rba, user, null, ba.getAccountNumber());
		}else {
			responseMap.put("message", "bank account insufficient balance");
		}
		return responseMap;
	}
	
	public BankAccount viewBankAccount(AppUserDetails currentUser, String accountNumber) {
		User user = userRepository.findById(currentUser.getId()).orElseThrow(()-> new CustomException("user not found", 10));
		BankAccount ba = bankAccountRepo.findByAccountNumberAndUserId(Long.parseLong(accountNumber), user.getId());
		if(ba == null) {
			throw new CustomException("no bank account", 10);
		}
		return ba;
	}
	
	private void logHistory(ActivityEnum act, Double amount, BankAccount ba, User user, Long recieverAccountNumber, Long benefactorAccountNumber) {
		History history =  new History();
		history.setActivity(act);
		history.setAmount(amount);
		history.setBankAccount(ba);
		history.setUser(user);
		if(recieverAccountNumber != null)
		{
			history.setRecieverAccountNumber(recieverAccountNumber);
		}
		if(benefactorAccountNumber != null)
		{
			history.setBenefactorAccountNumber(benefactorAccountNumber);
		}
		historyRepository.save(history);
	}
}
