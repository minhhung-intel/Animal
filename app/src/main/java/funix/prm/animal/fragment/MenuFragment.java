package funix.prm.animal.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import funix.prm.animal.R;
import funix.prm.animal.activity.MainActivity;
import funix.prm.animal.adapter.AnimalAdapter;
import funix.prm.animal.model.Animal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    private Context mContext;
    private RecyclerView rvAnimal;
    private List<Animal> listAnimals;
    private DrawerLayout mDrawer;

    public void setListAnimals(ArrayList<Animal> listAnimals) {
        this.listAnimals = listAnimals;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initView(View view) {
        mDrawer = view.findViewById(R.id.drawer);
        rvAnimal = view.findViewById(R.id.rv_animals);
        //Xử lý mở menu trái
        view.findViewById(R.id.iv_menu).setOnClickListener(v1 -> mDrawer.openDrawer(GravityCompat.START));
        //Hiển thị ảnh động vật biển
        view.findViewById(R.id.iv_sea).setOnClickListener(v1 -> {
            view.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
                showAnimals("sea");
        });
        //Hiển thị ảnh động vật có vú
        view.findViewById(R.id.iv_mammal).setOnClickListener(v1 -> {
            view.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
                showAnimals("mammal");
        });
        //Hiển thị ảnh chim muông
        view.findViewById(R.id.iv_bird).setOnClickListener(v1 -> {
            view.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
                showAnimals("bird");
        });
        //Hiển thị danh sách ảnh lên RecyclerView

    }

    private void showAnimals(String animalType) {
        ArrayList<Animal> listShow = new ArrayList<>();
        //Học sinh tự xử lý để xây dựng được danh sách Animal tương ứng dựa vào animalType
        for (Animal animal : listAnimals) {
            if (animal.getPath().contains(animalType)) { listShow.add(animal); }
        }
        //Hiển thị danh sách ảnh lên RecyclerView
        AnimalAdapter animalAdapter = new AnimalAdapter(listShow, mContext);
        rvAnimal.setAdapter(animalAdapter);
        mDrawer.closeDrawers();
    }
}
