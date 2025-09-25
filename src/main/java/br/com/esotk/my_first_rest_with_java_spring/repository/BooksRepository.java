package br.com.esotk.my_first_rest_with_java_spring.repository;

import br.com.esotk.my_first_rest_with_java_spring.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
