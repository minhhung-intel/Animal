package funix.prm.animal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import funix.prm.animal.R;
import funix.prm.animal.model.Animal;

public class DetailPageAdapter extends PagerAdapter {
    private final List<Animal> listAnimals;
    private final Context mContext;

    public DetailPageAdapter(List<Animal> listAnimals, Context mContext) {
        this.listAnimals = listAnimals;
        this.mContext = mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail, container, false);
        Animal item = listAnimals.get(position);

        ImageView ivPhotoBg = view.findViewById(R.id.iv_photo_bg);
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvContent = view.findViewById(R.id.tv_content);

        ivPhotoBg.setImageBitmap(item.getPhotoBg());
        tvName.setText(item.getName());
        tvContent.setText(item.getContent());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return listAnimals.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
