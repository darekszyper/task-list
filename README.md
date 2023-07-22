## Web application that allows users to manage their to-do list. It enables users to add, edit, and delete tasks, as well as mark them as completed. Additional functionalisty is RequestCounter that keeps track of the number of requests sent from the start of the application.

## Technologies:
- Java 17
- Spring Boot 3
- Thymeleaf
- PostgreSQL
- Liquibase
- Mapstruct
- Lombok
- Bootstrap

![Zrzut ekranu 2023-07-22 201527](https://github.com/darekszyper/task-list/assets/114878453/3415e804-1fc4-4c61-8a37-e24163cc25a2)
![Zrzut ekranu 2023-07-22 201430](https://github.com/darekszyper/task-list/assets/114878453/8340f231-a795-47e0-95a5-9bec09f1b86e)
![Zrzut ekranu 2023-07-22 201616](https://github.com/darekszyper/task-list/assets/114878453/83ec12f4-51db-4590-921a-f6d6b0a37aca)
![Zrzut ekranu 2023-07-22 202007](https://github.com/darekszyper/task-list/assets/114878453/3b51cae1-3731-4329-8ffb-3a589e39daca)

## Instructions for Running the Application:

- Clone the application code from the GitHub repository.
- Make sure you have Docker installed on your computer.
- Navigate to the root directory of the cloned project.
- Open a terminal and run the following command to start the PostgreSQL database: docker-compose up -d
- Now, you can build and run the TaskList application.
- The TaskList application will start, and you can access it in your web browser at http://localhost:8080/tasks.
- You can now use the TaskList application to manage your tasks. Any tasks you add, edit, or delete will be stored in the PostgreSQL database.

