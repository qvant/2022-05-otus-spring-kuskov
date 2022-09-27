package ru.otus.spring.library.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "comments")
@Data
public class Comment {
//    @Id
//    private ObjectId id;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "book_id")
    //@DBRef(lazy = true)
    //private Book book;

    private String text;


    public Comment() {

    }

    public Comment(//Book book,
                   String text) {
        //this.book = book;
        this.text = text;
    }

//    public ObjectId getId() {
//        return id;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //public Book getBook() {
//        return book;
//    }

}
