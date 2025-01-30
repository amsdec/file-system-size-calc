package com.github.amsdec.katas.filesystem;

import java.io.File;

import com.github.amsdec.katas.filesystem.visitor.Visitor;

import lombok.Getter;

public class PlainFileFileSystem implements FileSystem {

    @Getter
    private final File file;

    public PlainFileFileSystem(final File file) {
        this.file = file;
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }

}
