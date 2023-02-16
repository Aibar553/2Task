import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.io.*;

public class Sort {
    public static void main(String[] args) {
        int[] mas = { 11, 3, 14, 16, 7 };
        final File file = Paths.get("result.txt").toFile();

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            file.createNewFile();
            System.setOut(new PrintStreamFileForwarder(System.out, fileOutputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isSorted = false;
        int buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < mas.length - 1; i++) {
                if (mas[i] > mas[i + 1]) {
                    isSorted = false;
                    buf = mas[i];
                    mas[i] = mas[i + 1];
                    mas[i + 1] = buf;
                }
            }
        }
        System.out.println(Arrays.toString(mas));
    }

    public static class InputStreamFileForwarder extends InputStreamReader {

        private final FileOutputStream fileOutputStream;

        public InputStreamFileForwarder(InputStream console, FileOutputStream fileOutputStream) {
            super(console);
            this.fileOutputStream = fileOutputStream;
        }

        public int read(char[] cbuf, int offset, int length) throws IOException {
            int read = super.read(cbuf, offset, length);
            if (read > 0) {
                char[] allRead = new char[read];
                System.arraycopy(cbuf, offset, allRead, 0, read);
                fileOutputStream.write(new String(allRead).getBytes());
            }
            return read;
        }
    }

    public static class PrintStreamFileForwarder extends PrintStream {

        private final FileOutputStream fileOutputStream;

        public PrintStreamFileForwarder(PrintStream console, FileOutputStream fileOutputStream) {
            super(console);
            this.fileOutputStream = fileOutputStream;
        }

        public void write(byte[] buf, int off, int len) {
            super.write(buf, off, len);
            try {
                fileOutputStream.write(buf, off, len);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
