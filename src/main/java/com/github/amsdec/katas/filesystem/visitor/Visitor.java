package com.github.amsdec.katas.filesystem.visitor;

import com.github.amsdec.katas.filesystem.DirectoryFileSystem;
import com.github.amsdec.katas.filesystem.PlainFileFileSystem;

public interface Visitor {

    void visit(DirectoryFileSystem fs);

    void visit(PlainFileFileSystem fs);

}
