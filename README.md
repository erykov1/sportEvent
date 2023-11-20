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
 - spring.jpa.hibernate.ddl-auto=create-drop lub update
 - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
 - spring.jpa.properties.hibernate.format_sql=true
 - spring.jpa.show-sql=true

## Uruchomienie testów jednostkowych
należy przejść do pakietu test, nacisnąć na niego prawym przyciskiem myszy i kliknąć opcję: "Run 'Tests' in 'groovy'"

## Scenariusze testowe dla testera manualnego
| Test Case ID | Opis           | Kroki Testowe | Oczekiwany Wynik |
|--------------|----------------|---------------|-------------------|
| TC-001       | Rejestracja do systemu | Przejdź do zakładki 'Załóż konto', wypełnij w formularzu wszystkie pola, kliknij przycisk 'Utwórz konto' | Nastąpi przekierowanie na stronę główną |
| TC-002       | Logowanie się do systemu | Przejdź do zakładki 'Zaloguj się', wypełnij w formularzu wszystkie pola, kliknij przycisk 'Zaloguj się' | Nastąpi przekierowanie na stronę główną |
| TC-003       | Wylogowanie się z systemu | Naciśnij ikonę odpowiadającą za wylogowanie się | Nastąpi przekierowanie na stronę główną, nie będzie dostępnych funkcjonalności użytkownika zalogowanego |
| TC-004       | Przeglądanie dostępnych wydarzeń | Zaloguj się, Przejdź do zakładki 'Wydarzenia' | Na stronie pokażą się wszystkie dostępne wydarzenia |
| TC-005       | Utworzenie zgłoszenia | Zaloguj się, Przejdź do zakładki 'Zgłoszenia', 'Utwórz zgłoszenie', wypełnij wszystkie pola w formularzu, kliknij przycisk 'Utwórz zgłoszenie' | Na ekranie pojawi się informacja o utworzeniu zgłoszenia |
| TC-006       | Przeglądanie własnych zgłoszeń | Zaloguj się, Przejdź do zakładki 'Zgłoszenia', przejdź do zakładki 'Moje zgłoszenia' | Na ekranie pojawią się wszystkie zgłoszenia użytkownika |
| TC-007       | Przeglądanie własnych zgłoszeń wg. statusu | Zaloguj się, Przejdź do zakładki 'Zgłoszenia', przejdź do zakładki 'Moje zgłoszenia', wybierz status (Oczekujące, Potwierdzone, Odrzucone) | Na ekranie pojawią się wszystkie zgłoszenia użytkownika wg. statusu|
| TC-008       | Utworzenie wydarzenia sportowego | Zaloguj się jako administrator, przejdź do zakładki 'Wydarzenia', przejdź do zakładki 'Utwórz wydarzenie', wypełnij wszystkie pola w formularzu, kliknij przycisk 'Utwórz wydarzenie' | Na ekranie pojawi się informacja o utworzeniu wydarzenia|
| TC-009       | Usunięcie wydarzenia sportowego | Zaloguj się jako administrator, przejdź do zakładki 'Wydarzenia', przy wybranym wydarzeniu kliknij ikonę usuwania | Wydarzenie zniknie z listy wydarzeń |
| TC-010       | Usunięcie zgłoszenia | Zaloguj się, przejdź do zakładki 'Zgłoszenia', przejdź do zakładki 'Moje zgłoszenia', przy wybranym wydarzeniu, które ma status 'Oczekujące' kliknij ikonę usuwania | Zgłoszenie zniknie z listy zgłoszeń |

## Technologie użyte w projekcie
- Java 17,
- Maven 3,
- Spring boot,
- PostgreSQL,
- React,
- CSS,
- Bootstrap
