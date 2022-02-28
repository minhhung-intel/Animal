package funix.prm.animal.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import funix.prm.animal.R;
import funix.prm.animal.fragment.MenuFragment;
import funix.prm.animal.model.Animal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    public void initViews() {
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ln_main, menuFragment, null).commit();
        menuFragment.setListAnimals(list());
    }

    public ArrayList<Animal> list() {
        ArrayList<Animal> listAnimals = new ArrayList<>();
        int[] animalGroup = {R.string.group_bird, R.string.group_mammal, R.string.group_sea};
        String[] files;
        try {
            for (int id : animalGroup) {
                String folder = getString(id);
                //Duyệt qua tất cả các file trong folder và gán tất cả tên file vào string array files
                files = getApplicationContext().getAssets().list(folder);

                for (String fileName : files) {
                    //tên file có cấu trúc là ic_[name].png
                    //Vì vậy  sẽ lấy subString từ index 3 đến index của phần tử dấu .name
                    String name = fileName.substring(3, fileName.indexOf("."));

                    //tạo path đến vị trí lưu file
                    String path = folder + "/" + fileName;

                    //input file photo
                    Bitmap photo = BitmapFactory.decodeStream(getApplicationContext().getAssets().open(path));

                    //input file photoBg
                    String pathPhotoBg = "detail/photo/" + name + ".jpg";
                    Bitmap photoBg = BitmapFactory.decodeStream(getApplicationContext().getAssets().open(pathPhotoBg));

                    //input detail content từ file .txt trong thư mục assets/detail/text/...
                    InputStream input = getApplicationContext().getAssets().open("detail/text/" + name + ".txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
                    StringBuilder content = new StringBuilder();

                    String line;
                    while ((line = br.readLine()) != null) {
                        content.append(line).append("\n");
                    }

                    boolean isLove = getApplicationContext().getSharedPreferences("file_savef", Context.MODE_PRIVATE).getBoolean(path + "/" + photo, false);

                    listAnimals.add(new Animal(path, name, photo, photoBg, content.toString()));
                    br.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAnimals;
    }
}