# Trading Simulator

## Overview

**Trading Simulator** is a Spring Boot backend application designed to simulate real-world stock trading in a safe, learning-oriented environment. It features **JWT-secured authentication**, wallet management, live stock prices, AI-driven analysis, buy/sell operations, and portfolio tracking.

---

## Features

- 🔐 JWT-secured login/signup  
- 💰 Wallet with dummy money support  
- 📈 Real-time stock price & AI analysis API integration  
- 🛒 Buy/Sell stock operations  
- 📊 Portfolio tracking with live PnL calculation  
- 🗂️ Modular and extendable architecture  

---

## Project Structure

```bash
src/
└── main/
├── java/com/tradingsim/
│ ├── controller/
│ │ ├── AuthController.java
│ │ ├── WalletController.java
│ │ ├── StockController.java
│ │ ├── TradingController.java
│ │ └── PortfolioController.java
│ ├── service/
│ │ ├── UserService.java
│ │ ├── WalletService.java
│ │ ├── StockService.java
│ │ └── TradingService.java
│ ├── entity/
│ │ ├── User.java
│ │ ├── Wallet.java
│ │ ├── PortfolioEntry.java
│ │ └── Stock.java
│ ├── dto/
│ │ ├── AuthRequest.java
│ │ ├── AuthResponse.java
│ │ ├── SignupRequest.java
│ │ ├── WalletDto.java
│ │ └── PortfolioDto.java
│ ├── config/
│ │ ├── JwtUtil.java
│ │ └── SecurityConfig.java
│ └── repository/
│ ├── UserRepository.java
│ ├── WalletRepository.java
│ └── PortfolioRepository.java
└── resources/
├── application.properties (env placeholders)

```

---

## Setup Instructions

1. Clone the repo:

git clone https://github.com/<your-username>/trading-simulator.git
cd trading-simulator


2. Create `.env` file (ignored in git) with your secrets:

```bash
DB_USER=****
DB_PASS=****
JWT_SECRET=****
STOCK_DETAILS_URL=****
AI_ANALYSIS_URL=****
LIVE_PRICE_URL=https://stockdetailsapi.onrender.com/api/price
```



3. Make sure MySQL is running and create database:

CREATE DATABASE tradingdb;


4. Build and run the app:

mvn clean install
mvn spring-boot:run


The server will start on `http://localhost:9090`

---

## API Endpoints

### Auth

- **Signup**  
  `POST /api/auth/signup`  
  Request Body (JSON):
```bash
{
"username": "testuser",
"email": "testuser@example.com",
"password": "password123"
}
```


- **Login**  
  `POST /api/auth/login`  
  Request Body (JSON):
```bash
{
"username": "testuser",
"password": "password123"
}
```
```bash
Response:
{
"token": "jwt_token_here"
}
```


---

### Wallet

- **Get Wallet Balance**  
  `GET /api/wallet`  
  Headers: `Authorization: Bearer <jwt_token>`  

- **Add Dummy Money**  
  `POST /api/wallet/add-dummy?amount=100000`  
  Headers: `Authorization: Bearer <jwt_token>`  

---

### Stocks

- **Top 100 Stocks**  
  `GET /api/stocks/top`  

- **Stock Details**  
  `GET /api/stocks/{symbol}`  

- **AI Analysis**  
  `GET /api/stocks/{symbol}/ai`  

- **Live Price**  
  `GET /api/stocks/{symbol}/price`  

---

### Trading

- **Buy Stock**  
  `POST /api/trade/buy?symbol=INFY.NS&qty=10`  
  Headers: `Authorization: Bearer <jwt_token>`  

- **Sell Stock**  
  `POST /api/trade/sell?symbol=INFY.NS&qty=5`  
  Headers: `Authorization: Bearer <jwt_token>`  

---

### Portfolio

- **View Portfolio**  
  `GET /api/portfolio`  
  Headers: `Authorization: Bearer <jwt_token>`  
```bash
  Response:
[
{
"symbol": "INFY.NS",
"quantity": 10,
"avgPrice": 1500.0,
"investedAmount": 15000.0,
"currentPrice": 1520.0,
"pnl": 200.0
}
]
```


---

## Notes

- Keep `.env` file private; it contains DB credentials and JWT secret.  
- `application.properties` uses placeholders so contributors know what to set.  
- Use `application-local.properties` for local development (ignored by git).  
- Rotate JWT secret and DB password if accidentally pushed.  

---

## Technologies Used

- Java 17  
- Spring Boot 3.x  
- Spring Security + JWT  
- Spring Data JPA  
- MySQL 8  
- REST APIs  
- Maven  

---

