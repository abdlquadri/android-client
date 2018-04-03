package vision.genesis.clientapp.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.utils.TypefaceUtil;

/**
 * GenesisVision
 * Created by Vitaly on 1/19/18.
 */

public class ToolbarView extends RelativeLayout
{
	public interface ButtonClickListener
	{
		void onClicked();
	}

	@BindView(R.id.title)
	public TextView title;

	@BindView(R.id.subtitle)
	public TextView subtitle;

	@BindView(R.id.button_left)
	public ImageView leftButton;

	@BindView(R.id.button_right)
	public ImageView rightButton;

	@BindView(R.id.button_right_dot)
	public View rightButtonDot;

	private ButtonClickListener leftButtonClickListener;

	private ButtonClickListener rightButtonClickListener;

	private Unbinder unbinder;

	public ToolbarView(Context context) {
		super(context);
		initView();
	}

	public ToolbarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ToolbarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	@OnClick(R.id.button_left)
	public void onLeftButtonClicked() {
		if (leftButtonClickListener != null) {
			new Handler().postDelayed(() -> leftButtonClickListener.onClicked(), 0);
		}
	}

	@OnClick(R.id.button_right)
	public void onRightButtonClicked() {
		if (rightButtonClickListener != null) {
			new Handler().postDelayed(() -> rightButtonClickListener.onClicked(), 0);
		}
	}

	private void initView() {
		inflate(getContext(), R.layout.view_toolbar, this);

		unbinder = ButterKnife.bind(this);

		setFonts();
	}

	private void setFonts() {
		title.setTypeface(TypefaceUtil.bold());
	}

	public void setWhite() {
		int whiteColor = ContextCompat.getColor(getContext(), R.color.white);
		title.setTextColor(whiteColor);
		subtitle.setTextColor(whiteColor);
		leftButton.setColorFilter(whiteColor);
		rightButton.setColorFilter(whiteColor);
	}

	public void setTitle(String text) {
		title.setText(text);
	}

	public void setSubtitle(String text) {
		subtitle.setText(text);
		subtitle.setVisibility(View.VISIBLE);
	}

	public void addLeftButton(int buttonImageResId, ButtonClickListener clickListener) {
		leftButton.setImageDrawable(ContextCompat.getDrawable(getContext(), buttonImageResId));
		leftButtonClickListener = clickListener;
		leftButton.setVisibility(View.VISIBLE);
	}

	public void addRightButton(int buttonImageResId, ButtonClickListener clickListener) {
		rightButton.setImageDrawable(ContextCompat.getDrawable(getContext(), buttonImageResId));
		rightButtonClickListener = clickListener;
		rightButton.setVisibility(View.VISIBLE);
	}

	public void setLeftButtonPadding(int padding) {
		leftButton.setPadding(padding, padding, padding, padding);
	}

	public void setRightButtonPadding(int padding) {
		rightButton.setPadding(padding, padding, padding, padding);
	}

	public void showRightButtonDot(boolean show) {
		rightButtonDot.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public void onDestroy() {
		leftButtonClickListener = null;
		rightButtonClickListener = null;

		if (unbinder != null)
			unbinder.unbind();
	}
}
