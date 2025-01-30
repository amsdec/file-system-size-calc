package com.github.amsdec.katas.filesystem.visitor;

import com.github.amsdec.katas.filesystem.DirectoryFileSystem;
import com.github.amsdec.katas.filesystem.PlainFileFileSystem;

import lombok.Getter;

public class SizeCalculatorVisitor implements Visitor {

    @Getter
    private long size;

    @Override
    public void visit(final DirectoryFileSystem fs) {
        fs.getInnerFiles().forEach(f -> f.accept(this));
    }

    @Override
    public void visit(final PlainFileFileSystem fs) {
        this.size += fs.getFile().length();
    }

}
