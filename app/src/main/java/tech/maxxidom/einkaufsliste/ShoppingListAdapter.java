package tech.maxxidom.einkaufsliste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;


public class ShoppingListAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> shopping_list;

    // Сет для хранения номеров выбранных элементов
    private HashSet<Integer> selection = new HashSet<>();

    public ShoppingListAdapter(@NonNull Context context, int resource, ArrayList<Product> shopping_list) {
        super(context, resource);
        setShopping_list(shopping_list);
    }

    @Override
    public int getCount() {
        return shopping_list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(R.layout.item, parent, false);

        Product product = shopping_list.get(position);

        TextView list_item_product = convertView.findViewById(R.id.list_item_product);
        list_item_product.setText(product.getName());

        return convertView;
    }

    private void setShopping_list(ArrayList<Product> shopping_list) {
        this.shopping_list = shopping_list;
    }

}