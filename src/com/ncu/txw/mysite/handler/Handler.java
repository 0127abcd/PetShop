package com.ncu.txw.mysite.handler;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.ncu.txw.mysite.entities.Comment;
import com.ncu.txw.mysite.entities.Pet;
import com.ncu.txw.mysite.entities.ShopCar;
import com.ncu.txw.mysite.entities.User;
import com.ncu.txw.mysite.services.CommentService;
import com.ncu.txw.mysite.services.PetService;
import com.ncu.txw.mysite.services.ShopCarService;
import com.ncu.txw.mysite.services.UserService;

@Controller
public class Handler{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private ShopCarService shopCarService;
	
	@Autowired
	private CommentService commentService;
	
	private ShopCar shopCar = new ShopCar();
	
	//评论
	@SuppressWarnings("rawtypes")
	@RequestMapping("/doComment")
	public String doComment(@RequestParam("id") Integer id, @RequestParam("userName") String userName,
			@RequestParam("content") String content, 
			@RequestParam(name="anonymous", defaultValue="0", required=false) Integer anonymous){
		Iterator itCom = petService.getById(id).getComments().iterator();
		while (itCom.hasNext()) {
			Comment c1 = (Comment)itCom.next();
			User user = c1.getUser();
			if(user.getUserName().equals(userName)){
				content = c1.getContent() + " <br> 追加：" + content;
				c1.setContent(content);
				c1.setDate(new Date());
				commentService.save(c1);
				return "redirect:/petInfo?userName=" + userName + "&id=" + id;
			}
		}
		
		Comment comment = new Comment();
		comment.setDate(new Date());
		comment.setAnonymous(anonymous);
		comment.setContent(content);
		comment.setUser(userService.getByUserName(userName));
		comment.setPet(petService.getById(id));
		commentService.save(comment);
		petService.getById(id).getComments().add(commentService.getByUserId(
					userService.getByUserName(userName).getId(), id));
		return "redirect:/petInfo?userName=" + userName + "&id=" + id;
	}
	//付款完成
	@RequestMapping("/doPay")
	public String doPay(@RequestParam("id") Integer id, @RequestParam("userName") String userName,
			HttpSession session){
		ShopCar shopCar1 = shopCarService.getById(id);
		shopCar1.setStatus(1);
		shopCarService.save(shopCar1);
		shopCar = new ShopCar();
		session.setAttribute("shopCar", shopCar.getPets());
		session.setAttribute("sum", 0);
		return "redirect:/login?userName=" + userName;
	}
	
	//付款
	@RequestMapping("/pay")
	public String pay(@RequestParam("id") Integer id, @RequestParam("userName") String userName,
			@RequestParam("sum") Integer sum, Map<String, Object> map){
		map.put("car_id", id);
		map.put("userName", userName);
		map.put("sum", sum);
		return "pay";
	}
	
	//从购物车中移除
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping("/petDel")
	public String petDel(@RequestParam("id") Integer id, @RequestParam("userName") String userName, 
			@RequestParam(value="petCategory", required=false, defaultValue="cat") String petCategory,
			HttpSession session){
		int index = 0;
		Pet pet = petService.getById(id);
		Pet pet2 = new Pet();
		Iterator it = shopCar.getPets().iterator();
		while (it.hasNext()) {
			Pet p = (Pet)it.next();
			if(p.getId()==id)
				pet2 = shopCar.getPets().remove(index);
			index++;
		}
		
		shopCarService.save(shopCar);
		petService.save(pet);
		int sum = 0;
		for(int i = 0; i < shopCar.getPets().size(); i++){
			sum += shopCar.getPets().get(i).getPetPrice();
		}
		List<Pet> buyPets = shopCar.getPets();
		session.setAttribute("sum", sum);
		session.setAttribute("shopCar", buyPets);
		return "redirect:/login?userName=" + userName +"&petCategory=" + petCategory;
	}
	
	//购买
	@RequestMapping("/petBuy")
	public String petBuy(@RequestParam("id") Integer id, @RequestParam("userName") String userName, 
			@RequestParam(value="petCategory", required=false, defaultValue="cat") String petCategory,
			HttpSession session){
		Pet pet = petService.getById(id);
		boolean hasPet = false;
		shopCar.setUser(userService.getByUserName(userName));
		Iterator<Pet> it = shopCar.getPets().iterator();
		while (it.hasNext()) {
			Pet p = it.next();
			if(p.getId()==id)
				hasPet = true;
		}
		if(!hasPet){
			shopCar.getPets().add(pet);
			pet.getShopcars().add(shopCar);
			shopCarService.save(shopCar);
			petService.save(pet);
		}
		int sum = 0;
		for(int i = 0; i < shopCar.getPets().size(); i++){
			sum += shopCar.getPets().get(i).getPetPrice();
		}
		List<Pet> buyPets = shopCar.getPets();
		session.setAttribute("sum", sum);
		session.setAttribute("shopCar", buyPets);
		session.setAttribute("car_id", shopCar.getId());
		return "redirect:/login?userName=" + userName +"&petCategory=" + petCategory;
	}
	
	//显示详细信息
	@SuppressWarnings("rawtypes")
	@RequestMapping("/petInfo")
	public String petInfo(@RequestParam("id") Integer id, @RequestParam("userName") String userName,
			Map<String, Object> map){
		Pet pet = petService.getById(id);
		List<Comment> comments = commentService.getAll(id);
		Integer canComment = 0;
		Integer userId = userService.getByUserName(userName).getId();
		List<ShopCar> cars = pet.getShopcars();
		Iterator it = cars.iterator();
		while (it.hasNext()) {
			ShopCar car1 = (ShopCar)it.next();
			if(car1.getComplete() == 1){
				User user = car1.getUser();
				if (user.getId() == userId) {
					canComment = 1;
				}
			}
		}
		map.put("canComment", canComment);
		map.put("comments", comments);
		map.put("pet", pet);
		return "petInfo";
	}
	
	//注册
	@RequestMapping("/doRegister")
	public String doRegister(@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel){
		User user = new User(null, userName, password, email, address, tel);
		userService.save(user);
		return "index";
	}
	
	//校验用户名是否可用
	@ResponseBody
	@RequestMapping(value="/ajaxValidateUserName", method=RequestMethod.POST)
	public String validateLastName(@RequestParam(value="userName", required=true) String userName){
		User user = userService.getByUserName(userName);
		if(user == null){
			return "0";
		}else{
			return "1";
		}
	}
	
	//返回注册页面
	@RequestMapping("/register")
	public String Register(){
		return "register";
	}
	
	@RequestMapping("/logOff")
	public String LogOff(HttpSession session){
		shopCar = new ShopCar();
		session.setAttribute("shopCar", 0);
		session.setAttribute("sum", 0);
		return "index";
	}
	
	@RequestMapping("/modifyInfo")
	public String modify(@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("tel") String tel,
			@RequestParam("address") String address){
		User user = userService.getByUserName(userName);
		user.setAddress(address);
		user.setEmail(email);
		user.setPassword(password);
		user.setTel(tel);
		userService.save(user);
		return "index";
	}
	
	@RequestMapping("/doForgetPassword")
	public String doForgetPassword(@RequestParam("userName") String userName,
			@RequestParam("email") String email,
			@RequestParam("tel") String tel,
			Map<String, Object> map){
		
		User user = userService.getByUserName(userName);
		if (!user.getEmail().equals(email) || !user.getTel().equals(tel))
			return "index";
		map.put("user", user);
		return "modify";
	}
	
	@RequestMapping("/forgetPassword")
	public String forgetPassword(){
		return "forgetPassword";
	}
	
	//登录并显示主界面
	@RequestMapping("login")
	public String Login(@RequestParam(value="pageNo", required=false, defaultValue="1") String pageNoStr,
			@RequestParam(value="petCategory", required=false, defaultValue="cat") String petCategory,
			@RequestParam(value="userName", required=false) String userName,
			@RequestParam(value="password", required=false) String password, 
			Map<String, Object> map,
			HttpSession session){
		if(session.getAttribute("user") == null || 
			(!((User)session.getAttribute("user")).getUserName().equals(userName) && 
					!((User)session.getAttribute("user")).getUserName().equals(""))){
			
			User user = userService.getByUserName(userName);
			if(user == null || !user.getPassword().equals(password)){
				return "index";
			}
			session.setAttribute("user", user);
		}

		int pageNo=1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
			if(pageNo < 1){
				pageNo = 1;
			}
		} catch (Exception e) {}
		
		List<Pet> pets = petService.getByPetCategory(petCategory, pageNo, 6);
		int petSize = petService.getByPetCategory(petCategory).size();
		int pageCount = petSize % 6 != 0 ? petSize / 6 + 1 : petSize / 6;
		session.setAttribute("petCategory", petCategory);
		map.put("pets", pets);
		map.put("pageSize", petSize);
		map.put("pageNo", pageNo);
		map.put("pageCount", pageCount);
		return "list";
	}
	

	
}
