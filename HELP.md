# How to Upload a File Using Postman

## 1. Open Postman
- Open the Postman application on your system.

---

## 2. Create a New Request
1. Click **"New"** → **"Request"**.
2. Name your request and save it in a collection if needed.

---

## 3. Set Request Type and URL
1. Set the request type to **POST** (or another HTTP method required by your API).
2. Enter the request URL (e.g., `http://localhost:8080/admin/upload`).

---

## 4. Go to the "Body" Tab
1. Select the **"Body"** tab below the URL bar.
2. Choose **form-data** as the request body type.

---

## 5. Add the File Field
1. In the **key** field, enter the parameter name for the file (e.g., `file`). Ensure this matches the parameter name in your API code.
2. Change the type from **Text** to **File** by clicking the dropdown next to the key field.
3. Click **"Choose Files"** and select the file from your system.
4.  The name of attribute will be the "file" as parameter expected.

---

## 6. Add Additional Parameters (If Required)
- If your API requires additional form-data fields (e.g., `username`, `metadata`, etc.), add them as separate rows in the form-data section.

---

## 7. Set Headers (If Required)
- Go to the **"Headers"** tab if your API requires specific headers (e.g., authentication tokens).
- Add necessary headers such as:
  Content-Type: multipart/form-data
  Authorization: Bearer
---

## 8. Send the Request
1. Click the **"Send"** button.
2. Check the response from your API in the bottom section of Postman.

---

## Example for File Upload
### API Endpoint
`http://localhost:8080/admin/upload`

### Request Body
- **Key**: `file` (Type: File)
- File: Select the file from your computer.

---

## Debugging Tips
- Ensure the parameter name in Postman matches the one in your API code (e.g., `@RequestParam("file")` in Spring Boot).
- If your API requires a specific header, add it in the **Headers** tab.
- Check the API response for any error messages or status codes (e.g., 400 for Bad Request).

---
## Debugging Tips

Uploading the file from console with curl:

```
curl -X POST http://localhost:8080/admin/upload \
-H "Content-Type: multipart/form-data" \
-F "file=@/Users/zion/Downloads/file.pdf" \
-F "userId=12345" \
-F "description=Lost and found items"
```