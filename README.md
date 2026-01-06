# AnimeLounge

AnimeLounge is a Java-based desktop application developed for **Visual Programming 2**.  
The application allows users to connect and communicate through **real-time text messaging**, organized into chat rooms based on specific anime titles.

The project is inspired by platforms like Discord, but focuses on anime-based communities and text-only communication rather than voice chat.

---

## Course Information
- **Course:** Visual Programming 2  
- **Project Type:** Java Desktop Application  
- **Author:** Tyler Williams  

---

## Features
- Real-time instant messaging
- Anime-specific chat rooms
- User-to-user communication
- Database-backed users and messages
- External anime API integration

---

## Project Structure
- `pom.xml` – Maven configuration and dependency management  
- `src/main/java` – Java source code  
- `nbactions.xml` – NetBeans project actions  

---

## Technologies Used
- **Java**
- **NetBeans IDE**
- **MySQL** (local database)
- **Node.js** (local server)
- **Socket.IO** (real-time communication)
- **JSON**
- **Anime Database API**

---

## Building and Running

### Requirements
Before running the project, ensure the following are installed:

- Java JDK
- NetBeans IDE
- MySQL
- Node.js
- Socket.IO dependencies
- JSON dependencies

---

### Database Setup
- A **local MySQL database** is required
- The database is not hosted online
- Database credentials must be configured in the project before running

---

### Server Setup
- A local **Node.js server** must be running for messaging to function
- Install required Socket.IO packages
- Start the server before launching the Java application

---

## Running the Application
1. Start the Node.js server
2. Ensure the MySQL database is running
3. Open the project in **NetBeans**
4. Build the project using Maven
5. Run the main Java class

Once all services are active, the application should run correctly in NetBeans.

---

## Limitations / Notes
- Server and database must be running locally
- Text-based messaging only (no voice support)
- Internet connection required for anime API access

---

## Educational Purpose
This project was created for **Visual Programming 2** to demonstrate:
- Client-server architecture
- Real-time communication using sockets
- Database integration with MySQL
- API consumption
- Java GUI application development
