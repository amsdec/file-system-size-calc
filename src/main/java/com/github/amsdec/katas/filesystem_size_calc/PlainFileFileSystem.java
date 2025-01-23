package com.github.amsdec.katas.filesystem_size_calc;

import java.io.File;

public class PlainFileFileSystem implements FileSystem {

    private final File file;

    public PlainFileFileSystem(final File file) {
        this.file = file;
    }

    @Override
    public long size() {
        return this.file.length();
    }

}
