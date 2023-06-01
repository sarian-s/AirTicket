import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AviaSoulsTest {
    @Test
    public void compareTo() {
        Ticket ticket1 = new Ticket("адлер", "москва", 1200, 5, 10);
        Ticket ticket2 = new Ticket("ростов", "москва", 2200, 8, 18);
        Ticket ticket3 = new Ticket("краснодар", "москва", 2200, 8, 18);

        Assertions.assertEquals(ticket1.compareTo(ticket2) < 0, true);
        Assertions.assertEquals(ticket2.compareTo(ticket1) > 0, true);
        Assertions.assertEquals(ticket3.compareTo(ticket2), 0);
        //Assertions.assertEquals(ticket3.(ticket2), 0);
    }

    @Test
    public void search() {
        Ticket ticket1 = new Ticket("адлер", "москва", 1200, 5, 10);
        Ticket ticket2 = new Ticket("адлер", "москва", 4300, 5, 10);
        Ticket ticket3 = new Ticket("адлер", "москва", 800, 5, 10);
        Ticket ticket4 = new Ticket("ростов", "москва", 2200, 8, 18);
        Ticket ticket5 = new Ticket("краснодар", "москва", 2200, 8, 18);
        Ticket ticket6 = new Ticket("адлер", "москва", 200, 8, 18);

        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);
        Ticket[] expected = {ticket6, ticket3, ticket1, ticket2};
        Ticket[] actual = aviaSouls.search("адлер", "москва");
        Assertions.assertArrayEquals(expected, actual);
        Ticket[] expected1 = {};
        Ticket[] actual1 = aviaSouls.search("адлер", "новгород");
        Assertions.assertArrayEquals(expected1, actual1);
        Ticket[] expected2 = {ticket4};
        Ticket[] actual2 = aviaSouls.search("ростов", "москва");
        Assertions.assertArrayEquals(expected2, actual2);
    }

    @Test
    public void comparator() {
        Ticket ticket1 = new Ticket("адлер", "москва", 1200, 5, 10);
        Ticket ticket2 = new Ticket("ростов", "москва", 2200, 8, 18);
        Ticket ticket3 = new Ticket("краснодар", "москва", 2200, 8, 18);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Assertions.assertEquals(comparator.compare(ticket1, ticket2), -1);
        Assertions.assertEquals(comparator.compare(ticket2, ticket3), 0);
        Assertions.assertEquals(comparator.compare(ticket2, ticket1), 1);
    }

    @Test
    public void searchAndSortBy() {
        Ticket ticket1 = new Ticket("адлер", "москва", 1200, 1, 2);
        Ticket ticket2 = new Ticket("адлер", "москва", 4300, 1, 5);
        Ticket ticket3 = new Ticket("адлер", "москва", 800, 1, 4);
        Ticket ticket4 = new Ticket("ростов", "москва", 2200, 1, 3);
        Ticket ticket5 = new Ticket("краснодар", "москва", 2200, 1, 7);
        Ticket ticket6 = new Ticket("адлер", "москва", 200, 1, 9);

        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] expected = {ticket1, ticket3, ticket2, ticket6};
        Ticket[] actual = aviaSouls.searchAndSortBy("адлер", "москва", comparator);
        Assertions.assertArrayEquals(expected, actual);
        Ticket[] expected1 = {ticket4};
        Ticket[] actual1 = aviaSouls.searchAndSortBy("ростов", "москва", comparator);
        Assertions.assertArrayEquals(expected1, actual1);
        Ticket[] expected2 = {};
        Ticket[] actual2 = aviaSouls.searchAndSortBy("ростов", "новгород", comparator);
        Assertions.assertArrayEquals(expected2, actual2);
    }
}