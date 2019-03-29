package com.example.holiday_app_android;
import android.os.Bundle;
import com.example.holiday_app_android.adapters.HolidayAdapter;
import com.example.holiday_app_android.clients.HolidayRestClient;
import com.example.holiday_app_android.models.Holiday;
import com.loopj.android.http.JsonHttpResponseHandler;
import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView holidayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHolidays();
    }

    private void getHolidays()
    {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        HolidayRestClient.get(MainActivity.this, "api/Holidays", headers.toArray(new Header[headers.size()]),
                null, new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response)
                    {
                        ArrayList<Holiday> holidayArray = new ArrayList<Holiday>();
                        HolidayAdapter holidayAdapter = new HolidayAdapter(MainActivity.this, holidayArray);

                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                holidayAdapter.add(new Holiday(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        holidayList = (ListView) findViewById(R.id.list_holidays);
                        holidayList.setAdapter(holidayAdapter);
                    }
                });
    }

}
