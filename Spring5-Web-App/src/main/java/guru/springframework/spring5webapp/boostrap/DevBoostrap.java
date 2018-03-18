package guru.springframework.spring5webapp.boostrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.respositories.AuthorRepository;
import guru.springframework.spring5webapp.respositories.BookRepository;
import guru.springframework.spring5webapp.respositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// create a class that can add some data into the database to work with
@Component
public class DevBoostrap implements ApplicationListener<ContextRefreshedEvent>{

    // These fields are extended with CRUD functionality
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    // Auto injection with constructor

    @Autowired
    public DevBoostrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        // create publishers and save it to the database
        Publisher publisher = new Publisher();
        Publisher publisher1 = new Publisher();
        publisher.setName("foo");
        publisher1.setName("foo2");

        publisherRepository.save(publisher);
        publisherRepository.save(publisher1);

        // create author and book to be saved into the database
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234" ,publisher);
        // add the books and authors to the list of both class
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        // save the author and book into the database
        authorRepository.save(eric);
        bookRepository.save(ddd);

        // create another author and book
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("Spring Development", "23444", publisher1);
        rod.getBooks().add(noEJB);

        // save the author and book into the database
        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }
}
