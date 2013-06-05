package com.nextplus.housetolet.controller.manage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextplus.housetolet.controller.BaseController;
import com.nextplus.housetolet.domain.Payment;
import com.nextplus.housetolet.domain.Rental;
import com.nextplus.housetolet.domain.User;

/**
 * 账单管理
 * 
 * @author didi
 *
 * 创建时间：2013-1-8
 *
 */
@Controller
@RequestMapping("/payment")
public class PaymentController extends BaseController {
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,  ServletRequestDataBinder binder) throws Exception {   
	      DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
	      CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);  
	      binder.registerCustomEditor(Date.class, dateEditor);  
	}
	
	@RequestMapping(value = "/validRentalList", method = RequestMethod.GET)
	public String validRentalList(
			HttpSession session, 
			ModelMap modelMap) {
		// TODO 账单结算页面，根据下一次结算时间排序显示
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		List<Rental> rentalList = rentalRepository.findCurrentRentalByUserASCNextPayDate(user);
		modelMap.addAttribute("rentalList", rentalList);
		modelMap.addAttribute(PAGE_PATH, "content/payment/rentalList");
		return LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createPaymentForm(
			@RequestParam(value="rentalId", required=true) Long rentalId,
			@RequestParam(value="isClose", required=false) Boolean isClose,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		Rental rental = rentalRepository.findOne(rentalId);
		modelMap.addAttribute("rental", rental);
		// 处理账单表单，以及映射的页面
		List<Payment> paymentList = paymentRepository.findByUserAndRentalDESC(user, rental);
		if(paymentList.size() == 0) {
			// 首次开房账单，从租赁中获得初始值
			modelMap.addAttribute("payment", paymentService.preFirstPayment(user, rental));
			modelMap.addAttribute(PAGE_PATH, "content/payment/first_form");
		} else {
			if(isClose) {
				// 结账账单，从租赁和上月账单中获得初始值
				modelMap.addAttribute("payment", paymentService.preLastPayment(user, rental, paymentList.get(0)));
				modelMap.addAttribute(PAGE_PATH, "content/payment/last_form");
			} else {
				// 月结账单，从租赁和上月账单中获得初始值
				modelMap.addAttribute("payment", paymentService.preMonthPayment(user, rental, paymentList.get(0)));
				modelMap.addAttribute(PAGE_PATH, "content/payment/month_form");
			}
		}
		return LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createPaymentProcess(
			@RequestParam("rentalId") Long rentalId,
			@RequestParam("isFirst") Boolean isFirst,
			@RequestParam("isClose") Boolean isClose,
			@Valid @ModelAttribute("payment") Payment payment,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		Rental rental = rentalRepository.findOne(rentalId);
		Payment p = null;
		if(isFirst) {
			// 首次账单
			p = paymentService.saveFirstPayment(user, rental, payment);
			modelMap.addAttribute("payment", p);
		} else {
			if(isClose) {
				// 退房账单
				p = paymentService.saveLastPayment(user, rental, payment);
				modelMap.addAttribute("payment", p);
			} else {
				// 月结账单
				p = paymentService.saveMonthPayment(user, rental, payment);
				modelMap.addAttribute("payment", p);
			}
		}
		// 跳转到账单查看页面
		return "redirect:/payment/view/" + p.getId();
	}
	
	@RequestMapping(value = "/view/{paymentId}", method = RequestMethod.GET)
	public String paymentView(
			@PathVariable("paymentId") Long paymentId,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		Payment p = paymentRepository.findOne(paymentId);
		if(p.getUser().getId() == user.getId()) {
			modelMap.addAttribute("payment", p);
			modelMap.addAttribute(PAGE_PATH, "content/payment/print");
		} else {
			// TODO 不是该用户的账单数据
		}
		return LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/del/{paymentId}", method = RequestMethod.GET)
	public String deletePayment(
			@PathVariable("paymentId") Long paymentId,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		paymentService.deletePayment(paymentId);
		return "redirect:/payment/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String paymentList(
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		List<Payment> paymentList = paymentRepository.findByUserDateDesc(user);
		modelMap.addAttribute("paymentList", paymentList);
		modelMap.addAttribute(PAGE_PATH, "content/payment/list");
		return LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/getSum", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getPaymentSum(
			@RequestBody Payment payment,
			HttpSession session, ModelMap modelMap) {
		// TODO 计算账单结算页面的预合计
		return null;
	}
	
}
