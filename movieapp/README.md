localhost:8082'ye get isteği yollanarak uygulamadaki bütün endpointler ve örnek datalar elde edilebilir.
<pre>
{
    "link": "localhost:8082/movieapp/user/register",
    "httpMethodType": "Post",
    "purpose": "Kullanıcının sisteme kayıt olmasını sağlayan endpoint.",
    "data": {
        "name": "Çağla",
        "surname": "Sır",
        "password": "1234567",
        "email": "caglasir4@gmail.com",
        "subscriptionType": "ONE_MONTH"
    }
},
{
    "link": "localhost:8082/movieapp/user/login",
    "httpMethodType": "Post",
    "purpose": "Kullanıcının sisteme login olmasını sağlayan endpoint.",
    "data": {
        "email": "caglasir4@gmail.com",
        "password": "1234567"
    }
},
{
    "link": "localhost:8082/movieapp/user/{userId}/change/password",
    "httpMethodType": "Post",
    "purpose": "Kullanıcının şifresini değiştirmesini sağlayan endpoint.",
    "data": {
        "password": "newpassword"
    }
},
{
    "link": "localhost:8082/movieapp/user/{userId}/change/name",
    "httpMethodType": "Post",
    "purpose": "Kullanıcının ismini değiştirmesini sağlayan endpoint.",
    "data": {
        "name": "newname"
    }
},
{
    "link": "localhost:8082/movieapp/user/{userId}/movie",
    "httpMethodType": "Post",
    "purpose": "Kullanıcının sisteme film eklemesini sağlayan endpoint.",
    "data": {
        "movieId": 1,
        "comment": "It was a nice movie.",
        "score": 7
    }
},
{
    "link": "localhost:8082/movieapp/user/{userId}/movie",
    "httpMethodType": "Get",
    "purpose": "Verilen userId'ye sahip kullanıcının eklediği filmleri getiren endpoint.",
    "data": null
},
{
    "link": "localhost:8082/movieapp/movies",
    "httpMethodType": "Get",
    "purpose": "Sisemdeki bütün kullanıcıların eklediği bütün filmleri getiren endpoint.",
    "data": null
}</pre>