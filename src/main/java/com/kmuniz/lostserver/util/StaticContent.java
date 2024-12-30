package com.kmuniz.lostserver.util;

public class StaticContent {

    public static final String PDF_TYPE = "pdf";

    public static final String htmlUploadedResponse = """
            <html>
            <body>
                <h1>File processed and items uploaded</h1>
                <p>
                    <a href="/items">View Items</a>
                </p>
                <p>
                    <a href="/claim">View Claims</a>
                </p>
                <p>
                    <a href="/users">User Profile</a>
                </p>
            </body>
            </html>
            """;
}
