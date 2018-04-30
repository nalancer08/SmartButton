<p align="center">
  <img src="https://github.com/nalancer08/ABAIS/blob/master/logo.png">
</p>

# SmartButton
SmartButton allows to have a button with a lot of personalization without external drawables

## Installation
SmartButton uses jCenter, please add the repository if you don't have it, then add the dependencie

```
compile 'com.appbuilders.smartbutton:smartbutton:1.0'
```

## Usage
SmartButton can be used into XML layout or JAVA.

Example of usage in xml layout
```
    <com.appbuilders.smartbutton.SmartButton
        android:id="@+id/test"
        android:layout_width="240dp"
        android:layout_height="50dp"
        android:layout_centerInParent="true"
        android:background="@color/fb"
        android:textColor="@color/white"
        android:text="Inicia sesion con facebook"
        app:drawableTint="@color/white"
        android:textSize="12sp"
        app:borderRadius="20"
        app:strokeWidth="3"
        app:strokeColor="@color/colorAccent"
        android:drawableLeft="@drawable/fb_logo_small"
        android:drawablePadding="0dp"/>
```

Example of usage in Java

```
SmartButton button new SmartButton(<context>);
button.setTintColor(Color.CYAN);
button.setBorderRadius(50);
button.setStrokeColor(Color.GREEN);
button.setStrokeWidth(5);
button.setBackgroundColor(Color.RED);
```
All the methods can be ommited, if you setted the background directly from xml.
The only method that works diffrent as normal view its ```setBackgroundColor```,  so if you need to change the color only into the JAVa code, use this method.

To understand the properties we have 4 current properties

- drawableTint: Allows to re paint the icon with the color that you want
- borderRadius: Allows to automatically set border radius to the button without an external drawable attributtes
- strokeColor: Allows to set a color for the stroke
- strokeWidth: Allows to set stroke width

