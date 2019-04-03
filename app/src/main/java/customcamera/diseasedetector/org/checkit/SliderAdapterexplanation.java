package customcamera.diseasedetector.org.checkit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapterexplanation extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapterexplanation(Context context){
        this.context = context;
    }

    //Arrays
    //Arrays
    public int[] uptopic = {
            R.string.first,
            R.string.third,
            R.string.fifth

    };

    public int[] uptopic2 = {
            R.string.second,
            R.string.fourth,
            R.string.sixth

    };
    public int[] slide_images = {
            R.drawable.page1,
            R.drawable.page3,
            R.drawable.page6
    };

    public int[] slide_images2 = {
            R.drawable.page2,
            R.drawable.page5,
            R.drawable.page4
    };


    @Override
    public int getCount() {
        return uptopic.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_explaination, container, false);


        TextView topic = (TextView) view.findViewById(R.id.topic);
        TextView topic2 = (TextView) view.findViewById(R.id.topic2);
        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        ImageView slideImageView2 = (ImageView) view.findViewById(R.id.slide_image2);

        topic.setText(uptopic[position]);
        topic2.setText(uptopic2[position]);
        slideImageView.setImageResource(slide_images[position]);
        slideImageView2.setImageResource(slide_images2[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
