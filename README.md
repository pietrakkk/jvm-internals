# jvm-internals 2016

##Testy mechanizmu refleksji:

    POJO -> JSON parser

    Parser bierze pod uwagę tylko pola, do których posiadamy publiczny dostęp (gettery).
    Dla porównania wygenerowany został string jsonowy przez blibliotekę jackson i własny parser.
    Rezultaty obydwu parsowań dla testowego przypadku były identyczne.

    Parser obsługuje:
        - zagnieżdżone obiekty,
        - lista wewnątrz obiektu,
        - pola typów prostych.



##Uruchomienie:
    mvn exec: java lub mvn compile exec:java
