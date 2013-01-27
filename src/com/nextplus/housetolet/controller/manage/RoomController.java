package com.nextplus.housetolet.controller.manage;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nextplus.housetolet.controller.BaseController;
import com.nextplus.housetolet.domain.Room;
import com.nextplus.housetolet.domain.User;

/**
 * 房间管理
 * 
 * @author didi
 *
 * 创建时间：2013-1-8
 *
 */
@Controller
@RequestMapping("/room")
public class RoomController extends BaseController {
	
	@RequestMapping(value = "/list", method = RequestMethod.GET) 
	public String roomList(
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		user = userRepository.findOne(user.getId());
		List<Room> roomList = roomRepository.findByUser(user);
		modelMap.addAttribute("roomList", roomList);
		modelMap.addAttribute(PAGE_PATH, "content/room/list");
		return  LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/emptyList", method = RequestMethod.GET) 
	public String emptyRoomList(
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		user = userRepository.findOne(user.getId());
		List<Room> roomList = roomRepository.findEmptyRoomByUser(user);
		modelMap.addAttribute("roomList", roomList);
		modelMap.addAttribute(PAGE_PATH, "content/room/emptylist");
		return  LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/del/{roomId}", method = RequestMethod.GET) 
	public String roomDel(
			@PathVariable Long roomId,
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		roomRepository.delete(roomId);
		return  "redirect:/room/list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET) 
	public String roomCreateForm(
			HttpSession session, 
			ModelMap modelMap) {
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null) {
			return NEED_LOGIN;
		}
		modelMap.addAttribute("room", new Room());
		modelMap.addAttribute("title", "创建房间");
		modelMap.addAttribute("postPath", "./create");
		modelMap.addAttribute(PAGE_PATH, "content/room/form");
		return LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST) 
	public String roomCreateProccess(
			@Valid @ModelAttribute("room") Room room, 
			Errors errors, 
			HttpSession session, 
			ModelMap modelMap) {
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null) {
			return NEED_LOGIN;
		}
		if(errors.hasErrors()) {
			modelMap.addAttribute(PAGE_PATH, "content/room/form");
			return LAYOUT_PAGE;
		} 
		User user = userRepository.findOne(sessionUser.getId());
		room.setIsEmpty(true);
		room.setUser(user);
		roomRepository.save(room);
		return "redirect:/room/list";
	}
	
	@RequestMapping(value = "/update/{roomId}", method = RequestMethod.GET) 
	public String roomUpdateForm(
			@PathVariable Long roomId,
			HttpSession session, 
			ModelMap modelMap) {
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null) {
			return NEED_LOGIN;
		}
		modelMap.addAttribute("room", roomRepository.findOne(roomId));
		modelMap.addAttribute("title", "编辑房间信息");
		modelMap.addAttribute("postPath", "../update/" + roomId);
		modelMap.addAttribute(PAGE_PATH, "content/room/form");
		return LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/update/{roomId}", method = RequestMethod.POST) 
	public String roomUpdateProccess(
			@PathVariable Long roomId,
			@Valid @ModelAttribute("room") Room room, 
			Errors errors, 
			HttpSession session, 
			ModelMap modelMap) {
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null) {
			return NEED_LOGIN;
		}
		if(errors.hasErrors()) {
			modelMap.addAttribute(PAGE_PATH, "content/room/form");
			return LAYOUT_PAGE;
		} 
		Room r = roomRepository.findOne(roomId);
		r.setName(room.getName());
		r.setInfo(room.getInfo());
		roomRepository.save(r);
		return "redirect:/room/list";
	}
	
}
