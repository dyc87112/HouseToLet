package com.nextplus.housetolet.controller;

import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextplus.housetolet.repository.PaymentRepository;
import com.nextplus.housetolet.repository.RentalRepository;
import com.nextplus.housetolet.repository.RoomRepository;
import com.nextplus.housetolet.repository.UserRepository;
import com.nextplus.housetolet.service.PaymentService;

public class BaseController {
	
	protected Log log = LogFactory.getLog(getClass());
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String PAGE_PATH = "pagePath";

	public static String NEED_LOGIN = "redirect:/login";
	public static String LAYOUT_PAGE = "layouts/layout";

	@Autowired
	protected UserRepository userRepository;
	@Autowired
	protected RoomRepository roomRepository;
	@Autowired
	protected RentalRepository rentalRepository;
	@Autowired
	protected PaymentRepository paymentRepository;
	@Autowired
	protected PaymentService paymentService;

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

}
