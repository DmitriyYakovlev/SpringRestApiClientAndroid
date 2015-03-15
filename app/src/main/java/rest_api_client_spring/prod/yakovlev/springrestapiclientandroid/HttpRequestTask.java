package rest_api_client_spring.prod.yakovlev.springrestapiclientandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Dimasta on 15.03.2015.
 */
public class HttpRequestTask  extends AsyncTask<Void, Void, Greeting> {

    private RequestCallback callback;
    private Context context;

    public HttpRequestTask(RequestCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.beforeRequestTask();
    }

    @Override
    protected Greeting doInBackground(Void... params) {
        try {
            final String url = "http://rest-service.guides.spring.io/greeting";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            Greeting greeting = restTemplate.getForObject(url, Greeting.class);
            return greeting;
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Greeting greeting) {
        callback.afterRequest(greeting);
    }

}