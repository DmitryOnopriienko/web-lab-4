package com.mvc.weblab4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @GetMapping
  public String index() {
    return "index";
  }

  @GetMapping("/hi")
  public String sayHi(Model model,
                      @RequestParam(value = "first-name", required = false) String firstName,
                      @RequestParam(value = "last-name", required = false) String lastName) {
    model.addAttribute("firstName", firstName);
    model.addAttribute("lastName", lastName);
    return "hi";
  }
}
