package guru.springframework.spring5webapp.boostrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.respositories.AuthorRepository;
import guru.springframework.spring5webapp.respositories.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// create a class that can add some data into the database to work with
@Component
public class DevBoostrap implements ApplicationListener<ContextRefreshedEvent>{

    // These fields are extended with CRUD functionality
    private AuthorRepository authorRepository;
    private BookRespository bookRespository;

    // Auto injection with constructor


    @Autowired
    public DevBoostrap(AuthorRepository authorRepository, BookRespository bookRespository) {
        this.authorRepository = authorRepository;
        this.bookRespository = bookRespository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        // create author and book to be saved into the database
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234" ,"Harper Collins");
        // add the books and authors to the list of both class
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        // save the author and book into the database
        authorRepository.save(eric);
        bookRespository.save(ddd);

        // create another author and book
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", "Worx");
        rod.getBooks().add(noEJB);

        // save the author and book into the database
        authorRepository.save(rod);
        bookRespository.save(noEJB);

    }
}
