package io.github.hyuck9.hanimani.common.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddTask
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import io.github.hyuck9.hanimani.R
import io.github.hyuck9.hanimani.common.preview.SampleBooleanProvider
import io.github.hyuck9.hanimani.common.theme.AlphaDisabled
import io.github.hyuck9.hanimani.common.theme.HaniManiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HmTextField(
	modifier: Modifier = Modifier,
	value: TextFieldValue,
	onValueChange: (TextFieldValue) -> Unit,
	placeholderValue: String,
	trailingIcon: @Composable (() -> Unit)? = null,
	leadingIcon: @Composable (() -> Unit)? = null,
	isError: Boolean = false,
	visualTransformation: VisualTransformation = VisualTransformation.None,
	keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
	keyboardActions: KeyboardActions = KeyboardActions.Default,
	shape: Shape = MaterialTheme.shapes.small,
	textColor: Color = MaterialTheme.colorScheme.onSurface,
	textStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurface),
	errorLabel: @Composable (() -> Unit)? = null,
) {
	OutlinedTextField(
		value = value,
		onValueChange = { onValueChange(it) },
		placeholder = {
			Text(
				text = placeholderValue,
				style = textStyle,
				color = MaterialTheme.colorScheme.onSurface.copy(AlphaDisabled)
			)
		},
		modifier = modifier
			.height(56.dp)
			.background(
				color = MaterialTheme.colorScheme.secondary,
				shape = shape
			),
		visualTransformation = visualTransformation,
		trailingIcon = trailingIcon,
		leadingIcon = leadingIcon,
		textStyle = textStyle,
		isError = isError,
		keyboardOptions = keyboardOptions,
		keyboardActions = keyboardActions,
		singleLine = true,
		colors = TextFieldDefaults.outlinedTextFieldColors(
			focusedBorderColor = Color.Transparent,
			unfocusedBorderColor = Color.Transparent,
			textColor = textColor
		),
	)

	if (errorLabel != null && isError) {
		errorLabel()
	}
}

@Composable
fun HmTodoCreator(
	modifier: Modifier = Modifier,
	value: TextFieldValue,
	isValid: Boolean,
	placeholder: String,
	onValueChange: (TextFieldValue) -> Unit,
	onSubmit: () -> Unit
) {
	HmModalRow(
		modifier = modifier.padding(all = 16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		HmTextField(
			value = value,
			onValueChange = onValueChange,
			placeholderValue = placeholder,
			shape = MaterialTheme.shapes.large,
			keyboardOptions = KeyboardOptions.Default.copy(
				imeAction = ImeAction.None,
				capitalization = KeyboardCapitalization.Sentences
			),
			modifier = modifier
				.height(50.dp)
				.weight(0.6f),
			trailingIcon = {
				HmIconButton(
					onClick = onSubmit,
					enabled = isValid,
					color = if (isValid) {
						MaterialTheme.colorScheme.primaryContainer
					} else {
						MaterialTheme.colorScheme.surfaceVariant
					},
					modifier = Modifier.size(42.dp)
				) {
					HmIcon(
						imageVector = Icons.Rounded.ArrowUpward,
						tint = if (isValid) {
							LocalContentColor.current
						} else {
							LocalContentColor.current.copy(alpha = AlphaDisabled)
						}
					)
				}
			}
		)
	}
}





@Preview(showBackground = true)
@Composable
private fun HmTextFieldPreview(@PreviewParameter(SampleBooleanProvider::class) isValid: Boolean) {
	HmTextField(
		value = TextFieldValue(),
		onValueChange = {},
		placeholderValue = stringResource(id = R.string.hint_add_task),
		modifier = Modifier.height( 50.dp),
		shape = MaterialTheme.shapes.large,
		textStyle = MaterialTheme.typography.titleSmall,
		trailingIcon = {
			HmIconButton(
				onClick = {},
				enabled = isValid,
				color = if (isValid) {
					MaterialTheme.colorScheme.primaryContainer
				} else {
					MaterialTheme.colorScheme.surfaceVariant
				},
				modifier = Modifier.size(42.dp)
			) {
				HmIcon(
					imageVector = Icons.Rounded.ArrowUpward,
					tint = if (isValid) {
						LocalContentColor.current
					} else {
						LocalContentColor.current.copy(alpha = AlphaDisabled)
					}
				)
			}
		},
		leadingIcon = {
			Icon(
				imageVector = Icons.Rounded.AddTask,
				contentDescription = stringResource(id = R.string.hint_add_task),
				tint = LocalContentColor.current,
				modifier = Modifier
			)
		}
	)
}

@Preview(showBackground = true)
@Composable
private fun HmTodoCreatorPreview() {
	HaniManiTheme {
		HmTodoCreator(
			value = TextFieldValue(),
			isValid = true,
			placeholder = stringResource(id = R.string.hint_add_task),
			onValueChange = {},
			onSubmit = {}
		)
	}
}