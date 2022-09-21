package io.github.hyuck9.hanimani.presentation.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import dagger.hilt.android.AndroidEntryPoint
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.databinding.ActivityListBinding
import io.github.hyuck9.hanimani.presentation.BaseActivity
import io.github.hyuck9.hanimani.presentation.view.ToDoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
internal class ListActivity : BaseActivity<ListViewModel>(), CoroutineScope {

	override val coroutineContext: CoroutineContext
		get() = Dispatchers.Main + Job()

	private lateinit var binding: ActivityListBinding

	private val adapter = ToDoAdapter()

	override val viewModel by viewModels<ListViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityListBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}

	private fun initViews(binding: ActivityListBinding) = with(binding) {
		recyclerView.adapter = adapter

		refreshLayout.setOnRefreshListener {
			viewModel.fetchData()
		}

		addToDoButton.setOnClickListener {
			// TODO: DetailActivity 구현
			/*startActivityForResult(
				DetailActivity.getIntent(this@ListActivity, it.id, DetailMode.WRITE),
				DetailActivity.FETCH_REQUEST_CODE
			)*/
		}
	}

	override fun observeData() {
		viewModel.toDoListLiveData.observe(this) {
			when (it) {
				is ToDoListState.UnInitialized -> {
					initViews(binding)
				}
				is ToDoListState.Loading -> {
					handleLoadingState()
				}
				is ToDoListState.Success -> {
					handleSuccessState(it)
				}
				is ToDoListState.Error -> {
					handleErrorState()
				}
			}
		}
	}

	private fun handleLoadingState() = with(binding) {
		refreshLayout.isRefreshing = true
	}

	private fun handleSuccessState(state: ToDoListState.Success) = with(binding) {
		refreshLayout.isEnabled = state.toDoList.isNotEmpty()
		refreshLayout.isRefreshing = false

		if (state.toDoList.isEmpty()) {
			emptyResultTextView.isGone = false
			recyclerView.isGone = true
		} else {
			emptyResultTextView.isGone = true
			recyclerView.isGone = false
			adapter.setToDoList(
				state.toDoList,
				toDoItemClickListener = {
					// TODO: DetailActivity 구현
					/*startActivityForResult(
						DetailActivity.getIntent(this@ListActivity, it.id, DetailMode.DETAIL),
						DetailActivity.FETCH_REQUEST_CODE
					)*/
				}, toDoCheckListener = {
					viewModel.updateEntity(it)
				}
			)
		}
	}
	
	private fun handleErrorState() {
		Toast.makeText(this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when(item.itemId) {
			R.id.action_delete_all -> {
				viewModel.deleteAll()
				true
			}
			else -> {
				super.onOptionsItemSelected(item)
			}
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.list_menu, menu)
		return true
	}

}