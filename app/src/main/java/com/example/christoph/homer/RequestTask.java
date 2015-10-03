package com.example.christoph.homer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by Christoph on 03.10.2015.
 */


/*
Syntax for an request
new RequestTask().execute("http://stackoverflow.com");

 */

class RequestTask extends AsyncTask<String, String, String>{

    private static final String TAG = "RequestTask";
    private MainActivity mainActivity;
    private boolean isInInitialisation;

    public RequestTask(boolean isInInitialisation,MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.isInInitialisation = isInInitialisation;
    }


    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            Log.i(TAG,"Execute httpget");
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                Log.i(TAG,"Got valid response");
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                Log.e(TAG, "Error in status code, got: " + statusLine.getStatusCode());
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            Log.e(TAG,"ClientProtocolException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(TAG, "IOException");
            e.printStackTrace();
        }
        catch (Exception e){
            Log.e(TAG,"Error in request Tastk: "+e.toString());
            e.printStackTrace();
        }

        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
      //  super.onPostExecute(result);
        Log.i(TAG, "Replay arrived");
        Log.i(TAG, result);
        AnswerParser ap = new AnswerParser();
        ap.parse(result);

        if(isInInitialisation){
            mainActivity.exitFormAndGoToSwiping();
        }

    }
}