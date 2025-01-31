package com.github.amsdec.katas.filesystem.visitor;

import java.io.FileReader;
import java.io.IOException;

import com.github.amsdec.katas.filesystem.DirectoryFileSystem;
import com.github.amsdec.katas.filesystem.PlainFileFileSystem;

import lombok.Getter;

public class CharCounterVisitor implements Visitor {

    @Getter
    private long totalChars;

    @Override
    public void visit(final DirectoryFileSystem fs) {
        fs.accept(this);
    }

    @Override
    public void visit(final PlainFileFileSystem fs) {
        try (FileReader reader = new FileReader(fs.getFile())) {
            while ((reader.read()) != -1) {
                this.totalChars++;
            }
        } catch (final IOException e) {
            this.totalChars += 0;
        }
    }

}
