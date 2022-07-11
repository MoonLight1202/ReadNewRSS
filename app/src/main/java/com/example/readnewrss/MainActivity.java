package com.example.readnewrss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtNoiDung;
    private ListView lvNews;
    ArrayList<NewsItem> arrayNewsItem;
    NewsItemAdapter newsItemAdapter;
    String mota= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        new LoadMaNguon().execute("https://vnexpress.net/rss/so-hoa.rss");
        newsItemAdapter = new NewsItemAdapter(this, R.layout.dong_news,arrayNewsItem);
        lvNews.setAdapter(newsItemAdapter);
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("linkBai",arrayNewsItem.get(i).getLinkbai());
                startActivity(intent);
            }
        });
    }
    public class LoadMaNguon extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line+"\n");
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParse xmldomParse = new XMLDOMParse();
            Document document = xmldomParse.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String htmlDesc = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                htmlDesc = xmldomParse.getValueDesc(element,"description");
                arrayNewsItem.add(new NewsItem(xmldomParse.getValue(element,"title"),xmldomParse.getDescContent(htmlDesc),xmldomParse.getValue(element,"link"),xmldomParse.getImageLink(htmlDesc)));
            }
            newsItemAdapter.notifyDataSetChanged();
        }
    }
    private void Mapping() {
        lvNews = (ListView) findViewById(R.id.lvNews);
        txtNoiDung = (TextView) findViewById(R.id.txtTitle);
        arrayNewsItem = new ArrayList<>();
    }
}