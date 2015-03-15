package rest_api_client_spring.prod.yakovlev.springrestapiclientandroid;

/**
 * Created by Dimasta on 15.03.2015.
 */
public interface RequestCallback {

    public void beforeRequestTask();

    public void afterRequest(Greeting greeting);


}
