package org.codenova.start.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.codenova.start.entity.Book;
import org.codenova.start.entity.Movie;
import org.codenova.start.repository.BookRepository;
import org.codenova.start.repository.MovieRepository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class MybatisController {

    private MovieRepository movieRepository;
    private BookRepository bookRepository;

    @GetMapping("/mybatis/movie")
    public String findAll(Model model){


        List<Movie> movies = movieRepository.findAll();

        model.addAttribute("movies",movies);

        return "mybatis/movie";
    }
    @GetMapping("/mybatis/book")
    public String mybatisBookHandle(Model model){
        model.addAttribute("book",bookRepository.findAll());
        return "mybatis/book";
    }
    @GetMapping("mybatis/book-create")
    public String mybatisbookCreatehandle(Model model,@ModelAttribute Book book){
        new Book();

        int r = bookRepository.crate(book);


        return "redirect:mybatis/book";
    }
}
