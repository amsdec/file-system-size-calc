package com.github.amsdec.katas.filesystem.visitor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.github.amsdec.katas.filesystem.FileSystem;
import com.github.amsdec.katas.filesystem.FileSystemFactory;

public class SizeCalculatorVisitorTest {

    private SizeCalculatorVisitor calc;

    private String basePath;

    @Before
    public void setup() throws URISyntaxException {
        this.calc = new SizeCalculatorVisitor();
        this.basePath = Thread.currentThread().getContextClassLoader().getResource(".").toURI().getPath();
    }

    @Test
    public void unknownDirReturnsZero() {
        this.testFileSystem("unknown", 0);
    }

    @Test
    public void directFileReturnsSize() {
        this.testFileSystem("file0.txt", 6);
    }

    @Test
    public void emptyDirReturnsZero() {
        this.testFileSystem("dir0", 0);
    }

    @Test
    public void dirWithFileReturnsSize() {
        this.testFileSystem("dir1", 12);
    }

    @Test
    public void fileInDirReturnsSize() {
        this.testFileSystem("dir1/dir1_file1.txt", 12);
    }

    @Test
    public void dirWithFilesReturnsSize() {
        this.testFileSystem("dir2", 12 * 2);
    }

    @Test
    public void dirWithInnerDirReturnsSize() {
        this.testFileSystem("dir3", 12);
    }

    @Test
    public void dirWithComplexStructureReturnsSize() {
        this.testFileSystem("dir5", 12 * 9);
    }

    private void testFileSystem(final String finalFile, final int expectedSize) {
        final File file = new File(this.basePath + finalFile);
        final FileSystem fileSystem = FileSystemFactory.get(file);

        fileSystem.accept(this.calc);

        final long size = this.calc.getSize();

        assertEquals(expectedSize, size);
    }
}
