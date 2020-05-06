package tech.maxxidom.einkaufsliste;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

class FileLoad {

    private Context context;
    private File file;

    FileLoad(Context context, String filename) {
        setContext(context);
        setFile(filename);
    }

    ArrayList<Product> getArrayList() {

        ArrayList<Product> list = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String product;
            while ((product = bufferedReader.readLine()) != null) {
                list.add(new Product(product));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private void setFile(String filename) {
        file = new File(context.getExternalFilesDir(null), filename);
    }

    private void setContext(Context context) {
        this.context = context;
    }
}
