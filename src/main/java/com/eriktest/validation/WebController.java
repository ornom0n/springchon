package com.eriktest.validation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		
		PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
		List<BoardPost> posts = postDAO.getPosts();
		model.addAttribute("posts", posts);
		((AbstractApplicationContext) context).close();
		return "form";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String checkPersonInfo(@Valid @ModelAttribute BoardPost boardPost, BindingResult bindingResult,
			Model model) {
		model.addAttribute("personForm", boardPost);
		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		
		if (bindingResult.hasErrors()) {
			((AbstractApplicationContext) context).close();
			return "form";
		} 
		else {
			PostDAO postDAO = (PostDAO) context.getBean("PostDAO");
			postDAO.insert(boardPost);
			
			((AbstractApplicationContext) context).close();
			return "results";
		}

	}

}