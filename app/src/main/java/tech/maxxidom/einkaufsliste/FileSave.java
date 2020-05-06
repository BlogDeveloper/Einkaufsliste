package tech.maxxidom.einkaufsliste;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;


public class FileSave {

    private File file;
    private Context context;

    FileSave(Context context) {
        setContext(context);
    }

    void ListWriteToTextFile(List<Product> list, String filename) {

        setFile(filename);

        try {
            BufferedWriter bwr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            for (Product product: list) {
                bwr.append(product.toCSV());
            }

            bwr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ListWriteToCsvFile(List<Product> list, String filename) {

        setFile(filename);

        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
//
//            for (Product product: list) {
//                outputStreamWriter.write(product.toCSV());
//            }
//
//            outputStreamWriter.close();
//            fileOutputStream.close();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            for (Product product: list) {
                out.append(product.toCSV());
            }

            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFile(String filename) {
        file = new File(context.getExternalFilesDir(null), filename);
    }

    private void setContext(Context context) {
        this.context = context;
    }
}
