package com.projectx.mvc.controller.quickregister;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.projectx.mvc.domain.quickregister.Person;


@Controller
public class WebController {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/results").setViewName("results");
//    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showForm(Person person) {
        return "form";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String checkPersonInfo(@Valid Person person1, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/results";
    }

}