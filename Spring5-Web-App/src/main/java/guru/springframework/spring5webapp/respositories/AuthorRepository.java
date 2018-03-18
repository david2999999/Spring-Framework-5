package guru.springframework.spring5webapp.respositories;

import guru.springframework.spring5webapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long>{

    // the extension basically creates CRUD functionality

}
