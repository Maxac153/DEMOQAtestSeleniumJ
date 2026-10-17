package ui.demoqa.selenium.models.elements;

public enum Links {
    SIMPLE_LINK_LINK("demosite"),
    DYNAMIC_LINK_LINK("demosite"),

    CREATED_LINK("Link has responded with staus 201 and status text Created"),
    NO_CONTENT_LINK("Link has responded with staus 204 and status text No Content"),
    MOVED_LINK("Link has responded with staus 301 and status text Moved Permanently"),
    BAD_REQUEST_LINK("Link has responded with staus 400 and status text Bad Request"),
    UNAUTHORIZED_LINK("Link has responded with staus 401 and status text Unauthorized"),
    FORBIDDEN_LINK("Link has responded with staus 403 and status text Forbidden"),
    INVALID_URL_LINK("Link has responded with staus 404 and status text Not Found");

    private final String value;

    Links(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
