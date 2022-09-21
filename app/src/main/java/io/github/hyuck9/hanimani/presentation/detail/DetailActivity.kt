package io.github.hyuck9.hanimani.presentation.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isGone
import dagger.hilt.android.AndroidEntryPoint
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.databinding.ActivityDetailBinding
import io.github.hyuck9.hanimani.databinding.ActivityListBinding
import io.github.hyuck9.hanimani.extensions.getSerializable
import io.github.hyuck9.hanimani.presentation.BaseActivity
import java.io.Serializable
import javax.inject.Inject

@AndroidEntryPoint
internal class DetailActivity : BaseActivity<DetailViewModel>() {

	@set:Inject
	internal lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory

	private lateinit var binding: ActivityDetailBinding

	override val viewModel: DetailViewModel by viewModels {
		DetailViewModel.provideFactory(
			detailViewModelFactory,
			intent.getSerializable(DETAIL_MODE_KEY, DetailMode::class.java),
			intent.getLongExtra(TODO_ID_KEY, -1)
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setResult(RESULT_OK)
	}

	override fun observeData() = viewModel.toDoDetailLiveData.observe(this) {
		when (it) {
			is ToDoDetailState.UnInitialized -> {
				initViews(binding)
			}
			is ToDoDetailState.Loading -> {
				handleLoadingState()
			}
			is ToDoDetailState.Success -> {
				handleSuccessState(it)
			}
			is ToDoDetailState.Modify -> {
				handleModifyState()
			}
			is ToDoDetailState.Delete -> {
				Toast.makeText(this, "성공적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show()
				finish()
			}
			is ToDoDetailState.Error -> {
				Toast.makeText(this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
				finish()
			}
			is ToDoDetailState.Write -> {
				handleWriteState()
			}
		}
	}

	private fun initViews(binding: ActivityDetailBinding) = with(binding) {
		titleInput.isEnabled = false
		descriptionInput.isEnabled = false

		deleteButton.isGone = true
		modifyButton.isGone = true
		updateButton.isGone = true

		deleteButton.setOnClickListener {
			viewModel.deleteToDo()
		}
		modifyButton.setOnClickListener {
			viewModel.setModifyMode()
		}
		updateButton.setOnClickListener {
			viewModel.writeToDo(
				title = titleInput.text.toString(),
				description = descriptionInput.text.toString()
			)
		}
	}

	private fun handleLoadingState() = with(binding) {
		progressBar.isGone = false
	}

	private fun handleModifyState() = with(binding) {
		titleInput.isEnabled = true
		descriptionInput.isEnabled = true

		deleteButton.isGone = true
		modifyButton.isGone = true
		updateButton.isGone = false
	}

	private fun handleWriteState() = with(binding) {
		titleInput.isEnabled = true
		descriptionInput.isEnabled = true

		updateButton.isGone = false
	}

	private fun handleSuccessState(state: ToDoDetailState.Success) = with(binding) {
		progressBar.isGone = true

		titleInput.isEnabled = false
		descriptionInput.isEnabled = false

		deleteButton.isGone = false
		modifyButton.isGone = false
		updateButton.isGone = true

		val toDoItem = state.toDoItem
		titleInput.setText(toDoItem.title)
		descriptionInput.setText(toDoItem.description)
	}


	companion object {
		const val TODO_ID_KEY = "ToDoId"
		const val DETAIL_MODE_KEY = "DetailMode"
		const val FETCH_REQUEST_CODE = 10

		fun getIntent(context: Context, detailMode: DetailMode) = Intent(context, DetailActivity::class.java).apply {
			putExtra(DETAIL_MODE_KEY, detailMode)
		}

		fun getIntent(context: Context, id: Long, detailMode: DetailMode) = Intent(context, DetailActivity::class.java).apply {
			putExtra(TODO_ID_KEY, id)
			putExtra(DETAIL_MODE_KEY, detailMode)
		}

	}
}