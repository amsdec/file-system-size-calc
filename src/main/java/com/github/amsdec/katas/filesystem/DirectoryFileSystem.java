package com.github.amsdec.katas.filesystem;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.amsdec.katas.filesystem.visitor.Visitor;

import lombok.Getter;

public class DirectoryFileSystem implements FileSystem {

    @Getter
    private final List<FileSystem> innerFiles;

    public DirectoryFileSystem(final File dir) {
        this.innerFiles = Stream.of(dir.listFiles()).map(FileSystemFactory::get).collect(Collectors.toList());
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

}
