# Books API

A simple CRUD-based RESTful API for managing books. This application supports creating, retrieving, updating, and deleting books with robust validation, error handling, and optional documentation.

---

## Features

- **CRUD Operations**:
  - Create, Read, Update, and Delete books using dedicated endpoints.
- **Validation**:
  - Validates book data to ensure data integrity (e.g., non-null fields, size limits, unique ISBNs).
- **Error Handling**:
  - Global exception handling with user-friendly error responses.
- **Documentation**:
  - Optional API documentation using Swagger/OpenAPI.
- **Bonus Features**:
  - Pagination and sorting for book listings.
  - Pre-configured Postman collection or curl commands for easy testing.

---

## Endpoints

### **POST /books**
- **Description**: Creates a new book.
- **Success Response**: 
  - HTTP 201 Created
  - Returns the created book object.
- **Failure Response**:
  - HTTP 400 Bad Request (Validation Errors)

### **GET /books**
- **Description**: Retrieves all books.
- **Success Response**:
  - HTTP 200 OK
  - Returns a list of all books.
- **Optional**:
  - Supports pagination and sorting.

### **GET /books/{id}**
- **Description**: Retrieves a book by its ID.
- **Success Response**:
  - HTTP 200 OK
  - Returns the book object.
- **Failure Response**:
  - HTTP 404 Not Found (If the book is not found)

### **PUT /books/{id}**
- **Description**: Updates a book by its ID.
- **Success Response**:
  - HTTP 200 OK
  - Returns the updated book object.
- **Failure Response**:
  - HTTP 404 Not Found (If the book is not found)

### **DELETE /books/{id}**
- **Description**: Deletes a book by its ID.
- **Success Response**:
  - HTTP 204 No Content
- **Failure Response**:
  - HTTP 404 Not Found (If the book is not found)

---

## Validation Rules

- **Fields**:
  - Non-null fields are enforced with `@NotNull`.
  - Length constraints are applied using `@Size`.
  - Patterns (e.g., ISBN format) are enforced using `@Pattern`.
- **Unique Constraints**:
  - ISBN must be unique.

---

## Error Handling

- **Global Exception Handling**:
  - Handles `BookNotFoundException` with a custom 404 error response.
  - Handles `MethodArgumentNotValidException` with a detailed 400 error response.
- **Custom Error Format**:
  ```json
  {
    "timestamp": "2024-12-03T12:00:00Z",
    "status": 400,
    "error": "Bad Request",
    "message": "Validation failed for field 'title'",
    "path": "/books"
  }
  ```

---

## Testing

### **Unit Tests**
- Coverage for all controller methods.
- Validates input, output, and error handling logic.

### **Integration Tests**
- Uses `MockMvc` to validate complete CRUD flows.

---

## Optional Documentation

- **Swagger/OpenAPI**:
  - Automatically generated API documentation.
  - Accessible at `/swagger-ui.html` or `/v3/api-docs` if enabled.

---

## Bonus Features

### **Pagination and Sorting**
- **Parameters**:
  - `page` (default: 0)
  - `size` (default: 10)
  - `sort` (e.g., `title,asc` or `publishedDate,desc`)

### **Postman Collection**
- Import the provided Postman collection (`postman_collection.json`) to test the API.
- Alternatively, use the following curl commands:
  - **Create a Book**:
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"title": "Book Title", "isbn": "1234567890"}' http://localhost:8080/books
    ```
  - **Get All Books**:
    ```bash
    curl -X GET http://localhost:8080/books
    ```

---

## Getting Started

### Prerequisites
- Java 17+
- Maven or Gradle
- (Optional) Postman

### Setup
1. Clone the repository.
   ```bash
   git clone https://github.com/your-repo/books-api.git
   ```
2. Navigate to the project directory.
   ```bash
   cd books-api
   ```
3. Build and run the project.
   ```bash
   mvn spring-boot:run
   ```
4. Access the API at `http://localhost:8080`.

---

## Contribution

Contributions are welcome! Please open an issue or submit a pull request for bug fixes, feature requests, or documentation updates.

---

## License

This project is licensed under the MIT License. See `LICENSE` for more details.
