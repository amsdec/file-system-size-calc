package com.github.amsdec.katas.filesystem_size_calc;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

public class FileSystemSizeCalculatorTest {

    private FileSystemSizeCalculator calc;

    private String basePath;

    @Before
    public void setup() throws URISyntaxException {
        this.calc = new FileSystemSizeCalculator();
        this.basePath = Thread.currentThread().getContextClassLoader().getResource(".").toURI().getPath();
    }

    @Test
    public void nullDirReturnsZero() {
        final long size = this.calc.getSize(null);
        assertEquals(0, size);
    }

    @Test
    public void unknownDirReturnsZero() {
        final long size = this.calc.getSize(this.basePath + "unknown");
        assertEquals(0, size);
    }

    @Test
    public void directFileReturnsSize() {
        final long size = this.calc.getSize(this.basePath + "file0.txt");
        assertEquals(6, size);
    }

    @Test
    public void emptyDirReturnsZero() {
        final long size = this.calc.getSize(this.basePath + "dir0");
        assertEquals(0, size);
    }

    @Test
    public void dirWithFileReturnsSize() {
        final long size = this.calc.getSize(this.basePath + "dir1");
        assertEquals(12, size);
    }

    @Test
    public void fileInDirReturnsSize() {
        final long size = this.calc.getSize(this.basePath + "dir1/dir1_file1.txt");
        assertEquals(12, size);
    }

    @Test
    public void dirWithFilesReturnsSize() {
        final long size = this.calc.getSize(this.basePath + "dir2");
        assertEquals(12 * 2, size);
    }

    @Test
    public void dirWithInnerDirReturnsSize() {
        final long size = this.calc.getSize(this.basePath + "dir3");
        assertEquals(12, size);
    }

    @Test
    public void dirWithComplexStructureReturnsSize() {
        final long size = this.calc.getSize(this.basePath + "dir5");
        assertEquals(12 * 9, size);
    }
}
