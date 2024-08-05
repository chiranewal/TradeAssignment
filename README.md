# TradeAssignment

TradeAssignment:
Write a Java program using springboot and streaming tool, SQL/NoSQL DB along with all the JUNIT cases.
TDD approach will be preferred. Please provide a pipeline to deploy the application preferably using github actions if
not then jenkins. The pipeline must have automation regression, OSS vulnerability check (If critical or blocker found
then fail the build).

1) Adding a New Trade to Store: POST localhost:8080/trades
   **Body**
   {
   "tradeId": "AB104",
   "version": 1,
   "counterPartyId": "DB London",
   "bookId": "AB102",
   "maturityDate": "2024-08-06"
   }

2) Get All Trades from Store : GET localhost:8080/trades

