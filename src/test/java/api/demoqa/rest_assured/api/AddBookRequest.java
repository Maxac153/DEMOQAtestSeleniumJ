package api.demoqa.rest_assured.api;

import java.util.List;

public record AddBookRequest(String userId, List<Isbn> collectionOfIsbns) {

    public record Isbn(String isbn) {
    }

    public static AddBookRequest of(String userId, String isbn) {
        return new AddBookRequest(userId, List.of(new Isbn(isbn)));
    }
}
