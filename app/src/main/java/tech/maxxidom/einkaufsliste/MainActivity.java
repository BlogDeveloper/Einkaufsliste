package tech.maxxidom.einkaufsliste;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Product> shopping_list = new ArrayList<>();
    private ShoppingListAdapter adapter;

    private final static String FILE_NAME = "shopping_list.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // File Load
        FileLoad lsp = new FileLoad(this, FILE_NAME);
        shopping_list = lsp.getArrayList();

        // Einkaufsliste
        ListView list = findViewById(R.id.list_product);
        adapter = new ShoppingListAdapter(this, R.layout.item, shopping_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(this);

        // Float Button
        FloatingActionButton btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        FileSave save_list = new FileSave(this);
        save_list.ListWriteToTextFile(shopping_list, FILE_NAME);
    }

    @Override
    public void onClick(View v) {
        ShowAddDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = shopping_list.get(position);
        ShowDialogDelete(product.getName(), position);
    }

    private void ShowDialogDelete(String productname, int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("re you sure you want to delete?" + productname);
        builder.setPositiveButton("Delete", (dialog1, which) -> {
            shopping_list.remove(position);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Deleted: "+ productname, Toast.LENGTH_SHORT).show();
        });
        builder.create();
        builder.show();
    }


    private void ShowAddDialog() {

        // Инициализация диалога.
        View dialog = getLayoutInflater().inflate(R.layout.dialog_add, null);

        // Инициализация EditText.
        EditText product = (EditText) dialog.findViewById(R.id.dialog_product);

        // Инициализация Alert Builder.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialog);
        builder.setTitle("Neu Product");
        builder.setPositiveButton("Produkt einlegen", (dialog1, which) -> {

            // Добавляет в массив новый продукт.
            shopping_list.add(new Product(product.getText().toString()));

            // Перепускает в основном потоке массив
            adapter.notifyDataSetChanged();
        });

        // Создает диалог.
        builder.create();

        // Выводит на экран диалог.
        builder.show();
    }
}
