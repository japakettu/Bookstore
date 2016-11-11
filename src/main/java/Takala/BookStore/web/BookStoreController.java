package Takala.BookStore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import Takala.BookStore.Book.Book;
import Takala.BookStore.Book.BookRepositort;
import Takala.BookStore.Book.CategoryRepository;

@Controller
public class BookStoreController {
		
	@Autowired
	private BookRepositort repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
		// Show all books
		@RequestMapping(value="/booklist")
		public String bookList(Model model) {
			model.addAttribute("books", repository.findAll());
			return "booklist";
		}
		
		// RESTful service to get all books
	    @RequestMapping(value="/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> studentListRest() {	
	        return (List<Book>) repository.findAll();
	    }    

		// RESTful service to get book by id
	    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	    public @ResponseBody Book findStudentRest(@PathVariable("id") Long bookId) {	
	    	return repository.findOne(bookId);
	    }       
		
		// add new student
		@RequestMapping(value ="/add")
		public String addBook(Model model){
			model.addAttribute("book", new Book());
			model.addAttribute("categories", crepository.findAll());
			return "addbook";
		}
		
		//Save new book
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Book book) {
			repository.save(book);
			return "redirect:booklist";
		}

		//Delete student
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long bookId, Model model) {
			repository.delete(bookId);
			return "redirect:../booklist";
		}
}
