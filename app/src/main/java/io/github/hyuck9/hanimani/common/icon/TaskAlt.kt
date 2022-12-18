package io.github.hyuck9.hanimani.common.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TaskAlt: ImageVector
    get() {
        if (taskAlt != null) {
            return taskAlt!!
        }
        taskAlt = Builder(name = "TaskAltIcon", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(18.7082f, 4.9264f)
                curveTo(16.927f, 3.2635f, 14.5832f, 2.3274f, 12.1414f, 2.3037f)
                curveTo(9.6996f, 2.28f, 7.338f, 3.1703f, 5.5245f, 4.7983f)
                curveTo(3.7111f, 6.4263f, 2.5781f, 8.6731f, 2.3502f, 11.0935f)
                curveTo(2.1222f, 13.5139f, 2.8159f, 15.9312f, 4.2939f, 17.8663f)
                curveTo(5.7718f, 19.8015f, 7.926f, 21.1134f, 10.3297f, 21.5419f)
                curveTo(12.7334f, 21.9704f, 15.211f, 21.4844f, 17.2717f, 20.18f)
                curveTo(19.3324f, 18.8757f, 20.8256f, 16.8483f, 21.4556f, 14.4996f)
                curveTo(22.0855f, 12.1509f, 21.4528f, 8.2913f, 20.227f, 6.9579f)
                lineTo(18.9327f, 8.4066f)
                curveTo(19.8374f, 10.1256f, 20.0604f, 12.1202f, 19.5575f, 13.9951f)
                curveTo(19.0546f, 15.87f, 17.8626f, 17.4884f, 16.2176f, 18.5296f)
                curveTo(14.5727f, 19.5708f, 12.5948f, 19.9588f, 10.676f, 19.6167f)
                curveTo(8.7572f, 19.2747f, 7.0375f, 18.2275f, 5.8578f, 16.6826f)
                curveTo(4.678f, 15.1378f, 4.1242f, 13.2082f, 4.3061f, 11.276f)
                curveTo(4.4881f, 9.3439f, 5.3925f, 7.5504f, 6.8402f, 6.2508f)
                curveTo(8.2878f, 4.9512f, 10.173f, 4.2404f, 12.1223f, 4.2594f)
                curveTo(14.0715f, 4.2783f, 15.9424f, 5.0255f, 17.3643f, 6.353f)
                lineTo(18.7082f, 4.9264f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(20.6166f, 5.105f)
                curveTo(20.6166f, 5.1747f, 20.5923f, 5.2422f, 20.5479f, 5.296f)
                lineTo(10.1034f, 17.942f)
                curveTo(9.9644f, 18.1103f, 9.698f, 18.0785f, 9.6024f, 17.8823f)
                lineTo(6.8051f, 12.1387f)
                curveTo(6.7393f, 12.0035f, 6.7837f, 11.8405f, 6.9089f, 11.7574f)
                lineTo(7.0551f, 11.6603f)
                curveTo(7.1806f, 11.577f, 7.3484f, 11.5997f, 7.4473f, 11.7133f)
                lineTo(9.6027f, 14.1892f)
                curveTo(9.7141f, 14.3172f, 9.9095f, 14.3273f, 10.0336f, 14.2116f)
                lineTo(20.112f, 4.811f)
                curveTo(20.3038f, 4.6321f, 20.6166f, 4.7681f, 20.6166f, 5.0303f)
                verticalLineTo(5.105f)
                close()
            }
        }
        .build()
        return taskAlt!!
    }

private var taskAlt: ImageVector? = null
