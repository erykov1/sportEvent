# Kurs
## Testowanie i Jakość Oprogramowania / Projekt

# Autor 
## Aleksandra Piątek 34316, Eryk Marnik 34307

# Temat projektu
## Aplikacja do organizacji wydarzeń sportowych

# Opis projektu
"Aplikacja do Organizacji Wydarzeń Sportowych" pozwala na rejestrację oraz logowanie do systemu.
Użytkownicy zalogowani mogą przeglądać dostępne wydarzenia sportowe i zgłaszać w nich chęć uczestnictwa.
Dodatkowo mogą przeglądać własne zgłoszenia oraz filtrować je po statusie. W przypadku zgłoszenia, które
nie jest zaakcpetowane przez administratora, można je usunąć. Administrator może dodawać nowe wydarzenia sportowe
oraz adresy do nich. Dodatkowo może przeglądać zgłoszenia użytkowników, a następnie odrzucać lub je akceptować.

- Strona główna
![image](https://github.com/erykov1/sportEvent/assets/62502523/ab9375c4-1b34-4ce2-b074-03eba07c0753)

- Strona rejestracji (analogicznie wygląda strona logowania)
![image](https://github.com/erykov1/sportEvent/assets/62502523/2d063fa2-8a0b-4771-9a04-736c2c8bfe6b)

- Strona wyboru kategorii sportowej
![image](https://github.com/erykov1/sportEvent/assets/62502523/ae5a1fd9-e3f5-4102-92d3-878be5065aed)

- Strona wyboru wydarzenia sportowego z danej kategorii
![image](https://github.com/erykov1/sportEvent/assets/62502523/2e3e5afb-67a0-4f9a-b8af-ece1cc5b1533)

- Strona szczegółów wydarzenia
![image](https://github.com/erykov1/sportEvent/assets/62502523/c6fde170-892c-455e-a43b-c8405e73526a)

- Strona zgłoszeń ('Przeglądaj zgłoszenia' widzi tylko administrator)
  ![image](https://github.com/erykov1/sportEvent/assets/62502523/0679a5ce-5af1-4094-a06a-886d20a9a1d9)

- Strona przeglądania zgłoszeń
![image](https://github.com/erykov1/sportEvent/assets/62502523/0a7f1d21-1af6-4bc7-86c9-4e440124faff)

- Strona tworzenia wydarzenia (część 1 dodanie adresu wydarzenia)
![image](https://github.com/erykov1/sportEvent/assets/62502523/dcdf87ef-15b9-412b-aad2-ca8ca0b9c245)

- Strona tworzenia wydarzenia (część 2 dodanie danych do wydarzenia)
![image](https://github.com/erykov1/sportEvent/assets/62502523/92635312-092d-4d36-89e0-cfda06dd7321)



## Uruchomienie projektu
Należy dodać i uruchomić zadanie maven-owe z następujący zmiennymi:
 - spring.datasource.password=<hasło>
 - spring.datasource.url=jdbc:postgresql://localhost:5432/<nazwa_bazy>
 - spring.datasource.username=<nazwa_użytkownika>
 - spring.datasource.driver-class-name=org.postgresql.Driver
 - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
 - rsa.private-key=classpath:certs/private.pem (klucz prywatny HS256)
 - rsa.public-key=classpath:certs/public.pem (klucz publiczny HS256)
 - spring.liquibase.change-log=classpath:/db/changelog/changelog.xml

Aby backend działał poprawnie należy utworzyć folder 'certs' w 'resources', następnie dodać klucz prywatny HS256 oraz klucz publiczny HS256

Aby uruchomić frontend (https://github.com/erykov1/sport-event-frontend) należy przejść do folderu z projektem i wpisać komendę 'npm run start'

## Uruchomienie testów jednostkowych
należy przejść do pakietu test, nacisnąć na niego prawym przyciskiem myszy i kliknąć opcję: "Run 'Tests' in 'groovy'" oraz mieć uruchomione narzędzie Docker

## Scenariusze testowe dla testera manualnego
| Test Case ID | Opis           | Kroki Testowe | Oczekiwany Wynik |
|--------------|----------------|---------------|-------------------|
| TC-001       | Rejestracja do systemu | Przejdź do zakładki 'Załóż konto', wypełnij w formularzu wszystkie pola, kliknij przycisk 'Utwórz konto' | Nastąpi przekierowanie na stronę główną |
| TC-002       | Logowanie się do systemu | Przejdź do zakładki 'Zaloguj się', wypełnij w formularzu wszystkie pola, kliknij przycisk 'Zaloguj się' | Nastąpi przekierowanie na stronę główną |
| TC-003       | Wylogowanie się z systemu | Naciśnij ikonę odpowiadającą za wylogowanie się | Nastąpi przekierowanie na stronę główną, nie będzie dostępnych funkcjonalności użytkownika zalogowanego |
| TC-004       | Przeglądanie dostępnych wydarzeń | Zaloguj się, przejdź do zakładki 'Typy wydarzeń', wybierz jeden z typów | Na stronie pokażą się wszystkie dostępne wydarzenia |
| TC-005       | Utworzenie zgłoszenia | Zaloguj się, przejdź do zakładki 'Typy wydarzeń', wybierz jeden z typów, wybierz wydarzenie, kliknij 'Zgłoś uczestnictwo' | Na ekranie pojawi się informacja o utworzeniu zgłoszenia |
| TC-006       | Przeglądanie własnych zgłoszeń | Zaloguj się, Przejdź do zakładki 'Zgłoszenia', przejdź do zakładki 'Moje zgłoszenia' | Na ekranie pojawią się wszystkie zgłoszenia użytkownika |
| TC-007       | Przeglądanie własnych zgłoszeń wg. statusu | Zaloguj się, Przejdź do zakładki 'Zgłoszenia', przejdź do zakładki 'Moje zgłoszenia', wybierz status (Oczekujące, Potwierdzone, Odrzucone) | Na ekranie pojawią się wszystkie zgłoszenia użytkownika wg. statusu|
| TC-008       | Utworzenie wydarzenia sportowego | Zaloguj się jako administrator, przejdź do zakładki 'Wydarzenia', przejdź do zakładki 'Utwórz wydarzenie', wypełnij wszystkie pola w formularzu, kliknij przycisk 'Utwórz wydarzenie' | Nastąpi przekierowanie na stronę główną|
| TC-009       | Przeglądanie wszystkich zgłoszeń | Zaloguj się jako administrator, przejdź do zakładki 'Zgłoszenia', wybierz 'Przeglądaj zgłoszenia' | Pokażą się wszystkie zgłoszenia użytkowników |
| TC-010       | Usunięcie zgłoszenia | Zaloguj się, przejdź do zakładki 'Zgłoszenia', przejdź do zakładki 'Moje zgłoszenia', przy wybranym wydarzeniu, które ma status 'Oczekujące' kliknij ikonę usuwania | Zgłoszenie zniknie z listy zgłoszeń |
| TC-011       | Błędne dane przy ponownym wpisywaniu hasła podczas rejestracji | Przejdź do zakładki 'Załóż konto', wypełnij w formularzu wszystkie pola, w polu 'Powtórz Hasło' wpisz wartość inną niż jest w polu 'Hasło' | Na ekranie pojawi się informacja o nieprawidłowych danych|
| TC-012       | Próba utworzenia konta z nazwą użytkownika, który już istnieje | Przejdź do zakładki 'Załóż konto', wypełnij w formularzu wszystkie pola, w polu 'Podaj login' wpisz nazwę użytkownika, która już jest w bazie danych | Nie utworzy się konto, nie nastąpi przekierowanie do strony głównej |
| TC-013       | Próba dostępu do ścieżki administratora | Zaloguj się jako użytkownik, podaj ścieżkę, która jest dostępna dla administratora | Pokażą się wszystkie zgłoszenia użytkowników | Brak przekierowania do żądanej ścieżki
| TC-014       | Próba dostępu do ścieżki użytkownika | podaj ścieżkę, która jest dostępna dla użytkownika | Nastąpi przekierowanie do ekranu logowania |
| TC-015       | Próba utworzenia adresu wydarzenia bez podania wszystkich danych | Zaloguj się jako administrator, przejdź do zakładki 'Dodaj wydarzenie', nie wypełniaj wszystkich pól, kliknij 'Potwierdź adres' | Pojawi się informacja 'Wypełnij wszystkie pola' |
| TC-016       | Próba utworzenia wydarzenia bez podania wszystkich danych | Zaloguj się jako administrator, przejdź do zakładki 'Dodaj wydarzenie', wypełnij formularz tworzenia adresu, nie wypełniaj wszystkich pól w formularzu tworzenia wydarzenia, kliknij 'Potwierdź adres' | Wydarzenie sportowe nie zostanie utworzone, nie nastąpi przejście na stronę główną |
| TC-017       | Przeglądanie szczegółów wydarzeń | Zaloguj się jako użytkownik, przejdź do zakładki 'Typy wydarzeń', wybierz jeden z typów, wybierz wydarzenie | Pojawią się szczegóły wydarzenia |
| TC-018       | Przeglądanie dostępnych typów wydarzeń | Zaloguj się jako użytkownik, przejdź do zakładki 'Typy wydarzeń' | Pojawią się wszystkie dostępne typy wydarzeń |
| TC-019       | Próba przejścia do ścieżki, która nie jest zdefiniowana | Wpisz ścieżkę, która nie jest zdefiniowana | Nastąpi przekierowanie na stronę główną |
| TC-020       | Zmiana wyglądu navbar po zalogowaniu się | Zaloguj się | wygląd paska oraz jego zakładki zmienią się wg roli użytkownika |

## Technologie użyte w projekcie
- Java 17,
- Maven 3,
- Spring boot,
- PostgreSQL,
- React,
- CSS,
- Bootstrap,
- Docker,
- Swagger.io,
- Liquibase
