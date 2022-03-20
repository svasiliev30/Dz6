package sbp.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class MyIOExample
{
    /**
     * Создать объект класса {@link java.io.File}, проверить существование и чем является (фалй или директория).
     * Если сущность существует, то вывести в консоль информацию:
     *      - абсолютный путь
     *      - родительский путь
     * Если сущность является файлом, то вывести в консоль:
     *      - размер
     *      - время последнего изменения
     * Необходимо использовать класс {@link java.io.File}
     * @param fileName - имя файла
     * @return - true, если файл успешно создан
     */
    public boolean workWithFile(String fileName) throws IOException {

        Path path = Paths.get(fileName);

            if (! Files.exists(path)) {
                System.out.println("Fail is not.");
                return false;
            }
            if (Files.isDirectory(path)) {
                System.out.println("This is directory" );
                System.out.println(" Get parent - " + path.getParent());
                System.out.println(" Get absolute path - " + path.getFileSystem());

            }else {
                System.out.println("This is file");
                System.out.println(" Get absolute path - " + path.getFileSystem());
                System.out.println(" Get parent - " + path.getParent());
                System.out.println("Length at byte -" + Files.size(path));
                System.out.println("Time with last modified -" + Files.getLastModifiedTime(path));
            }
            return true;
        }


    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.FileInputStream} и {@link java.io.FileOutputStream}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyFile(String sourceFileName, String destinationFileName) {

            try (
                    InputStream inputStream = Files.newInputStream(Paths.get(sourceFileName));
                    OutputStream outputStream = Files.newOutputStream(Paths.get(destinationFileName));
                    ){
                byte[] buf = new byte[1024];
                int r = inputStream.read(buf, 0, buf.length);
                if (r > 0) {
                    outputStream.write(buf, 0, r);
                }
            }catch (Exception e){
                return  false;
            }
        return true;
    }

    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.BufferedInputStream} и {@link java.io.BufferedOutputStream}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyBufferedFile(String sourceFileName, String destinationFileName) {
        Path path = Paths.get(sourceFileName);
        Path path1 = Paths.get(destinationFileName);
        String copy;

        if (!Files.exists(path)) {
            System.out.println("Fail is not.");
            return false;
        }
            try(BufferedReader reader = Files.newBufferedReader(path);
                BufferedWriter writer = Files.newBufferedWriter(path1);)
                    {
                        while ((copy = reader.readLine()) != null) {
                            System.out.println(copy);
                            writer.write("new" + copy);
                            writer.write('\n');
                        }
            }catch (Exception e){
                e.printStackTrace();
            }
        return true;
    }

    /**
     * Метод должен создавать копию файла
     * Необходимо использовать IO классы {@link java.io.FileReader} и {@link java.io.FileWriter}
     * @param sourceFileName - имя исходного файла
     * @param destinationFileName - имя копии файла
     * @return - true, если файл успешно скопирован
     */
    public boolean copyFileWithReaderAndWriter(String sourceFileName, String destinationFileName) {
        Path path = Paths.get(sourceFileName);
        Path path1 = Paths.get(destinationFileName);
    try {
            if (!Files.exists(path)) {
                System.out.println("Fail is not.");
                return false;
            }

            Files.copy(path, path1.getParent().resolve(path1));
            if (Objects.nonNull(path)) {
                if (Objects.nonNull(path1)) {
                    return true;
                }
            }
        }catch (IOException e){
        e.printStackTrace();
         }
        return false;
    }
}

