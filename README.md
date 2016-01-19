# AnimatedSpriteProgressBar
This Android library gives you custom ProgressBar made of your own sprites.

![Example](https://cloud.githubusercontent.com/assets/12033349/12423642/0e58ed62-bed5-11e5-908a-1653231a87af.gif)

### Download

Gradle:

```groovy
compile 'com.darkminstrel.aspb:animatedspriteprogressbar:1.0.0'
```

Maven:
```xml
<dependency>
  <groupId>com.darkminstrel.aspb</groupId>
  <artifactId>animatedspriteprogressbar</artifactId>
  <version>1.0.0</version>
  <type>aar</type>
</dependency>
```

### Usage

* put your sprites in one of the "drawable" folders:
![Example](https://cloud.githubusercontent.com/assets/12033349/12432011/65dd16b8-bf01-11e5-89ed-550f2e457637.png)

* define the array in values/arrays.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <integer-array name="progress_array">
        <item>@drawable/progress0</item>
        <item>@drawable/progress1</item>
        <item>@drawable/progress2</item>
        <item>@drawable/progress3</item>
        <item>@drawable/progress4</item>
        <item>@drawable/progress5</item>
        <item>@drawable/progress6</item>
        <item>@drawable/progress7</item>
        <item>@drawable/progress8</item>
        <item>@drawable/progress9</item>
    </integer-array>

</resources>
```

* add AnimatedSpriteProgressBar view to your layout:
```xml
<com.darkminstrel.aspb.AnimatedSpriteProgressBar
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:sprites="@array/progress_array"
    />
```

* use custom attributes if needed:
```
  app:duration="3000"
  app:rotations="4"
  app:adapter="trapeze|triangle|square" 
  app:interpolator="accelerate_decelerate|linear"
```

### Requirements

Tested on Android 4.1+ (minSdkVersion 16)

### Licence
> Copyright 2016 Dark Minstrel
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this work except in compliance with the License.
> You may obtain a copy of the License in the LICENSE file, or at:
>
>  [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
