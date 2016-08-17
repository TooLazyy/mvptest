package ru.wearemad.mvptest.Core.mvp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ru.wearemad.mvptest.Constants;
import ru.wearemad.mvptest.R;
import ru.wearemad.mvptest.myApplication;

/**
 * Created by Artem on 06.07.2016.
 */
public class MainListViewAdapter extends SimpleAdapter {

    private ViewHolder holder;
    private Context context;
    @Inject
    public Picasso picasso;
    private LayoutInflater inflater;
    private List<? extends Map<String, ?>> data;

    public MainListViewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        myApplication.getPicassoComponent().inject(this);
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        Map item = new HashMap(data.get(position));
        if(convertView == null) {
            holder = new ViewHolder();
            v = inflater.inflate(R.layout.main_list_item,null);
            holder.image = (ImageView) v.findViewById(R.id.ivImg);
            holder.text = (TextView) v.findViewById(R.id.tvText);
            v.setTag(holder);
        } else {
            v = convertView;
            holder = (ViewHolder) v.getTag();
        }

        holder.text.setText(item.get(Constants.ATTR_TITLE).toString());
        List<String> photos = (List<String>) item.get(Constants.ATTR_IMAGE);
        if(photos.isEmpty()) {
            holder.image.setImageResource(R.drawable.holder);
        } else {
            picasso
                    .load(photos.get(0))
                    .error(R.drawable.holder)
                    .into(holder.image);
        }
        return v;
    }

    static class ViewHolder {
        TextView text;
        ImageView image;
    }
}
