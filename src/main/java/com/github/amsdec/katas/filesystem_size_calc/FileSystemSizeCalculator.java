package com.github.amsdec.katas.filesystem_size_calc;

import java.io.File;

public class FileSystemSizeCalculator {

    public long getSize(final String path) {
        if (path == null) {
            return 0;
        }

        final File file = new File(path);
        return this.calcFileSize(file);
    }

    private long calcFileSize(final File file) {
        return FileSystemFactory.get(file).size();
    }

}
