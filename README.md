1. Get Wallet Balances
URL: /api/wallet
Method: GET
Description: Retrieve all wallet balances for a user.

2. Execute Trade
URL: /api/transaction/trade
Method: POST
Description: Execute a trade (buy/sell) for a user. The action must be BUY or SELL (all uppercase)
Request Body:
```json
{
    "currency": "BTC",
    "action": "BUY",
    "amount": 0.05
}
```

3. Get Trade History
URL: /api/transaction/list
Method: GET
Description: Retrieve all trades for user.

4. Get Latest Aggregated Price
URL: /api/price/latest
Method: GET
Description: Retrieve the latest aggregated price for supported crypto pairs.
