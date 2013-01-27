package com.nextplus.housetolet.controller.manage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nextplus.housetolet.controller.BaseController;
import com.nextplus.housetolet.domain.Rental;
import com.nextplus.housetolet.domain.Room;
import com.nextplus.housetolet.domain.User;

/**
 * 租赁管理
 * 
 * @author didi
 *
 * 创建时间：2013-1-8
 *
 */
@Controller
@RequestMapping("/rental")
public class RentalController extends BaseController {
	
	@InitBinder  
	protected void initBinder(HttpServletRequest request,  ServletRequestDataBinder binder) throws Exception {   
	      DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  
	      CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);  
	      binder.registerCustomEditor(Date.class, dateEditor);  
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET) 
	public String roomListAll(
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		user = userRepository.findOne(user.getId());
		List<Rental> rentalList = rentalRepository.findAllByUser(user);
		modelMap.addAttribute("rentalList", rentalList);
		modelMap.addAttribute("rentalType", "all");
		modelMap.addAttribute(PAGE_PATH, "content/rental/list");
		return  LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/listHistory", method = RequestMethod.GET) 
	public String roomListHistory(
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		user = userRepository.findOne(user.getId());
		List<Rental> rentalList = rentalRepository.findHistoryRentalByUser(user);
		modelMap.addAttribute("rentalList", rentalList);
		modelMap.addAttribute("rentalType", "history");
		modelMap.addAttribute(PAGE_PATH, "content/rental/list");
		return  LAYOUT_PAGE;
	}
	
	@RequestMapping(value = {"/listCurrent", "/list"}, method = RequestMethod.GET) 
	public String roomListCurrent(
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		user = userRepository.findOne(user.getId());
		List<Rental> rentalList = rentalRepository.findCurrentRentalByUser(user);
		modelMap.addAttribute("rentalList", rentalList);
		modelMap.addAttribute("rentalType", "current");
		modelMap.addAttribute(PAGE_PATH, "content/rental/list");
		return  LAYOUT_PAGE;
	}
	
	@RequestMapping(value = {"/listRentalNet"}, method = RequestMethod.GET) 
	public String roomListRentalNet(
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		user = userRepository.findOne(user.getId());
		List<Rental> rentalList = rentalRepository.findCurrentRentalByUser(user);
		modelMap.addAttribute("rentalList", rentalList);
		modelMap.addAttribute(PAGE_PATH, "content/rental/listNet");
		return  LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/setNet/{rentalId}", method = RequestMethod.GET) 
	public String setNetOn(
			@PathVariable Long rentalId,
			@RequestParam Boolean hasNet,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		Rental rental = rentalRepository.findOne(rentalId);
		rental.setHasNet(hasNet);
		rentalRepository.save(rental);
		return  "redirect:/rental/listRentalNet";
	}
	
	@Transactional
	@RequestMapping(value = "/del/{rentalId}", method = RequestMethod.GET) 
	public String rentalDel(
			@PathVariable Long rentalId,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		Rental rental = rentalRepository.findOne(rentalId);
		if(paymentRepository.findLastByUserAndRental(user, rental).size() == 0) {
			Room room = rental.getRoom();
			room.setIsEmpty(true);
			roomRepository.save(room);
			rentalRepository.delete(rentalId);
//			modelMap.addAttribute("error", rental.getRoom().getName() + "房间存在账单，不能删除！");
		} else {
			// TODO 租赁下有账单的时候，不允许删除
			
		}
		return  "redirect:/rental/list";
	}
	
	@RequestMapping(value = "/view/{rentalId}", method = RequestMethod.GET) 
	public String rentalView(
			@PathVariable Long rentalId,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		Rental rental = rentalRepository.findOne(rentalId);
		modelMap.addAttribute("rental", rental);
		modelMap.addAttribute(PAGE_PATH, "content/rental/view");
		return  LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET) 
	public String roomCreateForm(
			@RequestParam(value="roomId", required=false) Long roomId,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		List<Room> roomList = new ArrayList<Room>();
		if(roomId == null) 
			roomList = roomRepository.findEmptyRoomByUser(user);
		else {
			Room room = roomRepository.findOne(roomId); 
			roomList.add(room);
		}
		modelMap.addAttribute("rental", new Rental());
		modelMap.addAttribute("roomList", roomList);
		modelMap.addAttribute(PAGE_PATH, "content/rental/createForm");
		return LAYOUT_PAGE;
	}
	
	@Transactional
	@RequestMapping(value = "/create", method = RequestMethod.POST) 
	public String rentalCreateProccess(
			@Valid @ModelAttribute("rental") Rental rental, 
			Errors errors, 
			HttpSession session, 
			ModelMap modelMap) {
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null) {
			return NEED_LOGIN;
		}
		if(errors.hasErrors()) {
			modelMap.addAttribute(PAGE_PATH, "content/rental/createForm");
			return LAYOUT_PAGE;
		} 
		User user = userRepository.findOne(sessionUser.getId());
		Room room = roomRepository.findOne(rental.getRoom().getId());
		room.setIsEmpty(false);
		roomRepository.save(room);
		rental.setCreateDate(new Date());
		rental.setUser(user);
		rental.setRoom(room);
		rental.setIsValid(true);
		// 跳转到创建首张账单
		return "redirect:/payment/create?rentalId=" + rentalRepository.save(rental).getId() + "&isClose=false";
	}
	
	@RequestMapping(value = "/update/{rentalId}", method = RequestMethod.GET) 
	public String rentalUpdateForm(
			@PathVariable Long rentalId,
			HttpSession session, 
			ModelMap modelMap) {
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null) {
			return NEED_LOGIN;
		}
		modelMap.addAttribute("rental", rentalRepository.findOne(rentalId));
		modelMap.addAttribute(PAGE_PATH, "content/rental/updateForm");
		return LAYOUT_PAGE;
	}
	
	@Transactional
	@RequestMapping(value = "/update/{rentalId}", method = RequestMethod.POST) 
	public String rentalUpdateroccess(
			@PathVariable Long rentalId,
			@Valid @ModelAttribute("rental") Rental rental, 
			Errors errors, 
			HttpSession session, 
			ModelMap modelMap) {
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null) {
			return NEED_LOGIN;
		}
		if(errors.hasErrors()) {
			modelMap.addAttribute(PAGE_PATH, "content/rental/updateForm");
			return LAYOUT_PAGE;
		} 
		Rental newRental = rentalRepository.findOne(rentalId);
		newRental.setCustomerName(rental.getCustomerName());
		newRental.setCustomerId(rental.getCustomerId());
		newRental.setCustomerTel(rental.getCustomerTel());
		newRental.setDeposit(rental.getDeposit());
		newRental.setHasNet(rental.getHasNet());
		newRental.setOtherInfo(rental.getOtherInfo());
		newRental.setStartDate(rental.getStartDate());
		newRental.setBasePayment(rental.getBasePayment());
		rentalRepository.save(newRental);
		return "redirect:/rental/view/{rentalId}";
	}
	
}
