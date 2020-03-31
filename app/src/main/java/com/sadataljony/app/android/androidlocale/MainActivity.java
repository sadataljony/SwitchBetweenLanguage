package com.sadataljony.app.android.androidlocale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnChangeLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();// get lang code at the time of activity start
        setContentView(R.layout.activity_main);// initialize ui
        mBtnChangeLanguage = findViewById(R.id.buttonChangeLanguage);// initialize button
        mBtnChangeLanguage.setOnClickListener(this);// set on click event on button
    }

    // load previously set lang code in SharedPreferences
    public void loadLocale() {
        try {
            // get language to SharedPreferences
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(MainActivity.this);
            String strMyLanguage = sharedPreferences.getString("my_language", "");// set lang code in my_language key which type is string
            setLocale(strMyLanguage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        if (v == mBtnChangeLanguage) {
            chooseLanguageDialog();// show choose language dialog
        }
    }

    private void chooseLanguageDialog() {
        final String[] listItems = {"English", "বাংলা", "हिंदी", "العربية", "اردو", "Français", "Germán", "Espanol", "Nederlands", "中国人", "日本人"};// string array of languages
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Change Language");// dialog title
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                if (position == 0) {// string array index
                    setLocale("en");// en for english
                    recreate();
                } else if (position == 1) {
                    setLocale("bn");// bn for bengali
                    recreate();
                } else if (position == 2) {
                    setLocale("hi");// hi for hindi
                    recreate();
                } else if (position == 3) {
                    setLocale("ar");// ar for arabic
                    recreate();
                } else if (position == 4) {
                    setLocale("ur");// ur for urdu
                    recreate();
                } else if (position == 5) {
                    setLocale("fr");// fr for french
                    recreate();
                } else if (position == 6) {
                    setLocale("de");// de for german
                    recreate();
                } else if (position == 7) {
                    setLocale("es");// es for spanish
                    recreate();
                } else if (position == 8) {
                    setLocale("nl");// nl for dutch
                    recreate();
                } else if (position == 9) {
                    setLocale("zh");// zh for chinese
                    recreate();
                } else if (position == 10) {
                    setLocale("ja");// ja for japanese
                    recreate();
                }
                dialogInterface.dismiss();// dismiss dialog after select an item
            }
        });
        AlertDialog alertDialog = mBuilder.create();// create dialog
        alertDialog.show();// show dialog
    }

    // set language
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        try {
            // set language to SharedPreferences
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(MainActivity.this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("my_language", lang);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
