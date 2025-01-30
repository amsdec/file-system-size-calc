package com.github.amsdec.katas.filesystem;

import com.github.amsdec.katas.filesystem.visitor.Visitor;

public interface FileSystem {

    void accept(Visitor visitor);

}
