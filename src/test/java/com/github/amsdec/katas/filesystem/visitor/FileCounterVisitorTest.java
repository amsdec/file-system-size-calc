package com.github.amsdec.katas.filesystem.visitor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.github.amsdec.katas.filesystem.FileSystem;
import com.github.amsdec.katas.filesystem.FileSystemFactory;

public class FileCounterVisitorTest {

    private String basePath;

    private FileCounterVisitor counter;

    @Before
    public void setup() throws URISyntaxException {
        this.basePath = Thread.currentThread().getContextClassLoader().getResource(".").toURI().getPath();
        this.counter = new FileCounterVisitor();
    }

    @Test
    public void unknownDirReturnsZero() {
        this.testFileSystem("unknown", 0);
    }

    @Test
    public void directFileReturnsOne() {
        this.testFileSystem("file0.txt", 1);
    }

    @Test
    public void emptyDirReturnsZero() {
        this.testFileSystem("dir0", 0);
    }

    @Test
    public void dirWithFileReturnsFilesCount() {
        this.testFileSystem("dir1", 1);
    }

    @Test
    public void fileInDirReturnsFilesCount() {
        this.testFileSystem("dir1/dir1_file1.txt", 1);
    }

    @Test
    public void dirWithFilesReturnsFilesCount() {
        this.testFileSystem("dir2", 2);
    }

    @Test
    public void dirWithInnerDirFilesCount() {
        this.testFileSystem("dir3", 1);
    }

    @Test
    public void dirWithComplexStructureReturnsFilesCount() {
        this.testFileSystem("dir5", 9);
    }

    private void testFileSystem(final String finalFile, final int expectedCount) {
        final File file = new File(this.basePath + finalFile);
        final FileSystem fileSystem = FileSystemFactory.get(file);

        fileSystem.accept(this.counter);

        final long count = this.counter.getCount();

        assertEquals(expectedCount, count);
    }

}
