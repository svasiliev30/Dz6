import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sbp.io.MyIOExample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestMyIOExceptions {

    String myFile = "C:\\work\\learn programmer\\6 week\\lecture11-12\\MyFile";
    String myFileNew = "C:\\work\\learn programmer\\6 week\\lecture11-12\\NewMyFile";
    String directory = "C:\\work\\learn programmer\\6 week\\lecture11-12\\src";
    String myFileTest = "C:\\work\\learn programmer\\6 week\\lecture11-12\\MyFileTest";

    /**
     * Проверка, что файл существует и отображается как файл.
     * @throws IOException
     */
    @Test
    void fileExistsAndIsFile() throws IOException {

        MyIOExample myIOExample = new MyIOExample();
        Assertions.assertTrue(myIOExample.workWithFile(myFile));
    }

    /**
     * Проверка, что файл не существует.
     * @throws IOException
     */
    @Test
    void fileIsNotExists() throws IOException {

        MyIOExample myIOExample = new MyIOExample();
        Assertions.assertFalse(myIOExample.workWithFile("MyFil"));
    }

    /**
     * Проверка, что это директория.
     * @throws IOException
     */
    @Test
    void ThisIsDirectory() throws IOException {

        MyIOExample myIOExample = new MyIOExample();
        Assertions.assertTrue(myIOExample.workWithFile(directory));
    }

    /**
     * Проверка, на копирование файла, файл есть.
     * @throws IOException
     */
    @Test
    void CopyFile() throws IOException {

        Path path = Paths.get(myFileNew);
        MyIOExample myIOExample = new MyIOExample();

        Assertions.assertTrue(myIOExample.copyFile(myFile, myFileNew));
        Files.delete( path);
    }

    /**
     * Проверка, на копирование файла, файла нет.
     * @throws IOException
     */
    @Test
    void IsNotCopyFile() throws IOException {

        MyIOExample myIOExample = new MyIOExample();
        Assertions.assertFalse(  myIOExample.copyFile("MyFil", myFileNew));
    }

    /**
     * Проверка, на копирование файла.
     * @throws IOException
     */
    @Test
    void BufferCopy() throws IOException {

        MyIOExample myIOExample = new MyIOExample();
        Assertions.assertTrue(myIOExample.copyBufferedFile(myFile, myFileTest));
    }

    /**
     * нет файла
     * @throws IOException
     */
    @Test
    void BufferNeedFail() throws IOException {

        MyIOExample myIOExample = new MyIOExample();
        Assertions.assertFalse(myIOExample.copyBufferedFile("myf", myFileTest));
    }

    /**
     * Проверка, на копирование файла, файл есть.
     * @throws IOException
     */
    @Test
    void CopyFileTwoVersion() throws IOException {

        Path path = Paths.get(myFileNew);
        MyIOExample myIOExample = new MyIOExample();

        Assertions.assertTrue(myIOExample.copyFileWithReaderAndWriter(myFile, myFileNew));
        Files.delete( path);
    }

    /**
     * Проверка, на копирование файла, файла нет.
     * @throws IOException
     */
    @Test
    void CopyFileTwoVersionNeedFile() throws IOException {

        Path path = Paths.get(myFileNew);
        MyIOExample myIOExample = new MyIOExample();

        Assertions.assertFalse(myIOExample.copyFileWithReaderAndWriter("myFil", myFileNew));
    }
}


