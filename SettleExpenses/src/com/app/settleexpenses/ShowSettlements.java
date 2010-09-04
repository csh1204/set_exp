package com.app.settleexpenses;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.app.settleexpenses.domain.Event;
import com.app.settleexpenses.domain.Settlement;

import java.util.List;

public class ShowSettlements extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_settlements);
        DbAdapter mDbHelper = new DbAdapter(this, new ContactsAdapter(this));
        Event event = mDbHelper.getEventById(getIntent().getLongExtra(DbAdapter.EVENT_ID, -1));
        List<Settlement> settlements = event.calculateSettlements();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.settlement);
        for (Settlement settlement : settlements) {
            Log.d("Settlement", settlement.payer().getId() + " pays $" + settlement.getAmount() + " to " + settlement.receiver().getId());
            arrayAdapter.add(settlement.payer().getName() + " pays $" + settlement.getAmount() + " to " + settlement.receiver().getName());
        }
        setListAdapter(arrayAdapter);
    }
}