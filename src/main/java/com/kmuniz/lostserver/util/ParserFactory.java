package com.kmuniz.lostserver.util;

/**
 * Factory class for creating instances of FileParser based on file type.
 *
 * <p>This class uses the Factory Pattern to abstract the creation of different
 * types of file parsers, such as PdfParser, and makes the code extensible.
 * New parsers can be added easily by creating a new implementation of the
 * FileParser interface and updating this factory method.</p>
 *
 * <p>The design adheres to the Open-Closed Principle, as it is open to
 * extension (adding new file types) but closed to modification (existing
 * code remains unaffected).</p>
 * */
public class ParserFactory {

    public static FileParser getParser(String fileType) {
        if ("pdf".equalsIgnoreCase(fileType)) {
            return new PdfParser();
        }
        // Add more parsers here if needed
        throw new IllegalArgumentException("Unsupported file type: " + fileType);
    }
}