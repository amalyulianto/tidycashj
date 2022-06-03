package helper;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class xstream<E> {

    private final XStream xstream = new XStream(new StaxDriver());
    private final String namaFileXml;
    private E data;

    public xstream(String namaFileXml, E data) {
        this.namaFileXml = namaFileXml;
        this.data = data;
    }

    public E loadXml() {
        FileInputStream fileXml = null;
        try {
            // nama file yang akan dibuka (termasuk folder jika perlu
            fileXml = new FileInputStream(namaFileXml + ".xml");
            // harus diingat objek apa yang dahulu disimpan di file 
            // program untuk membaca harus sinkron dengan program
            // yang dahulu digunakan untuk menyimpannya
            int isi;
            char charnya;
            String stringnya;
            // isi file dikembalikan menjadi string
            stringnya = "";
            while ((isi = fileXml.read()) != -1) {
                charnya = (char) isi;
                stringnya = stringnya + charnya;
            }
            // string isi file dikembalikan menjadi larik double
            this.data = (E) xstream.fromXML(stringnya);
        } catch (IOException e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (fileXml != null) {
                try {
                    fileXml.close();
                } catch (IOException e) {
                }
            }
        }
        return data;
    }

    public void saveToXML(E data) {

        // larik double diubah menjadi string dengan format XML
        String xml = xstream.toXML(data);

        FileOutputStream outputFileXml = null;

        try {
            // membuat nama file & folder tempat menyimpan jika perlu
            outputFileXml = new FileOutputStream(namaFileXml + ".xml");

            // mengubah karakter penyusun string xml sebagai 
            // bytes (berbentuk nomor2 kode ASCII
            byte[] bytes = xml.getBytes("UTF-8");

            // menyimpan file dari bytes
            outputFileXml.write(bytes);
        } catch (IOException e) {
            System.err.println("Perhatian: " + e.getMessage());
        } finally {
            if (outputFileXml != null) {
                try {
                    outputFileXml.close();
                } catch (IOException e) {
                }
            }
        }

    }
}
