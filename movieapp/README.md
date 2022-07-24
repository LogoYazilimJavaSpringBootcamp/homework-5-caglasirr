## movieapp hakkında

localhost:8082'ye get isteği yollanarak uygulamadaki bütün endpointler ve endpointe gitmek için kullanılacak olan örnek datalar elde edilebilir. 
Aynı zamanda aşağıda da görebilirsiniz :) Uygulama başladığında Movie ve SubscriptionType tablolarına birkaç data eklenir. localhost:8082/users/movies ve localhost:8082/users/subscription-types endpointlerine get isteği yollayarak görüntülenebilir.
Uygulamada PostgreSQL veritabanı ve RabbitMQ kullanılmıştır.

#### movieapp, email, paymentDto için üç farklı db vardır ve portları aşağıdaki gibidir.
spring.datasource.url=jdbc:postgresql://localhost:5432/registration
<br>spring.datasource.url=jdbc:postgresql://localhost:5432/email
<br>spring.datasource.url=jdbc:postgresql://localhost:5432/payment

#### RabbitMQ portu: localhost:15672


<pre>
    {
        "link": "localhost:8082/users/movies",
        "httpMethodType": "Get",
        "purpose": "Databasede bulunan ve kullanıcıların ekleyebileceği filmleri (Movie tablosu) getiren endpoint.",
        "data": null
    },
    {
        "link": "localhost:8082/users/subscription-types",
        "httpMethodType": "Get",
        "purpose": "Kullanıcının seçebileceği üyelik tiplerini (SubscriptionType tablosu) getiren endpoint.",
        "data": null
    },
    {
        "link": "localhost:8082/users/register",
        "httpMethodType": "Post",
        "purpose": "Kullanıcının sisteme kayıt olmasını sağlayan endpoint.",
        "data": {
            "name": "Çağla",
            "surname": "Sır",
            "password": "1234567",
            "email": "caglasir4@gmail.com",
            "subscriptionTypeId": 1
        }
    },
    {
        "link": "localhost:8082/users/login",
        "httpMethodType": "Post",
        "purpose": "Kullanıcının sisteme login olmasını sağlayan endpoint.",
        "data": {
            "email": "caglasir4@gmail.com",
            "password": "1234567"
        }
    },
    {
        "link": "localhost:8082/users/{userId}",
        "httpMethodType": "Put",
        "purpose": "Path variable olarak verilen userid'ye sahip kullanıcının ismini ve şifresini değiştirmesini sağlayan endpoint.",
        "data": {
            "name": "newname",
            "password": "newpassword"
        }
    },
    {
        "link": "localhost:8082/users/{userId}/movie",
        "httpMethodType": "Post",
        "purpose": "Path variable olarak verilen userid'ye sahip kullanıcının sisteme film eklemesini sağlayan endpoint.",
        "data": {
            "movieId": 1,
            "comment": "It was a nice movie.",
            "score": 7
        }
    },
    {
        "link": "localhost:8082/users/{userId}/movie",
        "httpMethodType": "Get",
        "purpose": "Path variable olarak verilen userid'ye sahip kullanıcının eklediği filmleri getiren endpoint.",
        "data": null
    },
    {
        "link": "localhost:8082/users/recommended-movies",
        "httpMethodType": "Get",
        "purpose": "Sisemdeki bütün kullanıcıların eklediği bütün filmleri getiren endpoint.",
        "data": null
    },
    {
        "link": "localhost:8082/users/{userId}/movie",
        "httpMethodType": "Put",
        "purpose": "Kullanıcının sisteme önceden eklediği filmin yorumunu ve puanını güncellemesini sağlar.",
        "data": {
            "movieId": 1,
            "comment": "It was a nice movie.",
            "score": 7
        }
    }</pre>