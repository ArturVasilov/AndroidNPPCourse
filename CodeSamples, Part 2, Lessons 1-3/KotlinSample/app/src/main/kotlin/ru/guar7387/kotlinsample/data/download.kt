package ru.guar7387.kotlinsample.data

import ru.guar7387.kotlinsample.EarthquakerService
import java.util.ArrayList
import java.net.URL
import org.apache.http.message.BasicNameValuePair
import org.json.JSONObject
import ru.guar7387.kotlinsample.log
import java.io.InputStream
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.params.HttpParams
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.params.BasicHttpParams
import org.apache.http.params.HttpConnectionParams
import org.apache.http.HttpResponse
import java.io.UnsupportedEncodingException
import org.apache.http.client.ClientProtocolException
import java.io.IOException
import java.io.InputStreamReader
import java.io.BufferedReader

val TAG = "downloading earthquakes"

public fun EarthquakerService.downloadEarthquakes(request: String) :
        ArrayList<Earthquake> {
    val builder = StringBuilder();
    log(TAG, "Request. Params - " + builder.toString());
    val inputStream : InputStream;
    try {
        val url = request
        val httpClient = DefaultHttpClient();
        val httpPost = HttpPost(url);

        httpPost.setEntity(UrlEncodedFormEntity(listOf(), "UTF-8"));

        val httpParams = BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 7000);
        httpClient.setParams(httpParams);

        val httpResponse = httpClient.execute(httpPost);

        val httpEntity = httpResponse.getEntity();

        inputStream = httpEntity.getContent();
    } catch (e: UnsupportedEncodingException) {
        log(TAG, "UnsupportedEncodingException during sending request");
        return ArrayList()
    } catch (e: ClientProtocolException) {
        log(TAG, "ClientProtocolException during sending request");
        return ArrayList()
    } catch (e: IOException) {
        log(TAG, "IOException during sending request");
        return ArrayList()
    } catch (e: Exception) {
        log(TAG, "Exception during sending request");
        return ArrayList()
    }

    try {
        val reader = BufferedReader(InputStreamReader(inputStream, "utf-8"), 8);
        val sb = StringBuilder();
        var line = reader.readLine();
        while (line != null) {
            sb.append(line).append("\n");
            line = reader.readLine();
        }
        inputStream.close();
        log(TAG, "answer - " + sb)
        val json = JSONObject(sb.toString())
        return parseJson(json)
    } catch (e: Exception) {
        log(TAG, "Buffer error, converting result " + e.toString());
        return ArrayList()
    }
}

fun parseJson(json: JSONObject): ArrayList<Earthquake> {
    log("downloading earthquakes", "Result json - " + json)
    val result = ArrayList<Earthquake>()
    val features = json.getJSONArray("features")
    for (index in 0..features.length() - 1) {
        val props = (features[index] as JSONObject).getJSONObject("properties")
        result.add(Earthquake(props.getLong("time"), props.getInt("mag"), props.getString("place")))
    }
    log(TAG, features.toString())
    return result
}



