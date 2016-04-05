# jvm-internals 2016

##Testy mechanizmu refleksji:

    SimpleDateFormat thread safety test

    Przeprowadzono testy SimpleFormat pod kątem thread safety. Jak łatwo się domyślić (czytając dokumentację) okazało się, że nie jest.
    Przeprowadzono próbę skonwertowania daty (20 iteracji), w zależności od liczby uruchomień, rezultaty prezentowały się m. in. następująco:


    Sat Jun 08 00:00:00 CEST 1991
    Fri Jun 08 00:00:00 CET 8
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991
    Sat Jun 08 00:00:00 CEST 1991

    co świadczy o wadliwym działaniu na wielu wątkach.


    Zapewnienie bezpieczeństwa wątkowego zostało rozwiązne poprzez tworzenie nowej instancji SimpleDateFormat dla każdego wątku.


##Uruchomienie:
    mvn exec: java lub mvn compile exec:java
