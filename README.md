# Spring Security Test Project

This is a simple Spring Boot project demonstrating **user authentication** using Spring Security. The project includes:

- In-memory authentication
- BCrypt password encoding
- H2 in-memory database for testing
- Basic secured and public endpoints

---

## Technologies Used

- Java 22
- Spring Boot 3.5.5
- Spring Security
- Spring Data JPA
- H2 Database
- Maven

---

## Getting Started

### Prerequisites

- Java 22 installed
- Maven installed
- Git installed

### Running the Application

1. Clone the repository:

```bash
git clone https://github.com/divaydua/springSecurity.git
cd springSecurity

	2.	Build and run the application:

mvn clean install
mvn spring-boot:run

	3.	Access the application:

	•	Home (secured): http://localhost:8080/
	•	Public endpoint: http://localhost:8080/public
	•	H2 console: http://localhost:8080/h2-console

Default In-Memory Users

Username	Password	Role
test	1234	USER
admin	pass@123	ADMIN


⸻

Project Structure

src
├── main
│   ├── java/com/example/duadivay/springSecurityTest
│   │   ├── config       # Security configuration
│   │   ├── controller   # Controllers for endpoints
│   │   ├── entities     # UserEntity class
│   │   └── service      # UserDetailsService implementations
│   └── resources
│       ├── application.properties
│       └── templates (optional if custom login page is added)


⸻

Notes
	•	Currently uses default Spring Security login page.
	•	Passwords are encoded using BCrypt.
	•	Can be extended with custom login pages, database users, and roles/authorities.

⸻

License

This project is open-source and free to use.

---
