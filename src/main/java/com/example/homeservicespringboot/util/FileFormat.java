package com.example.homeservicespringboot.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileFormat implements FilenameFilter {
    String[] formats;

    public FileFormat(String...formats) {
        this.formats = formats;
    }

    @Override
    public boolean accept(File dir, String name) {
        for (String format: formats) {
            if (name.endsWith(format)){
                return true;
            }
        }
        return false;
    }
}
