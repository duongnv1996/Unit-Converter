package com.duongkk.unitconverter.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import com.duongkk.unitconverter.R;

/**
 * Created by MyPC on 7/12/2016.
 */
public class CommomUtils {
    public static void copyToClipBoard(String s, Context context){
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(s, s);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, R.string.copy_to_clipboard,Toast.LENGTH_SHORT).show();
    }
}
