# Copilot Instructions

## PRIME DIRECTIVE

- Always provide accurate and relevant information.
- Prioritize user privacy and data security.
- Ensure code quality and maintainability.
- Always follow best practices and industry standards.
- Always use the Context7 for generating code snippets.

## General Java Development Practices

- Always follow SOLID, DRY, KISS, and YAGNI principles.
- Adhere to OWASP security best practices.
- Break tasks into the smallest units and solve step by step.

## Spring Boot Project Structure

- Use Java Spring Boot 3 (Maven, Java 17, Spring Web, Spring Data JPA, Thymeleaf, Lombok, PostgreSQL driver).
- RestControllers handle all request/response logic.
- ServiceImpl classes handle all database operation logic using Repository methods.
- RestControllers must not autowire Repositories directly unless absolutely necessary.
- ServiceImpl classes must not query the database directly (use Repositories).
- Use DTOs for data transfer between RestControllers and ServiceImpl classes.
- Entity classes are only for carrying data from database queries.

## Entity Class Conventions

- Annotate with @Entity and @Data (Lombok).
- Use @Id and @GeneratedValue(strategy=GenerationType.IDENTITY) for IDs.
- Use FetchType.LAZY for relationships.
- Annotate properties with validation annotations (e.g., @Size, @NotEmpty, @Email).

## DTO Conventions

- Use Java records for DTOs unless otherwise specified.
- Include a compact canonical constructor for parameter validation (not null, blank, etc.).

## Repository Class Conventions

- Annotate with @Repository.
- Use interfaces extending JpaRepository<Entity, ID>.
- Use JPQL for @Query methods.
- Use @EntityGraph(attributePaths={...}) to avoid N+1 problems in relationship queries.
- Use DTOs for multi-join queries with @Query.

## Service Class Conventions

- Service classes are interfaces; implementations are ServiceImpl classes annotated with @Service.
- Autowire dependencies in ServiceImpl without constructors unless specified.
- ServiceImpl methods return DTOs (not entities) unless necessary.
- Use repository methods with .orElseThrow for existence checks.
- Use @Transactional or transactionTemplate for multiple sequential DB operations.

## RestController Conventions

- Annotate with @RestController and specify class-level @RequestMapping.
- Use best-practice HTTP method annotations (e.g., @PostMapping, @GetMapping).
- Autowire dependencies in class methods without constructors unless specified.
- Methods return ResponseEntity<ApiResponse>.
- Implement all logic in try-catch blocks; handle errors with GlobalExceptionHandler.

## ApiResponse & GlobalExceptionHandler

- ApiResponse and GlobalExceptionHandler classes must be present and follow best practices for structure and error handling.
