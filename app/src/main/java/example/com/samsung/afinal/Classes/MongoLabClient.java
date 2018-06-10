package example.com.samsung.afinal.Classes;

import android.os.AsyncTask;
import android.util.Log;

import com.mongodb.BasicDBList;

import org.bson.BasicBSONCallback;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by samsung on 4/13/2018.
 */

public class MongoLabClient extends AsyncTask<String, Void, String> {
    final String baseUrl = "https://api.mlab.com/api/1/";
    private String apiKey;
    private String database;
    private String collection;
    private URL url = null;
    private HttpURLConnection httpURLConnection = null;
    private String requestMethod;
    private boolean doOutputMethod;
    private String query;
    private String outputData;

    String response = null;

    public MongoLabClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public MongoLabClient(String apiKey, String database) {
        this.apiKey = apiKey;
        this.database = database;
    }

    public MongoLabClient(String apiKey, String database, String collection) {
        this.apiKey = apiKey;
        this.database = database;
        this.collection = collection;
    }

    public void setPrivateApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setPrivateDatabase(String database) {
        this.database = database;
    }

    public void setPrivateCollection(String collection) {
        this.collection = collection;
    }

    public String getPrivateApiKeyS() {
        return this.apiKey;
    }

    public String getPrivateDatabase() {
        return this.database;
    }

    public String getPrivateCollection() {
        return this.collection;
    }

    private String getApiKey() {
        return "apiKey=" + apiKey;
    }

    private String getBaseURL() {
        return baseUrl;
    }

    private URL toURL(String urlString) {
        URL tempUrl = null;
        try {
            tempUrl = new URL(urlString);
            URI uri = new URI(tempUrl.getProtocol(), tempUrl.getUserInfo(), tempUrl.getHost(),
                    tempUrl.getPort(), tempUrl.getPath(), tempUrl.getQuery(), tempUrl.getRef());
            tempUrl = uri.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return tempUrl;
    }

    private String getDatabaseNameURL() {
        return getBaseURL() + "databases" + "?" + getApiKey();
    }

    public String getDatabaseName() {
        try {
            this.requestMethod = "GET";
            this.url = toURL(getDatabaseNameURL());
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getCollectionNameURL() {
        return getBaseURL() + "databases/" + database + "/collections" + "?" + getApiKey();
    }

    public String getCollectionName() {
        try {
            this.requestMethod = "GET";
            this.url = toURL(getCollectionNameURL());
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getCollectionNameURL(String database) {
        return getBaseURL() + "databases/" + database + "/collections" + "?" + getApiKey();
    }

    public String getCollectionName(String database) {
        try {
            this.requestMethod = "GET";
            this.url = toURL(getCollectionNameURL(database));
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String findURL() {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?" + getApiKey();
    }

    public String find() {
        try {
            this.requestMethod = "GET";
            this.url = toURL(findURL());
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String findURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&" + getApiKey();
    }

    public String find(String query) {
        try {
            this.requestMethod = "GET";
            this.url = toURL(findURL(query));
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String find(JSONObject query) {
        try {
            this.requestMethod = "GET";
            this.url = toURL(findURL(query.toString()));
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String findOneURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&fo=true&" + getApiKey();
    }

    public String findOne(String query) {
        try {
            this.requestMethod = "GET";
            this.url = toURL(findOneURL(query));
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String findOne(JSONObject query) {
        try {
            this.requestMethod = "GET";
            this.url = toURL(findOneURL(query.toString()));
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getCountURL() {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?" + "&c=true&" + getApiKey();
    }

    public int count() {
        try {
            this.requestMethod = "GET";
            this.url = toURL(getCountURL());
            response = execute().get();
            Log.e("count", Integer.parseInt(response)+" ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(response);
    }

    private String getCountURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&c=true&" + getApiKey();
    }

    public int count(String query) {
        try {
            this.requestMethod = "GET";
            this.url = toURL(getCountURL(query));
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(response);
    }

    public int count(JSONObject query) {
        try {
            this.requestMethod = "GET";
            this.url = toURL(getCountURL(query.toString()));
            response = execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(response);
    }

    private String getInsertURL() {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?" + getApiKey();
    }

    public void insert(JSONObject newData) {
        this.requestMethod = "POST";
        this.outputData = newData.toString();
        this.url = toURL(getInsertURL());
        execute();
    }

    public void insert(String newData) {
        this.requestMethod = "POST";
        this.outputData = newData;
        this.url = toURL(getInsertURL());
        execute();
    }

    public void insert(JSONArray newData) {
        this.requestMethod = "POST";
        this.outputData = newData.toString();
        this.url = toURL(getInsertURL());
        execute();
    }

    private String updateURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&" + getApiKey();
    }

    public void update(String searchQuery, String newQuery) {
        this.requestMethod = "PUT";
        this.outputData = newQuery;
        this.url = toURL(updateURL(searchQuery));
        execute();
    }

    public void update(String searchQuery, JSONObject newQuery) {
        this.requestMethod = "PUT";
        this.outputData = newQuery.toString();
        this.url = toURL(updateURL(searchQuery));
        execute();
    }

    public void update(JSONObject searchQuery, String newQuery) {
        this.requestMethod = "PUT";
        this.outputData = newQuery;
        this.url = toURL(updateURL(searchQuery.toString()));
        execute();
    }

    public void update(JSONObject searchQuery, JSONObject newQuery) {
        this.requestMethod = "PUT";
        this.outputData = newQuery.toString();
        this.url = toURL(updateURL(searchQuery.toString()));
        execute();
    }

    private String deleteURL(String documentId) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "/" + documentId
                + "?" + getApiKey();
    }

    public void delete(String documentId) {
        this.requestMethod = "DELETE";
        this.url = toURL(deleteURL(documentId));
        execute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Log.e("MongoLab API URL : ", url.toString());
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            if (requestMethod.equals("GET")) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                response = bufferedReader.readLine();
                bufferedReader.close();
                Log.e("MongoLab Process", "Getting Data..");
            } else if (requestMethod.equals("POST") || requestMethod.equals("PUT")) {
                OutputStreamWriter outputStreamWriter =
                        new OutputStreamWriter(httpURLConnection.getOutputStream());
                outputStreamWriter.write(outputData);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                Log.e("MongoLab Process", "Sending Data..");
            } else {
                Log.e("MongoLab Process", "Deleting Data..");
            }
            Log.e("MongoLab Status Code", String.valueOf(httpURLConnection.getResponseCode()));
            Log.e("MongoLab Status Message", httpURLConnection.getResponseMessage());
            if (requestMethod.equals("GET")) {
                Log.e("MongoLab Response", response);
            } else if (requestMethod.equals("POST") || requestMethod.equals("PUT")) {
                Log.e("MongoLab Output Data", outputData);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
