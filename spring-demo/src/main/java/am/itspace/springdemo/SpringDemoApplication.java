package am.itspace.springdemo;

import am.itspace.springdemo.model.Book;
import am.itspace.springdemo.model.User;
import am.itspace.springdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringDemoApplication {

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }


    //    @Override
    //    public void run(String... args) throws Exception {
    //        for (int i = 0; i < 20; i++) {
    //            bookRepository.save(Book.builder()
    //                    .title("title" + i)
    //                    .description("desc" + i)
    //                    .user(User.builder()
    //                            .id(11)
    //                            .build())
    //                    .build());
    //
    //        }
    //    }

//    @Override
//    public void run(String... args) throws Exception {
////        String name = null;
////        char c = name.charAt(0);
////        System.out.println(c);
//        Optional<String> name = Optional.ofNullable(null);
////        if (name.isPresent()){
////            String s = name.get();
////            char c = s.charAt(0);
////        }
//        String strName = name.orElseThrow(RuntimeException::new);
//        System.out.printf(strName);
//    }
}
