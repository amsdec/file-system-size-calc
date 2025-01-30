package com.github.amsdec.katas.filesystem;

import java.io.File;

public class FileSystemFactory {

    private FileSystemFactory() {
    }

    public static FileSystem get(final File file) {
        if (file.isDirectory()) {
            return new DirectoryFileSystem(file);
        }
        return new PlainFileFileSystem(file);
    }
}
