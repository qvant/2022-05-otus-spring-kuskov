package ru.otus.spring.library.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "dropDb", author = "", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "", runAlways = true)
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> authorsCollection = db.getCollection("authors");
        var pushkin = new Document().append("name", "Пушкин");
        authorsCollection.insertOne(pushkin);
        var lermontov = new Document().append("name", "Лермонтов");
        authorsCollection.insertOne(lermontov);
        var strugatslie = new Document().append("name", "А. и Б. Стругацкие");
        authorsCollection.insertOne(strugatslie);
        var tolkine = new Document().append("name", "Толкиен");
        authorsCollection.insertOne(tolkine);

        MongoCollection<Document> genresCollection = db.getCollection("genres");
        var novel = new Document().append("name", "Роман");
        genresCollection.insertOne(novel);
        var poem = new Document().append("name", "Поэма");
        genresCollection.insertOne(poem);
        var fantasy = new Document().append("name", "Фентези");
        genresCollection.insertOne(fantasy);
        var scifi = new Document().append("name", "Фантастика");
        genresCollection.insertOne(scifi);
        var memories = new Document().append("name", "Мемуары");
        genresCollection.insertOne(memories);

        MongoCollection<Document> booksCollection = db.getCollection("books");
        var doc = new Document();
        doc.append("title", "Руслан и Людмила");
        doc.append("isbn", "978-5-9268-2735-1");
        doc.append("genre", poem);
        doc.append("author", pushkin);
        booksCollection.insertOne(doc);
        doc = new Document();
        doc.append("title", "Евгений Онегин");
        doc.append("isbn", "9780460875950");
        doc.append("genre", novel);
        doc.append("author", pushkin);
        booksCollection.insertOne(doc);
        doc = new Document();
        doc.append("title", "Понедельник начинается в субботу");
        doc.append("isbn", "978-5-17-090334-4");
        doc.append("genre", scifi);
        doc.append("author", strugatslie);
        booksCollection.insertOne(doc);
        doc = new Document();
        doc.append("title", "Хищные вещи века");
        doc.append("isbn", "978-5-17-094720-1");
        doc.append("genre", scifi);
        doc.append("author", strugatslie);
        booksCollection.insertOne(doc);
        doc = new Document();
        doc.append("title", "Властелин колец");
        doc.append("isbn", "978-5-17-089322-5");
        doc.append("genre", fantasy);
        doc.append("author", tolkine);
        booksCollection.insertOne(doc);

    }
}
