package am.itspace.springdemo.controller;


import am.itspace.springdemo.model.Book;
import am.itspace.springdemo.model.User;
import am.itspace.springdemo.repository.BookRepository;
import am.itspace.springdemo.repository.UserRepository;
import am.itspace.springdemo.service.BookService;
import am.itspace.springdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/book")
    public String bookPage(ModelMap modelMap,
                           @RequestParam(name = "msg", required = false) String msg,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "20") int size,
                           @RequestParam(value = "orderBy", defaultValue = "title") String orderBy,
                           @RequestParam(value = "order", defaultValue = "ASC") String order) {

        Sort sort = order.equals("ASC") ? Sort.by(Sort.Order.asc(orderBy)) : Sort.by(Sort.Order.desc(orderBy));

        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        List<User> users = userRepository.findAll();
        Page<Book> books = bookRepository.findAll(pageRequest);
        int totalPages = books.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("books", books);

        modelMap.addAttribute("msg", msg);
        log.info("inch grenq!");
        return "book";
    }

    @PostMapping("/book/save")
    public String add(@ModelAttribute Book book) {
        String msg = book.getId() > 0 ? "Book was updated" : "Book was added";
//        String ctrlName = book.getId() > 0 ? "/book" : "/";
        bookService.save(book);
        return "redirect:/?msg=" + msg;
    }

    @GetMapping("/book/editPage")
    public String edit(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<Book> one = bookService.findById(id);
        if (!one.isPresent()) {
            return "redirect:/";
        }
        modelMap.addAttribute("users", userService.findAll());
        modelMap.addAttribute("book", one.get());
        return "editBook";
    }

    @GetMapping("/book/delete")
    public String delete(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return "redirect:/";
    }


}
