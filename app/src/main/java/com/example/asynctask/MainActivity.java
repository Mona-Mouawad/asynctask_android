package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnDownload ;
   ImageView imageView ;
   EditText Url_EditText ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Url_EditText =(EditText) findViewById(R.id.editText_URL);
        imageView =(ImageView) findViewById(R.id.imageView);
        btnDownload =(Button) findViewById(R.id.button_DownLoad);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadImage downloadImage= new DownloadImage();
                downloadImage.execute(Url_EditText.getText().toString());
            }
        });
    }

    Bitmap Download (String Url)
    {
        Bitmap result = null ;
        URL urlObject = null ;
        HttpURLConnection httpURLConnection ;
        InputStream inputStream = null ;
        try {
            urlObject = new URL(Url);
            httpURLConnection =(HttpURLConnection) urlObject.openConnection();
            httpURLConnection.connect();
            inputStream =httpURLConnection.getInputStream();
            result = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return     result ;
    }
    
    class  DownloadImage extends AsyncTask<String,Void , Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... url) {
            Bitmap result = null ;
            Download (url[0]);
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            Toast.makeText(MainActivity.this, "Downloaded Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}