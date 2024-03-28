package softuni.exam.util;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path BOOKS_JSON_IMPORT_PATH = Path.of("skeleton", "src", "main", "resources", "files", "json", "books.json");
    public static final Path LIBRARY_MEMBER_JSON_IMPORT_PATH = Path.of("skeleton", "src", "main", "resources", "files", "json", "library-members.json");
    public static final Path BORROWING_RECORD_XML_IMPORT_PATH = Path.of("skeleton", "src", "main", "resources", "files", "xml", "borrowing-records.xml");
}
