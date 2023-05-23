package com.mvc.weblab4.controller;

import com.mvc.weblab4.dao.UsersDao;
import com.mvc.weblab4.dto.LoginDataDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  private final UsersDao usersDao;

  @Autowired
  public MainController(UsersDao usersDao) {
    this.usersDao = usersDao;
  }

  @GetMapping
  public String index() {
    return "index";
  }

  @GetMapping("/hi")
  public String sayHi(Model model,
                      @RequestParam(value = "first-name", defaultValue = "") String firstName,
                      @RequestParam(value = "last-name", defaultValue = "") String lastName) {
    String name = firstName + " " + lastName;
    model.addAttribute("name", name.trim());
    return "hi";
  }

  @GetMapping("/login")
  public String login() {
    return "login-page";
  }

  @PostMapping("/login")
  public String login(Model model,
                      HttpSession session,
                      LoginDataDto loginData) {
    String email = loginData.getEmail();
    String password = loginData.getPassword();

    if (usersDao.isExist(email, password)) {
      session.setAttribute("IS_LOGGED_IN", true);
      return "redirect:/users";
    }
    model.addAttribute("loginFailed", true);
    return "login-page";
  }

  @PostMapping("/logout")
  public String logout(HttpSession session) {
    session.setAttribute("IS_LOGGED_IN", false);
    return "redirect:/login";
  }

  @GetMapping("/users")
  public String users(Model model, HttpSession session) {
    if (session.getAttribute("IS_LOGGED_IN") == null
            || session.getAttribute("IS_LOGGED_IN").equals(false)) {
      model.addAttribute("notAuthorized", true);
      return "login-page";
    }
    model.addAttribute("usersFromServer", usersDao.findAll());
    return "user-list";
  }
}
