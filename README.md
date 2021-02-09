# Exam

##Java's polymorphic features
```
method: Get
endpoint: /polymorphism
```

##Producer-Consumer Model
```
To Start Thread 

method:Get
endpoint:/producer-consumer/start
```
```
To Stop Thread 

method:Get
endpoint:/producer-consumer/stop
```
##Exchange Rate
```
currAcrnym = simbol for the currency 

method:Get
endpoint:/exchange-rate?currAcronym=PHP
```
##Mini-Bank
```
To Create Account

method: Post
endpoint:/mini-bank/create-account
requestBody:{
    "name":"myname",
    "username":"username",
    "password":"password"
}
```
```
To Login (to get the bearer)

method: Post
endpoint:/mini-bank/login
requestBody:{
    "username":"username",
    "password":"password"
}
```
```
To Login (to get the token)

method: Post
endpoint:/mini-bank/login
requestBody:{
    "username":"username",
    "password":"password"
}
```
```
Deposit

method: Post
endpoint:/mini-bank/deposit
requestHeader:{
  Authorization : Bearer + "token from login"
}
requestBody:{
    "amount":1000.0,
    "accounNumber":"87956487580166"
}
```
```
Withdrew

method: Post
endpoint:/mini-bank/withdrew
requestHeader:{
  Authorization : Bearer + "token from login"
}
requestBody:{
    "amount":1000.0,
    "accounNumber":"87956487580166"
}
```
```
Transfer

method: Post
endpoint:/mini-bank/transfer
requestHeader:{
  Authorization : Bearer +"token from login"
}
requestBody:{
    "amount":1000.0,
    "accounNumber":"87956487580166",
    "recieverAccountNumber":"34145110999785"
}
```
```
View Bank Account

method: Get
endpoint:/mini-bank/view-bank-account?accountNumber="account number"
requestHeader:{
  Authorization : Bearer + "token from login"
}
```


