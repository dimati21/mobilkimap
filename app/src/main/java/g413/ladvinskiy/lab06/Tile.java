package g413.ladvinskiy.lab06;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import org.json.JSONObject;

public class Tile {
    int scale, x, y;
    Bitmap bmp;

    public Tile(int x, int y, int scale, Activity ctx){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.bmp = null;

        ApiHelper req = new ApiHelper(ctx){
            @Override
            public void on_ready(String res){
                try {
                    JSONObject obj = new JSONObject(res);
                    String b64 = obj.getString("data");
                    byte[] jpeg = Base64.decode(b64, Base64.DEFAULT);
                    bmp = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };

        req.send("https://fxnode.ru/api/tilemap/raster/" + String.format("%d/%d-%d", scale, x, y));
    }
}
