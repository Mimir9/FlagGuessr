package com.github.mimir9;

import com.github.mimir9.model.Data;
import com.github.mimir9.view.MainFrame;

public class Main {

    public static void main(String[] args) {
        Data.readIni();
        Data.deserializeCountries();
        new MainFrame();
    }
}
