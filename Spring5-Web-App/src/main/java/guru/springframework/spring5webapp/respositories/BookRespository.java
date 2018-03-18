package guru.springframework.spring5webapp.respositories;

import guru.springframework.spring5webapp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRespository extends CrudRepository<Book, Long> {

    // the extension basically creates CRUD functionality
}
