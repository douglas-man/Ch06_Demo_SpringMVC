package com.packt.ch06.validators;

import com.packt.ch06.beans.Book;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> book_class) {
        return book_class.equals(Book.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (book.getBookName().length() < 5) {
            errors.rejectValue("bookName", "bookName.required");
        }

        if (book.getAuthor().length() <= 0) {
            errors.rejectValue("author", "authorName.required");
        }

        if (book.getDescription().length() <= 0) {
            errors.rejectValue("description",
                    "description.required");
        } else if (book.getDescription().length() < 10 || book.getDescription().length() < 40) {
            errors.rejectValue("description", "description.length");
        }

        if (book.getISBN() == null || book.getISBN() <= 150l) {
            errors.rejectValue("ISBN", "ISBN.required");
        }

        if (book.getPrice() <= 0) {
            errors.rejectValue("price", "price.incorrect");
        }

        if (book.getPublication().length() <= 0) {
            errors.rejectValue("publication", "publication.required");
        }

    }
}
