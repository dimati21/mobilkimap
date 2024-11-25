package g413.ladvinskiy.lab06;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    MapView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mv = findViewById(R.id.abc);
        mv.ctx = this;
    }

    public void ZoomOut(View v){
        if (mv.current_level_index == 0) return;
        mv.offset_x += mv.width / 2.0f;
        mv.offset_y += mv.height / 2.0f;
        mv.offset_x /= 2.0f;
        mv.offset_y /= 2.0f;
        mv.current_level_index--;
        mv.invalidate();

    }
    public void ZoomIn(View v){
        if (mv.current_level_index == mv.levels.length - 1) return;
        mv.offset_x -= mv.width / 2.0f;
        mv.offset_y -= mv.height / 2.0f;
        mv.offset_x *= 2.0f;
        mv.offset_y *= 2.0f;
        mv.current_level_index++;
        mv.invalidate();

    }
}