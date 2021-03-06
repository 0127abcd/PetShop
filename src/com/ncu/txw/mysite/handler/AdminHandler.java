package com.ncu.txw.mysite.handler;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncu.txw.mysite.entities.Pet;
import com.ncu.txw.mysite.entities.ShopCar;
import com.ncu.txw.mysite.entities.User;
import com.ncu.txw.mysite.services.PetService;
import com.ncu.txw.mysite.services.ShopCarService;
import com.ncu.txw.mysite.services.UserService;

@Controller
public class AdminHandler {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShopCarService shopCarService;
	
	@Autowired
	private PetService petService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/doOrder")
	public String doOrder(@RequestParam("id") Integer car_id,
			@RequestParam("userName") String adminName){
		ShopCar shopCar = shopCarService.getById(car_id);
		shopCar.setComplete(1);
		List<Pet> pets = shopCar.getPets();
		Iterator it = pets.iterator();
		while (it.hasNext()) {
			Pet pet = (Pet)it.next();
			pet.setPetCount(pet.getPetCount()-1);
			petService.save(pet);
		}
		return "redirect:/adminLogin?userName=" + adminName;
	}
	
	//订单详细情况
	@SuppressWarnings("rawtypes")
	@RequestMapping("/orderInfo")
	public String orderInfo(@RequestParam("id") Integer car_id,
			@RequestParam("userName") String adminName,
			@RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
			Map<String, Object> map){
		ShopCar shopCar = shopCarService.getById(car_id);
		User user = shopCar.getUser();
		Integer sum = 0;
		
		int pageNo=1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			if(pageNo < 1){
				pageNo = 1;
			}
		} catch (Exception e) {}
		
		List<Pet> allPets = shopCar.getPets();
		int petSize = allPets.size();
		int pageCount = petSize / 5 + 1;
		
		List<Pet> pets = allPets.subList((pageNo-1)*5, pageNo*5 > petSize ? petSize : pageNo*5);
		
		Iterator it = allPets.iterator();
		while (it.hasNext()) {
			sum += ((Pet)it.next()).getPetPrice();
		}
		map.put("pageSize", petSize);
		map.put("pageNo", pageNo);
		map.put("pageCount", pageCount);
		
		map.put("user", user);
		map.put("sum", sum);
		map.put("pets", pets);
		map.put("carId", car_id);
		map.put("isComplete", shopCarService.getById(car_id).getComplete());
		return "orderInfo";
	}
	
	//登录并显示主界面
	@RequestMapping("/adminLogin")
	public String Login(@RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
			@RequestParam(value="userName", required=false) String userName,
			@RequestParam(value="password", required=false) String password, 
			Map<String, Object> map,
			HttpSession session){
		if(session.getAttribute("adminUser") == null || 
			(!((User)session.getAttribute("adminUser")).getUserName().equals(userName) && 
					!((User)session.getAttribute("adminUser")).getUserName().equals(""))){
			
			User adminUser = userService.getByUserName(userName);
			if(adminUser == null || !adminUser.getPassword().equals(password) || adminUser.getAdmin() != 1){
				return "adminLogin";
			}
			session.setAttribute("adminUser", adminUser);
		}

		int pageNo=1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			if(pageNo < 1){
				pageNo = 1;
			}
		} catch (Exception e) {}
		
		int pageSize = 5;
		int pageCount = shopCarService.findAllCar().size() / pageSize + 1;
		List<ShopCar> shopCars = shopCarService.getShopcars((pageNo-1)*5);
		session.setAttribute("orders", shopCars);
		map.put("pageSize", pageSize);
		map.put("pageNo", pageNo);
		map.put("pageCount", pageCount);
		return "order";
	}
	
}
