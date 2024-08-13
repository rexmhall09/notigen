package notigen.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.concurrent.atomic.AtomicInteger;

public class main extends AppCompatActivity {
    EditText textt;
    EditText titlet;
    Spinner iconSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationChannel channel = new NotificationChannel("Sender Notifications", "Sender Notifications", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        textt = findViewById(R.id.textt);
        titlet = findViewById(R.id.titlet);
        iconSpinner = findViewById(R.id.icon_spinner);

        // Populate the Spinner with icon choices
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.icon_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        iconSpinner.setAdapter(adapter);
    }

    public static class NotificationID {
        private final static AtomicInteger c = new AtomicInteger(0);

        public static int getID() {
            return c.incrementAndGet();
        }
    }

    public void onClick(View view) {
        textt = findViewById(R.id.textt);
        titlet = findViewById(R.id.titlet);

        // Get selected icon from Spinner
        int selectedIcon = getSelectedIcon();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                main.this, "Sender Notifications"
        );
        builder.setSmallIcon(selectedIcon);
        builder.setContentTitle(titlet.getText().toString());
        builder.setContentText(textt.getText().toString());
        builder.setAutoCancel(false);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(main.this);
        managerCompat.notify(NotificationID.getID(), builder.build());
    }

    private int getSelectedIcon() {
        String selected = iconSpinner.getSelectedItem().toString();
        switch (selected) {
            case "Message Bubble":
                return R.drawable.ic_message_bubble;
            case "Notification Bell":
                return R.drawable.ic_notification_bell;
            case "Dollar Sign":
                return R.drawable.ic_dollar_sign;
            default:
                return R.drawable.ic_message; // Default icon
        }
    }
}
