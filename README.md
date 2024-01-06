# Kurs
## Testowanie i Jakość Oprogramowania / Projekt

# Autor 
## Aleksandra Piątek 34316, Eryk Marnik 34307

# Temat projektu
## Aplikacja do organizacji wydarzeń sportowych

# Opis projektu
"Aplikacja do Organizacji Wydarzeń Sportowych" to wszechstronne
narzędzie stworzone z myślą o zapewnieniu kompleksowego wsparcia
organizatorowi eventów sportowych. Dzięki zestawowi zaawansowanych funkcji,
ułatwia planowanie, zarządzanie wydarzeniami sportowymi. Oto główne
aspekty tej aplikacji: Rejestracja Uczestników: Aplikacja umożliwia łatwą rejestrację
uczestników. Uczestnicy mogą dokonywać rejestracji online, dostarczając
niezbędne informacje. Tworzenie i Zarządzanie Wydarzeniami: Organizator może
tworzyć nowe wydarzenia sportowe, określając daty, miejsce, opis, kategorie.
System pozwala również na monitorowanie dostępności miejsc oraz śledzenie liczby
zarejestrowanych uczestników. Zarządzanie Uczestnikami: Aplikacja oferuje
narzędzia do zarządzania uczestnikami, takie jak tworzenie listy uczestników.
Zarządzanie Harmonogramem: Aplikacja umożliwia tworzenie i zarządzanie
harmonogramem wydarzenia, dzięki czemu uczestnicy mają dostęp do
informacji na temat danego wydarzenia.

## Uruchomienie projektu
Należy dodać zadanie maven-owe z następujący zmiennymi:
 - spring.datasource.password=<hasło>
 - spring.datasource.url=jdbc:postgresql://localhost:5432/<nazwa_bazy>
 - spring.datasource.username=<nazwa_użytkownika>
 - spring.datasource.driver-class-name=org.postgresql.Driver
 - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
 - rsa.private-key=classpath:certs/private.pem (klucz prywatny HS256)
 - rsa.public-key=classpath:certs/public.pem (klucz publiczny HS256)
 - spring.liquibase.change-log=classpath:/db/changelog/changelog.xml

## Uruchomienie testów jednostkowych
należy przejść do pakietu test, nacisnąć na niego prawym przyciskiem myszy i kliknąć opcję: "Run 'Tests' in 'groovy'"

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
| TC-020       | Zmiana wyglądu navbar po zalogowaniu się | Zaloguj się jako użytkownik | wygląd paska oraz jego zakładki zmienią się wg roli użytkownika |

## Technologie użyte w projekcie
- Java 17,
- Maven 3,
- Spring boot,
- PostgreSQL,
- React,
- CSS,
- Bootstrap
