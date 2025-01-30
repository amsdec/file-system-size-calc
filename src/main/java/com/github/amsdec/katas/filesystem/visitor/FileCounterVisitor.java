package com.github.amsdec.katas.filesystem.visitor;

import com.github.amsdec.katas.filesystem.DirectoryFileSystem;
import com.github.amsdec.katas.filesystem.PlainFileFileSystem;

import lombok.Getter;

public class FileCounterVisitor implements Visitor {

    @Getter
    private long count;

    @Override
    public void visit(final DirectoryFileSystem fs) {
        fs.getInnerFiles().forEach(f -> f.accept(this));
    }

    @Override
    public void visit(final PlainFileFileSystem fs) {
        if (fs.getFile().exists()) {
            this.count += 1;
        }
    }

}
