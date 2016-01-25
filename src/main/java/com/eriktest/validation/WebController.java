package com.eriktest.validation;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm(Model model) {
		model.addAttribute("personForm", new BoardPost());
		return "form";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String checkPersonInfo(@Valid @ModelAttribute BoardPost boardPost, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "form";
		} else {
			ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
			PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
			postDAO.insert(boardPost);
			model.addAttribute("personForm", boardPost);
			((AbstractApplicationContext) context).close();
			return "results";
		}

	}
}