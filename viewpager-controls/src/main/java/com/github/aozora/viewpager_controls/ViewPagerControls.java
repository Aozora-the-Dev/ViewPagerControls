package com.github.aozora.viewpager_controls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Locale;

@SuppressLint("ViewConstructor")
public class ViewPagerControls extends ConstraintLayout {
	private ViewPager2 viewPager2;
	private TextView currentPageTextView;
	private SeekBar seekBar;
	private TextView totalPagesTextView;
	private RecyclerView.Adapter<? extends RecyclerView.ViewHolder> viewpagerAdapter;

	public ViewPagerControls(@NonNull Context context) {
		super(context);
		initialize(context);
	}

	public ViewPagerControls(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		initialize(context);
	}

	public ViewPagerControls(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize(context);
	}

	public ViewPagerControls(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		initialize(context);
	}

	public void setAdapter(RecyclerView.Adapter<?extends RecyclerView.ViewHolder> adapter) {
		viewpagerAdapter = adapter;
		totalPagesTextView.setText(viewpagerAdapter.getItemCount());

		viewPager2.setAdapter(viewpagerAdapter);
		seekBar.setMax(viewpagerAdapter.getItemCount());
	}

	private void initialize(Context context) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.viewpager_controls, this);

		viewPager2 = findViewById(R.id.viewpager2);
		currentPageTextView = findViewById(R.id.current_page_textview);
		seekBar = findViewById(R.id.seekBar);
		totalPagesTextView = findViewById(R.id.total_pages_textview);
		totalPagesTextView.setText("1");

		viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				super.onPageScrolled(position, positionOffset, positionOffsetPixels);
				currentPageTextView.setText(String.format(Locale.ENGLISH, "%d", position));
				seekBar.setProgress(position);
			}
		});

		seekBar.setMax(1);

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int position, boolean b) {
				currentPageTextView.setText(String.format(Locale.ENGLISH, "%d", position));
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
