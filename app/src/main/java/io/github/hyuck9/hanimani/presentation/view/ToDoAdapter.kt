package io.github.hyuck9.hanimani.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.data.entity.Task
import io.github.hyuck9.hanimani.databinding.ItemTodoBinding

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoItemViewHolder>() {

	private var toDoList: List<Task> = listOf()
	private lateinit var toDoItemClickListener: (Task) -> Unit
	private lateinit var toDoCheckListener: (Task) -> Unit

	inner class ToDoItemViewHolder(
		private val binding: ItemTodoBinding,
		val toDoItemClickListener: (Task) -> Unit
	) : RecyclerView.ViewHolder(binding.root) {

		fun bindData(data: Task) = with(binding) {
			checkBox.text = data.title
			checkToDoComplete(data.isCompleted)
		}

		fun bindViews(data: Task) {
			binding.checkBox.setOnClickListener {
				toDoCheckListener(
					data.copy(isCompleted = binding.checkBox.isChecked)
				)
				checkToDoComplete(binding.checkBox.isChecked)
			}
			binding.root.setOnClickListener {
				toDoItemClickListener(data)
			}
		}

		private fun checkToDoComplete(isChecked: Boolean) = with(binding) {
			checkBox.isChecked = isChecked
			container.setBackgroundColor(
				ContextCompat.getColor(
					root.context,
					if (isChecked) R.color.gray_300 else R.color.white
				)
			)
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
		val view = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ToDoItemViewHolder(view, toDoItemClickListener)
	}

	override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
		holder.bindData(toDoList[position])
		holder.bindViews(toDoList[position])
	}

	override fun getItemCount(): Int = toDoList.size

	fun setToDoList(
		toDoList: List<Task>,
		toDoItemClickListener: (Task) -> Unit,
		toDoCheckListener: (Task) -> Unit
	) {
		this.toDoList = toDoList
		this.toDoItemClickListener = toDoItemClickListener
		this.toDoCheckListener = toDoCheckListener
		notifyDataSetChanged()
	}
}