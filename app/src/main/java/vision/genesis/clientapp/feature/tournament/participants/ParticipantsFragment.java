package vision.genesis.clientapp.feature.tournament.participants;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.swagger.client.model.ParticipantViewModel;
import rx.Subscription;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.feature.BaseFragment;
import vision.genesis.clientapp.ui.ToolbarView;

/**
 * GenesisVision
 * Created by Vitaly on 2/8/18.
 */

public class ParticipantsFragment extends BaseFragment implements ParticipantsView
{
	@BindView(R.id.toolbar)
	public ToolbarView toolbar;

	@BindView(R.id.refresh_layout)
	public SwipeRefreshLayout refreshLayout;

	@BindView(R.id.recycler_view)
	public RecyclerView recyclerView;

	@BindView(R.id.group_no_internet)
	public ViewGroup noInternetGroup;

	@BindView(R.id.group_tournament_not_started)
	public ViewGroup tournamentNotStartedGroup;

	@BindView(R.id.group_empty)
	public ViewGroup emptyGroup;

	@BindView(R.id.button_try_again)
	public View tryAgainButton;

	@BindView(R.id.progress_bar)
	public ProgressBar progressBar;

	@BindView(R.id.fab)
	public FloatingActionButton fab;

	@BindView(R.id.group_search)
	public ViewGroup searchGroup;

	@BindView(R.id.edittext_search)
	public EditText searchEditText;

	@InjectPresenter
	ParticipantsPresenter participantsPresenter;

	private ParticipantsListAdapter participantsListAdapter;

	private Subscription textChangeSubscription;

	private boolean fabInAnim = false;

	private int lastVisible = 0;

	@OnClick(R.id.button_try_again)
	public void onTryAgainClicked() {
		participantsPresenter.onTryAgainClicked();
	}

	@OnClick(R.id.button_visit)
	public void onVisitClicked() {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.genesis.vision"));
		startActivity(browserIntent);
	}

	@OnClick(R.id.button_search_close)
	public void onSearchCloseClicked() {
		participantsPresenter.onSearchCloseClicked();
	}

	@OnClick(R.id.fab)
	public void onFabClicked() {
		if (lastVisible < 20)
			recyclerView.smoothScrollToPosition(0);
		else
			recyclerView.scrollToPosition(0);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_participants, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ButterKnife.bind(this, view);

		initToolbar();

		refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary),
				ContextCompat.getColor(getContext(), R.color.colorAccent),
				ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
		refreshLayout.setOnRefreshListener(() -> participantsPresenter.onSwipeRefresh());
		initRecyclerView();
	}

	@Override
	public void onStart() {
		super.onStart();
		setSearchTextListener();
	}

	@Override
	public void onStop() {
		super.onStop();
		if (textChangeSubscription != null)
			textChangeSubscription.unsubscribe();
		hideSoftKeyboard();
	}

	private void initToolbar() {
		toolbar.setTitle(getString(R.string.tournament));
		toolbar.addRightButton(R.drawable.ic_search_black_24dp, () -> participantsPresenter.onSearchClicked());
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
		participantsListAdapter = new ParticipantsListAdapter();
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
		dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_item_divider));
		recyclerView.addItemDecoration(dividerItemDecoration);
		recyclerView.setAdapter(participantsListAdapter);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				checkIfLastItemVisible();
			}
		});
	}

	private void checkIfLastItemVisible() {
		LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
		int totalItemCount = layoutManager.getItemCount();
		lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

		boolean endHasBeenReached = lastVisible + 2 >= totalItemCount;
		if (totalItemCount > 0 && endHasBeenReached) {
			participantsPresenter.onLastListItemVisible();
		}
		if (!fabInAnim && fab.getVisibility() != View.VISIBLE && lastVisible > 3)
			showFab();
		else if (!fabInAnim && fab.getVisibility() == View.VISIBLE && lastVisible < 3)
			hideFab();
	}

	private void setSearchTextListener() {
		textChangeSubscription = RxTextView.textChanges(searchEditText)
				.subscribe(text -> participantsPresenter.onSearchTextChanged(text.toString()));
	}

	private void showFab() {
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_from_bottom);
		animation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
				fabInAnim = true;
				fab.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				fabInAnim = false;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		fab.startAnimation(animation);
	}

	private void hideFab() {
		Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_to_bottom);
		animation.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation) {
				fabInAnim = true;
				fab.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				fabInAnim = false;
				fab.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		fab.startAnimation(animation);
	}

	private void showSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.requestFocus();
		imm.showSoftInput(searchEditText, 0);
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		searchEditText.clearFocus();
		imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
	}

	@Override
	public void setParticipantsCount(int count) {
		toolbar.setSubtitle(String.format(Locale.getDefault(), "%d %s", count, getResources().getString(R.string.participants)));
	}

	@Override
	public void setParticipants(List<ParticipantViewModel> participants) {
		participantsListAdapter.setParticipants(participants);
	}

	@Override
	public void addParticipants(List<ParticipantViewModel> participants) {
		participantsListAdapter.addParticipants(participants);
	}

	@Override
	public void scrollListTo(int position) {
		recyclerView.scrollToPosition(position);
	}

	@Override
	public void setRefreshing(boolean refreshing) {
		refreshLayout.setRefreshing(refreshing);
	}

	@Override
	public void showSnackbarMessage(String message) {
		showSnackbar(message, toolbar);
	}

	@Override
	public void showNoInternet(boolean show) {
		noInternetGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		refreshLayout.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showProgressBar(boolean show) {
		progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
		tryAgainButton.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showTournamentNotStarted(boolean show) {
		tournamentNotStartedGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public void showSearch(boolean show) {
		searchGroup.setVisibility(show ? View.VISIBLE : View.GONE);

		if (show) {
			showSoftKeyboard();
		}
		else {
			hideSoftKeyboard();
			searchEditText.setText("");
		}
	}

	@Override
	public void showEmptyResults(boolean show) {
		emptyGroup.setVisibility(show ? View.VISIBLE : View.GONE);
		recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
	}

	@Override
	public boolean onBackPressed() {
//		LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
//		if (layoutManager.findFirstCompletelyVisibleItemPosition() > 0) {
//			recyclerView.smoothScrollToPosition(0);
//			return true;
//		}
//		else
		return false;
	}
}
