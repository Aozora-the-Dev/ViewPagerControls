package com.github.aozora.viewpager_controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerControls extends ConstraintLayout {
	private ViewPager2 viewPager2;
	private TextView currentPageTextView;
	private SeekBar seekBar;
	private TextView totalPagesTextView;
	private RecyclerView.Adapter viewpagerAdapter;

	public ViewPagerControls(@NonNull Context context, RecyclerView.Adapter<RecyclerView.ViewHolder> viewpagerAdapter) {
		super(context);
		this.viewpagerAdapter = viewpagerAdapter;
		initialize(context);
	}

	private void initialize(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.viewpager_controls, this);

		viewPager2 = (ViewPager2) findViewById(R.id.viewpager2);
		currentPageTextView = (TextView) findViewById(R.id.current_page_textview);
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		totalPagesTextView = (TextView) findViewById(R.id.total_pages_textview);

		viewPager2.setAdapter(viewpagerAdapter);
		viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				super.onPageScrolled(position, positionOffset, positionOffsetPixels);
				currentPageTextView.setText(Integer.toString(position));
				seekBar.setProgress(position);
			}
		});

		seekBar.setMax(viewpagerAdapter.getItemCount());
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int position, boolean b) {
				currentPageTextView.setText(Integer.toString(position));
				viewPager2.setCurrentItem(position, true);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// We do not need to implement this method
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// We do not need to implement this method
			}
		});
	}
}
