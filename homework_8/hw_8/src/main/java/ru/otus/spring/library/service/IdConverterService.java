package ru.otus.spring.library.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdConverterService {

    private final IOService ioService;

    public ObjectId convertToObjectId(String id) {
        ObjectId objectId = null;
        try {
            objectId = new ObjectId(id);
        } catch (IllegalArgumentException exception) {
            ioService.print("Неверный идентификатор " + id);
        }
        return objectId;
    }

    public Integer convertToInteger(String id) {

        Integer objectId = null;
        try {
            objectId = Integer.parseInt(id);
        } catch (Exception exception) {
            ioService.print("Неверный идентификатор " + id);
        }
        return objectId;
    }
}
