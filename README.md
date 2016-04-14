# jvm-internals 2016

##Lab 7


Dostępne endpointy:

1.

URL: http://localhost:8080/timetest/time/time_param

Powoduje usypianie na czas, który okreslony jest za pomocą parametry time_param.

2.

http://localhost:8080/timetest/time2/time_param

Powoduje usypianie na czas = time_param * 2, który okreslony jest za pomocą parametry time_param.

##Uruchomienie:
    mvn compile
    mvn assembly:assembly
    mvn exec:exec