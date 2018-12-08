package com.packt.ch06.controllers;

import com.packt.ch06.beans.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddBookController {

    @Autowired
    Validator validator;

    @ModelAttribute("priceList")
    public List<Integer> addPrices() {

        List<Integer> prices=new ArrayList<Integer>();
        prices.add(300);
        prices.add(350);
        prices.add(400);
        prices.add(500);
        prices.add(550);
        prices.add(600);

        return prices;
    }

    @RequestMapping("/showBookForm.htm")
    public ModelAndView showBookForm(ModelMap map)
            throws Exception {
        Book book=new Book();
        map.addAttribute(book);
        return new ModelAndView("bookForm");
    }

    @RequestMapping("/addBook.htm")
    public ModelAndView addBook(@Valid @ModelAttribute("book") Book book,
                                BindingResult bindingResult)
            throws Exception {

        //validator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("bookForm");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("display");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        modelAndView.addObject("book_list", bookList);
        modelAndView.addObject("auth_name", book.getAuthor());

        return modelAndView;
    }

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder)
    {
        webDataBinder.setValidator(validator);
    }
}
