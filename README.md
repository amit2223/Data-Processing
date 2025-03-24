# Real-time Data Tracking System

## Overview
This project is a real-time tracking and visualization system that processes positional data from a JSON file, stores it in a database, and displays it using graphs and heatmaps. This project is a real-time data tracking system that consists of three main components:
1. **Python Script**: Reads the Humand data from Given JSON file and sends it to a Spring Boot API.
2. **Spring Boot Backend**: Stores incoming data in a MySQL database and provides APIs to fetch the data for the frontend.
3. **React.js Frontend**: Reterives the data from the database using apis and visualizes the stored data using graphs and heatmaps.

## Tech Stack
- **Backend**: Spring Boot, Java, MySQL, JPA/Hibernate
- **Frontend**: React.js, Recharts, Heatmap.js, Moment.js
- **Database**: MySQL
- **Data Ingestion**: Python, Requests library

## Setup Instructions

### 1. Backend (Spring Boot)
#### Prerequisites:
- JDK 17+
- Maven
- MySQL Database

#### Steps:
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo.git
   cd backend
   ```
2. Configure `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```
3. Build and run the application:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

### 2. Frontend (React.js)
#### Prerequisites:
- Node.js
- npm or yarn

#### Steps:
1. Navigate to the frontend folder:
   ```sh
   cd frontend
   ```
2. Install dependencies:
   ```sh
   npm install
   ```
3. Start the React application:
   ```sh
   npm start
   ```

### 3. Data Ingestion (Python Script)
#### Prerequisites:
- Python 3+

#### Steps:
   ```
1. Run the script:
   ```sh
   python script.py
   ```

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/data` | Accepts JSON data and stores it in MySQL |
| `GET`  | `/api/data/graph` | Fetches aggregated data for graph visualization |
| `GET`  | `/api/data/heatmap` | Retrieves heatmap positional data |

## Dependencies
### Backend (Spring Boot)
- Spring Web
- Spring Data JPA
- MySQL Connector
- Lombok

### Frontend (React.js)
- Recharts
- Heatmap.js
- Moment.js

### Data Ingestion (Python)
- Requests
- JSON

