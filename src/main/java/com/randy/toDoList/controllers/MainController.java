package com.randy.toDoList.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.randy.toDoList.models.Ideas;
import com.randy.toDoList.models.User;
import com.randy.toDoList.models.UserLikedIdeas;
import com.randy.toDoList.services.UserService;
import com.randy.toDoList.services.validation.UserValidator;

@Controller
public class MainController {
	
	private final UserService userService;
	private final UserValidator userValidator;
    
    public MainController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

	
	

	@GetMapping("/")
	public String home(@ModelAttribute("user") User user) {
		
		return "index.jsp";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session ) {
		System.out.println("*********************");
		System.out.println(user.getEmail());
		System.out.println("*********************");
//		System.out.println(this.userService.findByEmail(user.getEmail()).toString());
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		}
		//TODO later after login reg works, prevent dupe emails
		
		//create a user with this information
		User u = this.userService.registerUser(user);
		//get the user that just got created's id and put it in session
		session.setAttribute("userid", u.getId());
		return "redirect:/tasks";
	}
	

	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirectAttributes ) {
		
		
		Boolean isLegit = this.userService.authenticateUser(email, password);
		
		if(isLegit) {
			//if the email password combo is correct, log them in using session and redirecting them to dashboard
			
			//get the user with that email
			User user = this.userService.findByEmail(email);
			//put that users id in session
			session.setAttribute("userid", user.getId());
			return "redirect:/tasks";
		}
		//if login is not successful, flash them a message
		redirectAttributes.addFlashAttribute("error", "Invalid login attempt");
		return "redirect:/";
	}
	@GetMapping("/tasks")
	public String home(HttpSession session, Model model) {
		Long id = (Long)session.getAttribute("userid");
		User loggedinuser = this.userService.findUserById(id);
		
		model.addAttribute("loggedinuser", loggedinuser);
		model.addAttribute("allideas", this.userService.findAllIdeas());
		
		
		return "tasks.jsp";
	}
	
	@GetMapping("/tasks/new")
	public String newIdea( @ModelAttribute("ideas") Ideas ideas, Model model) {
		
		return "newTask.jsp";
	}
	
	@PostMapping("/tasks/create")
	public String createIdea(@Valid @ModelAttribute("ideas") Ideas ideas, BindingResult result, Model model, HttpSession session) {
		
		if(result.hasErrors()) {

			return "newTask.jsp";
		}
		Long id = (Long)session.getAttribute("userid");
		User loggedinuser = this.userService.findUserById(id);
		ideas.setCreator(loggedinuser);
		
		this.userService.createIdeas(ideas);
		
		
		return "redirect:/tasks";
	}
	
	@GetMapping("/tasks/{id}")
	public String showIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
		model.addAttribute("thisidea", this.userService.thisIdea(id));
		model.addAttribute("allideas", this.userService.findAllIdeas());
		return "showTask.jsp";
	}
	
	@GetMapping("/tasks/{id}/edit")
	public String editIdea(@PathVariable("id")Long id, Model model, HttpSession session, Ideas ideas) {
		Long ids = (Long)session.getAttribute("userid");
		User loggedinuser = this.userService.findUserById(ids);

		Ideas x = this.userService.findAIdea(id);
		if(! x.getCreator().equals(loggedinuser)) {
			return"redirect:/tasks";
		}
		
		model.addAttribute("thisidea", this.userService.thisIdea(id));
		return "editTask.jsp";
	}
	
	@PostMapping("/tasks/update/{id}")
	public String updateIdea(@PathVariable("id") Long id, @Valid @ModelAttribute("thisidea") Ideas ideas, BindingResult result,Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "editTask.jsp";
		}
		
		Ideas x = this.userService.findAIdea(id);
		ideas.setCreator(x.getCreator());
		ideas.setLikedUsers(x.getLikedUsers());
		this.userService.updateIdea(ideas);
		return "redirect:/tasks";
	}
	
	@GetMapping("/tasks/like/{id}")
	public String likeIdea(@PathVariable("id") Long id, HttpSession session) {
		
		Ideas x = this.userService.findAIdea(id);
		Long loggedinuserid = (Long)session.getAttribute("userid");
		User loggedinuser = this.userService.findUserById(loggedinuserid);
		if(x.getLikedUsers().contains(loggedinuser)) {
			
			return"redirect:/tasks";
		}
		UserLikedIdeas ulIdeas = new UserLikedIdeas(loggedinuser, x);
		this.userService.createAssociation(ulIdeas);
		return"redirect:/tasks";
	}
	
	@GetMapping("/tasks/unlike/{id}")
	public String unlikeIdea(@PathVariable("id") Long id, HttpSession session) {
		Ideas x = this.userService.findAIdea(id);
		Long loggedinuserid = (Long)session.getAttribute("userid");
		User loggedinuser = this.userService.findUserById(loggedinuserid);
		if(!x.getLikedUsers().contains(loggedinuser)) {
			System.out.println("Already Logged in");
			return"redirect:/tasks";
		}
		loggedinuser.getIdeasBelongTo().remove(x);
		this.userService.unlike(loggedinuser);
		return"redirect:/tasks";
	}
	@GetMapping("/tasks/{id}/delete")
	public String deleteIdea(@PathVariable("id")Long id, HttpSession session) {
		Long ids = (Long)session.getAttribute("userid");
		User loggedinuser = this.userService.findUserById(ids);

		Ideas x = this.userService.findAIdea(id);
		if(! x.getCreator().equals(loggedinuser)) {
			return"redirect:/tasks";
		}
		this.userService.deleteIdea(x);
		return "redirect:/tasks";
	}
	@GetMapping("/user/{id}")
	public String showUser(@PathVariable("id")Long id, Model model) {
		model.addAttribute("thisuser", this.userService.findUserById(id));

		model.addAttribute("allideas", this.userService.findAllIdeas());
		User userToShow = this.userService.findUserById(id);
		
		model.addAttribute("userToShow", userToShow);
		System.out.println();
		return "showUser.jsp";
	}

}