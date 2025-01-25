package com.pupilmed.fitnesse;

import com.pupilmed.pupilmedapi.model.Visit;
import com.pupilmed.pupilmedapi.service.filterStrategy.DateRangeVisitFilterStrategy;
import fit.ColumnFixture;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateRangeVisitFilterFixture extends ColumnFixture {
    private String startDate; // Przechowuje datę jako String (dla FitNesse)
    private String endDate;   // Przechowuje datę jako String (dla FitNesse)
    private int filteredVisits; // Liczba przefiltrowanych wizyt
    private final List<Visit> visits;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Konstruktor inicjalizujący przykładowe wizyty
    public DateRangeVisitFilterFixture() {
        this.visits = new ArrayList<>();
        visits.add(createVisit(1, "Konsultacja", LocalDate.of(2025, 1, 1), 100, 1, 1, null));
        visits.add(createVisit(2, "Kontrola", LocalDate.of(2025, 1, 10), 200, 1, 2, null));
        visits.add(createVisit(3, "Operacja", LocalDate.of(2025, 2, 1), 300, 2, 3, 1));
    }

    // Setter dla startDate
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    // Getter dla startDate
    public String getStartDate() {
        return this.startDate;
    }

    // Setter dla endDate
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // Getter dla endDate
    public String getEndDate() {
        return this.endDate;
    }

    // Getter dla filteredVisits (wartość zwracana do FitNesse)
    public int getFilteredVisits() {
        return this.filteredVisits;
    }

    // Metoda execute wywoływana automatycznie przez FitNesse
    public void execute() {
        try {
            // Walidacja i parsowanie dat
            validateDates();

            LocalDate parsedStartDate = parseLocalDate(this.startDate);
            LocalDate parsedEndDate = parseLocalDate(this.endDate);

            // Filtrowanie wizyt
            DateRangeVisitFilterStrategy strategy = new DateRangeVisitFilterStrategy();
            List<Visit> filtered = strategy.filterVisits(visits, List.of(parsedStartDate, parsedEndDate));
            this.filteredVisits = filtered.size();
        } catch (IllegalArgumentException | DateTimeParseException e) {
            // Jeśli wystąpi błąd, ustawiamy `filteredVisits` na 0 i wypisujemy błąd w logach
            System.err.println("Error processing dates: " + e.getMessage());
            this.filteredVisits = 0; // Domyślna wartość w przypadku błędu
        }
    }

    // Publiczna metoda do walidacji dat dla FitNesse
    public String validateDates() {
        try {
            if (startDate == null || startDate.isEmpty()) {
                throw new IllegalArgumentException("StartDate must not be null or empty");
            }
            if (endDate == null || endDate.isEmpty()) {
                throw new IllegalArgumentException("EndDate must not be null or empty");
            }

            LocalDate parsedStartDate = parseLocalDate(this.startDate);
            LocalDate parsedEndDate = parseLocalDate(this.endDate);

            if (parsedStartDate.isAfter(parsedEndDate)) {
                throw new IllegalArgumentException("StartDate must not be after EndDate");
            }
            return "Validation successful";
        } catch (IllegalArgumentException | DateTimeParseException e) {
            return "Validation failed: " + e.getMessage();
        }
    }

    // Metoda pomocnicza do konwersji dat
    private static LocalDate parseLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    // Pomocnicza metoda do tworzenia przykładowych wizyt
    private Visit createVisit(int id, String typWizyty, LocalDate data, int cena, int weterynarzid, int zwierzeid, Integer zalecenieid) {
        Visit visit = new Visit();
        visit.setId(id);
        visit.setTypWizyty(typWizyty);
        visit.setData(data);
        visit.setCena(cena);
        visit.setWeterynarzid(weterynarzid);
        visit.setZwierzeid(zwierzeid);
        visit.setZalecenieid(zalecenieid);
        return visit;
    }
}
