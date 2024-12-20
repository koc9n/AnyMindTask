# Project Name

## Prerequisites

- Docker
- Docker Compose
- Java 17
- Gradle

## Setup

### 1. Clone the Repository

```sh
git clone https://github.com/koc9n/AnyMindTask.git
cd AnyMindTask
```

### 2. Create a .env File
Create a .env file in the root directory of the project and add the following environment variables:
```
DB_NAME=yourdbname
DB_USER=yourdbuser
DB_PASSWORD=yourdbpassword
```

### 3. Run docker compose

```sh
docker-compose up
```

### 4. Run the Application

```sh
./gradlew bootRun
```

### 5. Access the Application

Open your browser and navigate to `http://localhost:8080/graphiql`
you can play here with these request examples:

for insert new payment:
```graphql
mutation {
  processPayment(
      customerId: "12345",
      price: "100.0",
      priceModifier: 0.85,
      paymentMethod: "VISA",
      datetime: "2023-10-01T12:00:00",
      additionalItem: { last4: "1234"}
  ) {
    customerId
    price
    priceModifier
    paymentMethod
    datetime
    additionalItem
    finalPrice
    points
  }
}
```

for checking sales:
```graphql
{
  getSales(startDateTime: "2024-12-15T00:00:00", endDateTime: "2024-12-16T23:59:59") {
    datetime
    sales
    points
  }
}
```
