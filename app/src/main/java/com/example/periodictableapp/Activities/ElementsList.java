package com.example.periodictableapp.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.periodictableapp.Adapters.ListViewElementsAdapter;
import com.example.periodictableapp.Element;
import com.example.periodictableapp.LoadElements;
import com.example.periodictableapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ElementsList extends AppCompatActivity {

    ListView listViewElements;
    List<Element> elementList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements_list);

        LoadElements load = new LoadElements(getApplicationContext(), "elements");
        String query = load.loadInBackground();
        try {
            JSONObject obj = new JSONObject(query);
            JSONArray jsonArray = obj.getJSONArray("");
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                Element element = new Element();
                element.setSymbol(object.getString("symbol"));
                element.setGroupBlock(object.getString("groupblock"));
                element.setName(object.getString("name"));
                elementList.add(element);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        listViewElements = (ListView) findViewById(R.id.listView_elementsList);

        ListViewElementsAdapter adapter = new ListViewElementsAdapter(this, R.layout.element_item, elementList);
        listViewElements.setAdapter(adapter);
    }
}