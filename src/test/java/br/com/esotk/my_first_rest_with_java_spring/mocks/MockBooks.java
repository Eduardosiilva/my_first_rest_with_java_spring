package br.com.esotk.my_first_rest_with_java_spring.mocks;

import br.com.esotk.my_first_rest_with_java_spring.data.dto.BooksDTO;
import br.com.esotk.my_first_rest_with_java_spring.data.dto.UserDTO;
import br.com.esotk.my_first_rest_with_java_spring.model.Books;
import br.com.esotk.my_first_rest_with_java_spring.model.User;

import java.util.ArrayList;
import java.util.List;

public class MockBooks {

    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Books> mockEntityList() {
        List<Books> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity((int) i));
        }
        return books;
    }

    public List<BooksDTO> mockDTOList() {
        List<BooksDTO> booksDTO = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            booksDTO.add(mockDTO(i));
        }
        return booksDTO;
    }

    public Books mockEntity(Integer number) {
        Books books = new Books();
        books.setAuthor("Author Teste" + number);
        books.setLaunchDate("2020-01-01");
        books.setPrice(25.0 + number);
        books.setTitle("Title Teste" + number);
        books.setId(number.longValue());
        return books;
    }

    public BooksDTO mockDTO(Integer number) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.setAuthor("Author Teste" + number);
        booksDTO.setLaunchDate("2020-01-01");
        booksDTO.setPrice(25.0 + number);
        booksDTO.setTitle("Title Teste" + number);
        booksDTO.setId(number.longValue());
        return booksDTO;
    }



}
