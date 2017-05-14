package hu.bme.aut.mobsoft.lab.mobsoft.mock.interceptors;

import android.net.Uri;

import hu.bme.aut.mobsoft.lab.mobsoft.model.User;
import hu.bme.aut.mobsoft.lab.mobsoft.network.NetworkConfig;
import hu.bme.aut.mobsoft.lab.mobsoft.repository.MemoryRepository;
import hu.bme.aut.mobsoft.lab.mobsoft.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.aut.mobsoft.lab.mobsoft.mock.interceptors.MockHelper.bodyToString;
import static hu.bme.aut.mobsoft.lab.mobsoft.mock.interceptors.MockHelper.makeResponse;

public class UserMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "login") && request.method().equals("POST")) {
            String userString = bodyToString(request);
            User user = GsonHelper.getGson().fromJson(userString, User.class);
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            try{
                if(!memoryRepository.isInDB(user)) memoryRepository.saveUser(user);
                responseString = new Integer(user.getId()).toString();
                responseCode = 200;
            }
            catch(Exception ex){
                responseCode = 500;
                responseString = "Unexpected error happened.";
            }

        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
