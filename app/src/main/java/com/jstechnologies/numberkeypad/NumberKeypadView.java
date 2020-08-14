package com.jstechnologies.numberkeypad;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class NumberKeypadView extends LinearLayout {


    //Default KeyPadType
    private int _keyPadType=KeyPadType.ROUNDED_BORDER;
    private int _layoutBackground,_keyBackground,_hintTexTColor,_textColor,_maxLength,_backSpaceDrawable;
    int _keyTextColor;
    private boolean _isHidden=false;
    private int[] keyIDs={R.id.key0,R.id.key1,R.id.key2,R.id.key3,
            R.id.key4,R.id.key5,R.id.key6,R.id.key7,
            R.id.key8,R.id.key9,R.id.keystar,R.id.keyhash};
    String hintText=" ";
    float _textSize;
    Context context;
    View KeyPadView;
    LinearLayout layout;
    EditText text;
    OnKeyPressListener onKeyPressListener;
    OnEntryComplete onEntryComplete;
    ImageView backspace;


    public NumberKeypadView(Context context) {
        super(context);
        this.context=context;
        Init();
    }
    public NumberKeypadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        Init(attrs);
    }

    public NumberKeypadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        Init(attrs);
    }

    public void setOnEntryComplete(OnEntryComplete onEntryComplete) {
        this.onEntryComplete = onEntryComplete;
    }

    public void setOnKeyPressListener(OnKeyPressListener onKeyPressListener) {
        this.onKeyPressListener = onKeyPressListener;
    }

    public void setkeyTextColor(int keyTextColor) {
        this._keyTextColor = keyTextColor;
        updateKeyTextColor(_keyTextColor);
    }
    public void setkeyPadType(int _keyPadType) {
        this._keyPadType = _keyPadType;
        updateKeyBackground();
    }

    public void setlayoutBackground(int resID) {
        this._layoutBackground = resID;
    }

    public void Init()
    {
        KeyPadView=inflate(context,R.layout.layout_keypad,this);
        layout=KeyPadView.findViewById(R.id.layout);
        text=KeyPadView.findViewById(R.id.text);
        backspace=KeyPadView.findViewById(R.id.backspace);
      //  updateKeyBackground();
     //   updateKeyTextColorResources(android.R.color.white);

    }
    public void Init(AttributeSet attrs)
    {
        KeyPadView=inflate(context,R.layout.layout_keypad,this);
        layout=KeyPadView.findViewById(R.id.layout);
        text=KeyPadView.findViewById(R.id.text);
        backspace=KeyPadView.findViewById(R.id.backspace);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.NumberKeypadView,
                0, 0);

        try {
            _keyPadType=a.getInt(R.styleable.NumberKeypadView_keyType,KeyPadType.ROUNDED_BORDER);
            _keyBackground=a.getResourceId(R.styleable.NumberKeypadView_keyBackground, 0);
            _layoutBackground=a.getResourceId(R.styleable.NumberKeypadView_layoutBackground, 0);
            _backSpaceDrawable=a.getResourceId(R.styleable.NumberKeypadView_BackspaceDrawable, R.drawable.ic_outline_backspace);
            _keyTextColor=a.getColor(R.styleable.NumberKeypadView_keyTextColor,Color.BLACK);
            _hintTexTColor=a.getColor(R.styleable.NumberKeypadView_TextHintColor,Color.BLACK);
            _textColor=a.getColor(R.styleable.NumberKeypadView_TextColor,Color.BLACK);
            hintText=a.getString(R.styleable.NumberKeypadView_Hint);
            _textSize=a.getDimension(R.styleable.NumberKeypadView_TextSize,15);
            _maxLength=a.getInt(R.styleable.NumberKeypadView_maxLength,10);
            _isHidden=a.getBoolean(R.styleable.NumberKeypadView_TextHidden,false);
        } finally {
            a.recycle();
            updateKeyBackground();
            updateKeyTextColor(_keyTextColor);
            layout.setBackgroundResource(_layoutBackground);
            updateEditText();
            backspace.setOnClickListener(_backspaceClickListener);
        }
    }
    public void updateKeyBackground()
    {

        switch (_keyPadType)
        {
            case KeyPadType.BOXED_BORDER:_keyBackground=R.drawable.bg_key_boxed_border;break;
            case KeyPadType.BOXED_SOLID:_keyBackground=R.drawable.bg_key_boxed_solid;break;
            case KeyPadType.ROUNDED_BORDER:_keyBackground=R.drawable.bg_key_round_border;break;
            case KeyPadType.ROUNDED_SOLID:_keyBackground=R.drawable.bg_key_round_solid;break;
            case KeyPadType.NONE:_keyBackground=0;
        }
        for(int id:keyIDs)
        {
            Button button= findViewById(id);
            button.setBackgroundResource(_keyBackground);
            button.setOnClickListener(_buttonClickListener);
        }
        backspace.setImageResource(_backSpaceDrawable);
    }
    public static Drawable changeDrawableColor(int drawableRes, int colorRes, Context context) {
        Drawable mDrawable = ContextCompat.getDrawable(context,drawableRes);
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(context.getResources().getColor(colorRes), PorterDuff.Mode.SRC_OVER));
        return mDrawable;
    }
    public void updateKeyTextColorResources(int resID)
    {
        for(int id:keyIDs)
        {
            Button button= findViewById(id);
            button.setTextColor(context.getResources().getColor(resID));
        }
    }
    public void updateKeyTextColor(int color)
    {
        for(int id:keyIDs)
        {
            Button button= findViewById(id);
            button.setTextColor(color);
        }
    }
    public void updateEditText()
    {
        text.setFilters(new InputFilter[] { new InputFilter.LengthFilter(_maxLength) });
        text.setTextSize(_textSize);
        text.setHint(hintText);
        if(_isHidden)
        {
            text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            text.setSelection(text.getText().length());
        }
        text.setHintTextColor(_hintTexTColor);
        text.setTextColor(_textColor);
    }
    View.OnClickListener _buttonClickListener= new OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button= findViewById(view.getId());
            text.append(button.getText());
            if(onKeyPressListener!=null)
                onKeyPressListener.onPressed(view.getId(),button.getText().toString());
            if(text.length()>=_maxLength && onEntryComplete !=null)
                onEntryComplete.onComplete(text.getText().toString().trim());
        }
    };
    View.OnClickListener _backspaceClickListener= new OnClickListener() {
        @Override
        public void onClick(View view) {
            int length = text.getText().length();
            if (length > 0) {
                text.getText().delete(length - 1, length);
            }
            if(onKeyPressListener!=null)
                onKeyPressListener.onPressed(view.getId(),null);
        }
    };
    public String getText()
    {
        return text.getText().toString().trim();
    }
    public static class KeyPadType{
        public final static int ROUNDED_BORDER=1;
        public final static int BOXED_BORDER=2;
        public final static int ROUNDED_SOLID=3;
        public final static int BOXED_SOLID=4;
        public final static int NONE=6;
        public final static int CUSTOM=5;
    }
    public static class Keys
    {
        public final static int Key1=R.id.key1;
        public final static int Key2=R.id.key2;
        public final static int Key3=R.id.key3;
        public final static int Key4=R.id.key4;
        public final static int Key5=R.id.key5;
        public final static int Key6=R.id.key6;
        public final static int Key7=R.id.key7;
        public final static int Key8=R.id.key8;
        public final static int Key9=R.id.key9;
        public final static int KeyStar=R.id.keystar;
        public final static int KeyHash=R.id.keyhash;
        public final static int KeybackSpace=R.id.backspace;
    }

    public interface OnKeyPressListener{
        void onPressed(int key, String text);
    }
    public interface OnEntryComplete
    {
        void onComplete(String text);
    }
}
