package com.medialab.mybooks.repositories;

import com.medialab.mybooks.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
