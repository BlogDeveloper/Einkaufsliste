package tech.maxxidom.einkaufsliste;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Save {

    private File file;

    public Save(Context context, String filename) {
        setFile(context, filename);
    }


    public void ListWrite(List<Product> list) {

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            for (Product product: list) {
                outputStreamWriter.write(product.toCSV());
            }

            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setFile(Context context, String filename) {

        this.file = new File(context.getExternalFilesDir(null), filename);
    }
}
