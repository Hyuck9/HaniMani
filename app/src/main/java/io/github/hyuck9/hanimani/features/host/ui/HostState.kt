package io.github.hyuck9.hanimani.features.host.ui

import io.github.hyuck9.hanimani.model.Theme
import javax.annotation.concurrent.Immutable


@Immutable
data class HostState(val theme: Theme = Theme.SYSTEM)