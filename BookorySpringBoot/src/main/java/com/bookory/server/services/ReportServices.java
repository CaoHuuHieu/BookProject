package com.bookory.server.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.models.response.AdminReport;
import com.bookory.server.repositories.BookRepository;
import com.bookory.server.repositories.OrderRepository;
import com.bookory.server.repositories.StoreRepository;
import com.bookory.server.repositories.UserRepository;
@Service
public class ReportServices {
	@Autowired
	UserRepository userRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	OrderRepository orderRepository;
	public AdminReport createReport(Date startDate, Date endDate) {
		AdminReport adminReport = new AdminReport();
		adminReport.setSumAccount(countUser());
		adminReport.setSumStore(countStore());
		adminReport.setSumOrder(sumOrder());
		adminReport.setSumRevunue(getRevenue());
		
		adminReport.setNewAccount(countUserBetweenDate(startDate, endDate));
		adminReport.setNewStore(countStoreBetweenDate(startDate, endDate));
		adminReport.setSumNewRevunue(getRevenueBetweenDate(startDate, endDate));
		adminReport.setSumNewOrder(sumNewOrder(startDate, endDate));
		return adminReport;
	}
	
	public long countUser() {
		return userRepository.countByRoleId();
	}
	public long countUserBetweenDate(Date startDate, Date endDate) {
		return userRepository.countByCreateDateBetween(startDate, endDate);
	}
	public long countStore() {
		return storeRepository.count();
	}
	public long countStoreBetweenDate(Date startDate, Date endDate) {
		return storeRepository.countByCreateDateBetween(startDate, endDate);
	}
	public long sumOrder() {
		return orderRepository.count();
	}
	public long sumNewOrder(Date startDate, Date endDate) {
		return orderRepository.countByCreateDateBetween(startDate, endDate);
	}
	public long getRevenue() {
		return orderRepository.sumTotalMoney();
	}
	public long getRevenueBetweenDate(Date startDate, Date endDate) {
		return orderRepository.sumTotalMoneyBetweenDates(startDate, endDate);
	}
	public long countBookStore() {
		return bookRepository.countByStatus(1);
	}
	public long sumQuantitySold() {
		return bookRepository.sumQuantitySold();
	}
	
}
