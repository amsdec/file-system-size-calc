package com.github.amsdec.katas.filesystem.visitor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.github.amsdec.katas.filesystem.FileSystem;
import com.github.amsdec.katas.filesystem.FileSystemFactory;

public class CharCounterVisitorTest {

    private String basePath;

    private CharCounterVisitor counter;

    @Before
    public void setup() throws URISyntaxException {
        this.basePath = Thread.currentThread().getContextClassLoader().getResource(".").toURI().getPath();
        this.counter = new CharCounterVisitor();
    }

    @Test
    public void unknownDirReturnsZero() {
        this.testFileSystem("unknown", 0);
    }

    @Test
    public void emptyFileReturnsZero() {
        this.testFileSystem("empty.txt", 0);
    }

    @Test
    public void directFileReturnsCharsInFile() {
        this.testFileSystem("file0.txt", 6);
    }

    @Test
    public void emptyDirReturnsZero() {
        this.testFileSystem("dir0", 0);
    }

    @Test
    public void dirWithFileReturnsCharsInFile() {
        this.testFileSystem("dir1", 12);
    }

    @Test
    public void fileInDirReturnsCharsInFile() {
        this.testFileSystem("dir1/dir1_file1.txt", 12);
    }

    @Test
    public void dirWithFilesReturnsCharsInFiles() {
        this.testFileSystem("dir2", 24);
    }

    @Test
    public void dirWithInnerDirReturnsCharsInFiles() {
        this.testFileSystem("dir3", 12);
    }

    @Test
    public void dirWithComplexStructureReturnsCharsInFiles() {
        this.testFileSystem("dir5", 108);
    }

    private void testFileSystem(final String finalFile, final int expectedChars) {
        final File file = new File(this.basePath + finalFile);
        final FileSystem fileSystem = FileSystemFactory.get(file);

        fileSystem.accept(this.counter);

        final long chars = this.counter.getTotalChars();

        assertEquals(expectedChars, chars);
    }

}
