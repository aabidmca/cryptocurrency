# cryptocurrency
Run project as java application with class CryptocurrencyApplication


# Create resource CURL

curl --location --request POST 'localhost:8080/rest/v1/cryptocurrency' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Bitcoin", "code": "BTC", "price" : 4.06
}'


# Listing of resource

curl --location --request GET 'localhost:8080/rest/v1/cryptocurrency/list?sort=createdAt,desc&size=20&page=1'

we can use filter with query parameter one or more at a time like :
?createdAt=01/19/2022
?createdAtTo=01/19/2022
?createdAtFrom=01/19/2022
?updatedAt=01/19/2022
?updatedAtFrom=01/19/2022
?updatedAtTo=01/19/2022
?price=140.26

#Listing Response will look like :
{
    "pageInfo": {
        "totalRecords": 26,
        "totalPages": 2
    },
    "data": [
        {
            "id": 28,
            "name": "Tezas",
            "code": "XTZ",
            "price": "$4.06",
            "createdAt": "2022-01-19T15:58:09",
            "updatedAt": "2022-01-19T15:58:09"
        }
    ]
 }

# Create resource CURL

curl --location --request POST 'localhost:8080/rest/v1/cryptocurrency' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Tezas", "code": "XTZ", "price" : 4.06
}'

#Update resource CURL

curl --location --request PUT 'localhost:8080/rest/v1/cryptocurrency/29' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Tezas",
    "code": "XTZ",
    "price": 4.26
}'

#Retrieve Resource CURL

curl --location --request GET 'localhost:8080/rest/v1/cryptocurrency/28'


#Delete Resource CURL
curl --location --request DELETE 'localhost:8080/rest/v1/cryptocurrency/28' 

# History of crypto currency: default history is for 90 days if historyInDays parameter does not have any value or not used
curl --location --request GET 'localhost:8080/rest/v1/cryptocurrency/history/28?historyInDays=90'


