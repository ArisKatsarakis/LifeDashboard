package gr.ariskatsarakis.lifedashboard.samples;

import gr.ariskatsarakis.lifedashboard.budget.def.Entry;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntrySamples {

    public  static  List<Entry>  Sample_Entries() {
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(createRandomExpenseEntry());
        entries.add(createRandomIncomeEntry());
        entries.add(createRandomIncomeEntryInThePast());
        return entries;
    }

     public static Entry createRandomExpenseEntry() {
        Entry entry = new Entry();
        entry.setEntryId(1L);
        entry.setExpenseId(1L);
         entry.setMoney(BigDecimal.TEN);
         entry.setDateInserted(Timestamp.valueOf(LocalDateTime.now()));
        return entry;

    }

    public static Entry createRandomIncomeEntry() {
        Entry entry = new Entry();
        entry.setEntryId(1L);
        entry.setIncomeId(1L);
        entry.setMoney(BigDecimal.ONE);

        entry.setDateInserted(Timestamp.valueOf(LocalDateTime.now()));
        return entry;

    }

    public static Entry createRandomIncomeEntryInThePast() {
        Entry entry = new Entry();
        entry.setEntryId(1L);
        entry.setIncomeId(1L);
        entry.setMoney(BigDecimal.ONE);

        entry.setDateInserted(Timestamp.valueOf(LocalDateTime.now().minusDays(1l)));
        return entry;
    }
}
