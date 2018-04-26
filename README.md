# SmartButton
SmartButton allows to have a button with a lot of personalization without external drawables

## Usage
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

To understand the properties we have 4 current properties

- drawableTint: Allows to re paint the icon with the color that you want
- borderRadius: Allows to automatically set border radius to the button without an external drawable attributtes
- strokeColor: Allows to set a color for the stroke
- strokeWidth: Allows to set stroke width

