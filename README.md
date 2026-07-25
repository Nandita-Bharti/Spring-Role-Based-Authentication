# Spring Boot Role-Based Authentication with BCrypt

A robust Spring Boot REST API demonstrating database-backed authentication and strict role-based authorization using Spring Security, Spring Data JPA, and BCrypt password hashing.

## 🚀 Tech Stack

* **Java** 
* **Spring Boot**
* **Spring Security**
* **Spring Data JPA**
* **Maven**

## ✨ Key Security Features

* **Database Authentication:** Custom implementation of `UserDetailsService` (`CustomUserDetailsService`) and `DaoAuthenticationProvider` to authenticate users directly from a database.
* **Secure Password Hashing:** Utilizes `BCryptPasswordEncoder` to securely hash and verify passwords, ensuring plain-text passwords are never exposed or stored.
* **Role-Based Access Control (RBAC):** Utilizes `.hasRole()` and `.hasAnyRole()` to restrict endpoint access based on assigned authorities (`USER` vs `ADMIN`).
* **Data Transfer Objects (DTOs):** Securely handles incoming client payloads via `LoginRequest` and `RegisterRequest`.
* **Stateless API Ready:** CSRF is disabled (`csrf.disable()`), optimizing the configuration for REST API consumption.

## 📍 API Endpoints & Authorization Rules

Based on the `SecurityFilterChain` configuration:

### 🟢 Public Endpoints (Permit All)
No authentication required.
| Endpoint | Description |
| :--- | :--- |
| `/api/public` | General public access route. |
| `/api/auth/hello` | Public test endpoint. |
| `/api/auth/register` | Endpoint for registering new users (accepts `RegisterRequest` payload). |

### 🟡 User Endpoints (Requires `USER` or `ADMIN` Role)
| Endpoint | Description |
| :--- | :--- |
| `/api/user/**` | Routes accessible to any authenticated user with standard or elevated privileges. |

### 🔴 Admin Endpoints (Requires `ADMIN` Role ONLY)
| Endpoint | Description |
| :--- | :--- |
| `/api/admin/**` | Highly restricted routes accessible only to users explicitly assigned the ADMIN role. |

## 📁 Core Architecture

* **`config/`**: Contains the main `SecurityConfig` defining password encoders, auth providers, and request matchers.
* **`model/` & `repository/`**: Contains the `User` JPA entity and `UserRepository` for database interactions.
* **`service/`**: Houses `CustomUserDetails` and `CustomUserDetailsService` to map database user records to Spring Security's principal objects.
* **`controller/`**: Includes `AuthController` for managing authentication flows and `RoleController` for role-specific interactions.

## 🛠️ How to Run & Test

1. Clone the repository.
2. Update your database configuration (URL, username, password) inside `src/main/resources/application.properties`.
3. Build and run the application using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
4. **Testing Flow (via Postman or similar):**
   * Make a `POST` request to `/api/auth/register` to create a new user. 
   * The `UserService` will automatically encrypt the password using BCrypt before saving it to the database.
   * Attempt to access `/api/user/something` or `/api/admin/something` providing your credentials to see the Role-Based Authorization in action!
