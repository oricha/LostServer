# How to Upload a File Using Postman

## 1. Open Postman
- Open the Postman application on your system.

---

## 2. Import the Postman Collection
1. Navigate to the **"Import"** option in Postman.
2. Select the file located in the `/docs/LostFoundAPI_Postman_Collection.json` folder.
3. Open the imported request and ensure all necessary parameters (e.g., file, headers) are properly configured.
---

## 4. Go to the "Body" Tab
1. Select the **"Body"** tab below the URL bar.
2. Choose **form-data** as the request body type.

---

## 5. Add the File Field
1. In the **key** field, enter the parameter name for the file (e.g., `file`). Ensure this matches the parameter name in your API code.
2. Change the type from **Text** to **File** by clicking the dropdown next to the key field.
3. Click **"Choose Files"** and select the file from your system `/docs/Lost_Found_API.pdf`.
4. The name of attribute will be the "file" as parameter expected.

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

## Example for File Upload with curl
### API Endpoint
`http://localhost:8080/admin/upload`

### Request Body
- **Key**: `file` (Type: File)
- File: Select the file from your computer.
  Uploading the file from console with curl:

```
curl -X POST http://localhost:8080/admin/upload \
-H "Content-Type: multipart/form-data" \
-F "file=@/Users/zion/Downloads/file.pdf" \
-F "userId=12345" \
-F "description=Lost and found items"
```
---
