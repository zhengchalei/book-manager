package cn.runjava.book.domain.enumeration;

/**
 * The BookStatus enumeration.
 */
public enum BookStatus {
    UP("up"),
    DOWN("down"),
    WAIT("wait");

    private final String value;

    BookStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
