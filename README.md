NumberKeypadView
===============

A Fast and Simple Library for Number Keypad

How to Include in your project
------

Include in build.gradle (project level)

```
allprojects {
    repositories {
        ....
        maven { url 'https://jitpack.io' }
    }
}
```

Include in build.gradle (app level)
```
dependencies {
    ...
    implementation 'com.github.JerrySJoseph:NumberKeypad:1.1.0'
}
```

Usage
-----
```xml
<com.jstechnologies.numberkeypadview.NumberKeypadView
        android:id="@+id/numberkeypad"
        android:layout_gravity="bottom"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:keyType="NONE"
        app:maxLength="6"
        app:keyTextColor="@color/colorPrimary"
        app:TextColor="@color/colorPrimary"
        app:TextHintColor="@android:color/darker_gray"
        app:TextSize="12dp"
        app:BackspaceDrawable="@drawable/ic_outline_backspace"
        app:TextHidden="true"
        app:Hint="Please Enter the Password"/>
```

Updates
-----------
* You can now change the background of each key via attribute 'app:keyBackground' which accepts any drawable.
* The layout automatically adjusts to the screen Size and calculate the least required hieght. You can also set height explicitly.
* The app:layoutBackground attributes accepts drawable which is used to set the background of whole layout. Please note that in order for the background to be completely visible, all keytype should be NONE.



License
-------

    Copyright 2014 - 2021 Jerin Sebastian

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
