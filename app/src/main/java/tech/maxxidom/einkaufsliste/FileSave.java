package tech.maxxidom.einkaufsliste;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;


class FileSave {

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

    private void setFile(String filename) {
        file = new File(context.getExternalFilesDir(null), filename);
    }

    private void setContext(Context context) {
        this.context = context;
    }
}
