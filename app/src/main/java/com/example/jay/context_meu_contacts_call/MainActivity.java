package com.example.jay.context_meu_contacts_call;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> mContacts;
    private ArrayList<String> mphoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListView = (ListView) findViewById(R.id.lv_main);
//adding contact names to the array mContacts
        mContacts = new ArrayList<>();

        mContacts.add("Aai");
        mContacts.add("Bapu");
        mContacts.add("Dada");
        mContacts.add("Jyotin");
        mContacts.add("Bakula");
        mContacts.add("Nidhi");
        mContacts.add("Jay");

        mphoneno = new ArrayList<>();
//adding contact no to array mphone
        mphoneno.add("8182345547");
        mphoneno.add("8182345477");
        mphoneno.add("8185423479");
        mphoneno.add("8108234747");
        mphoneno.add("8108044444");
        mphoneno.add("8652251006");
        mphoneno.add("8652251112");

        mListView.setAdapter(new MyAdapter());
        registerForContextMenu(mListView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select the Action");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {

            case R.id.op1:
                //op1 is for call
                Intent i = new Intent();
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+ mphoneno));
                startActivity(i);

                return true;

            case R.id.op2:
                //op2 is for sms
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", mContacts);
                startActivity(smsIntent);


                return true;

            default: return super.onContextItemSelected(item);
        }
    }

    class MyAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View rowview;

        if(convertView != null)
        {
            rowview=convertView;
        }

        else
        {
            rowview = getLayoutInflater().inflate(R.layout.list_row_view, parent, false);
        }

        TextView contactname = (TextView) rowview.findViewById(R.id.name_list);
        contactname.setText(mContacts.get(i));

        TextView phoneno = (TextView) rowview.findViewById(R.id.phone_list);
        phoneno.setText(mphoneno.get(i));

        return rowview;
    }
}
}
