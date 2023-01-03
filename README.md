<h1 align="center">하니마니 (HaniMani)</h1>

<p align="center">
    <a href="https://developer.android.com/jetpack/androidx/versions/all-channel#november_11_2022"><img src="https://img.shields.io/badge/Jetpack%20Compose-1.3.1-brightgreen"/></a>
    <a href="https://android-arsenal.com/api?level=26"><img alt="API" src="https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">
✔️ 하니마니 앱은 MVI 기반으로 Jetpack Compose, Hilt, Material3, Coroutines, Flows, Room, Proto DataStore 등 Modern Android 기술 스택을 사용하여 구현하였습니다.
</p>

<p align="center">
    <a href="https://play.google.com/store/apps/details?id=io.github.hyuck9.hanimani">
      <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
    </a>
    <img src="https://github.com/Hyuck9/HaniMani/blob/master/art/hanimani_cover.png?raw=true"/>
</p>

## Screenshot 📷
|                                   Change Tab                                    |                                    Input Task                                     |                                    Change Theme                                     |
|:-------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------:|
| ![](https://github.com/Hyuck9/HaniMani/blob/master/art/1_move_tab.gif?raw=true) | ![](https://github.com/Hyuck9/HaniMani/blob/master/art/2_input_task.gif?raw=true) | ![](https://github.com/Hyuck9/HaniMani/blob/master/art/3_change_theme.gif?raw=true) |

|                                  Change Text Align                                  |                               LongClick Reorder                                |                                  Complete And Delete                                   |
|:-----------------------------------------------------------------------------------:|:------------------------------------------------------------------------------:|:--------------------------------------------------------------------------------------:|
| ![](https://github.com/Hyuck9/HaniMani/blob/master/art/4_change_align.gif?raw=true) | ![](https://github.com/Hyuck9/HaniMani/blob/master/art/5_reorder.gif?raw=true) | ![](https://github.com/Hyuck9/HaniMani/blob/master/art/6_complete_delete.gif?raw=true) |

## Features 👓
하니마니의 기본 기능은 다음과 같으며, 삭제된 Task 관리 및 통계 등 여러 기능들을 추가 구현할 예정입니다.
- Jetpack Compose UI 기반 Single Activity Design
- Broadcast receiver 를 통한 Screen On/Off 감지
- Screen On 자동실행 기능의 안정적 수행을 위한 Foreground Service
- Theme 및 Typography 등 실시간 변경 (Proto DataStore)
- Simple To-Do List
- URL 저장소 등으로도 활용할 수 있도록 Task 문구 복사 기능
- Swipe Complete/Delete 및 LongClick Reordering

## Libraries and tools 🛠️
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
- [Flow](https://developer.android.com/kotlin/flow)
- [Jetpack Compose UI Toolkit](https://developer.android.com/jetpack/compose)
- [Accompanist](https://chrisbanes.github.io/accompanist/)
- [Material3](https://m3.material.io/)
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
- [Room Database](https://developer.android.com/topic/libraries/architecture/room)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

- [Lottie Compose](https://github.com/airbnb/lottie/blob/master/android-compose.md)
- [Timber](https://github.com/JakeWharton/timber)

## Developed By 😎
- Hyuck9 - lhg1304@gmail.com

## License 🔗
```
MIT License

Copyright (c) 2022 Hyuck9

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
