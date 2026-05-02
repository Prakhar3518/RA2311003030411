RA2311003030411 — AffordMed Backend Tasks

Tasks Overview
TaskFolderStatusLogging Middlewarelogging_middleware/DoneVehicle Maintenance Schedulervehicle_maintence_scheduler/DoneNotification Appnotification_app_be/Not completed (explained below)
Postman screenshots and running code screenshots are in the root of the repo.

Task 1 — Logging Middleware
A reusable logging package built in Spring Boot that sends structured logs to the AffordMed evaluation server.
The main function is:
log(stack, level, package, message)
It validates the fields and calls the log API with a Bearer token. The middleware also auto-intercepts every HTTP request so you don't have to manually log every endpoint.
Output screenshots are inside logging_middleware/ folder.

Task 2 — Vehicle Maintenance Scheduler
A Spring Boot microservice that fetches depots and vehicles from the AffordMed API and figures out the best maintenance schedule for each depot using a 0/1 Knapsack algorithm — maximising impact without going over the available mechanic-hours.
Tech Stack: Java 17, Spring Boot 3.2, Lombok, Maven
Endpoint: GET /api/schedule
Output screenshots are inside vehicle_maintence_scheduler/ folder.

Task 3 — Notification App (incomplete)
Since the use of AI tools like GitHub Copilot or any AI model was not allowed, completing all 3 tasks within the given time bracket was not feasible without those tools. The folder notification_app_be/ exists but the implementation is not done.

Running any task locally
Go into the respective folder and run:
bashmvn spring-boot:run
