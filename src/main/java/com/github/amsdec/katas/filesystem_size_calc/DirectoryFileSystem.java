package com.github.amsdec.katas.filesystem_size_calc;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryFileSystem implements FileSystem {

    private final List<FileSystem> innerFiles;

    public DirectoryFileSystem(final File dir) {
        this.innerFiles = Stream.of(dir.listFiles()).map(FileSystemFactory::get).collect(Collectors.toList());
    }

    @Override
    public long size() {
        return this.innerFiles.stream().map(FileSystem::size).reduce(0L, Long::sum);
    }

}
