package com.kgisl.springproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee")
public class Employee2Controller {

    @RequestMapping("/helloworld")
    public String hello(ModelMap map) {
        String helloWorldMessage = "Hello world!";
        String welcomeMessage = "Welcome!";
        map.addAttribute("helloMessage", helloWorldMessage);
        map.addAttribute("welcomeMessage", welcomeMessage);
        return "hello"; // This will resolve to /WEB-INF/view/hello.jsp
    }
    @RequestMapping("/welcome")
public ModelAndView helloWorld() {
        String msg = "Hello from modelandview";
        return new ModelAndView("welcome", "message", msg);
    }
}
