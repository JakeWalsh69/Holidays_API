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
    private ListView bankHolidayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHolidays();
        getBankHolidays();
    }

    private void getHolidays()
    {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));

        HolidayRestClient.get(MainActivity.this, "api/Holidays", headers.toArray(new Header[0]),
                null, new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response)
                    {
                        ArrayList<Holiday> holidayArray = new ArrayList<>();
                        HolidayAdapter holidayAdapter = new HolidayAdapter(MainActivity.this, holidayArray);

                        for (int i = 0; i < response.length(); i++)
                        {
                            try {
                                holidayAdapter.add(new Holiday(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        holidayList = findViewById(R.id.list_holidays);
                        holidayList.setAdapter(holidayAdapter);
                    }
                });
    }

    private void getBankHolidays()
    {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));

        HolidayRestClient.get(MainActivity.this, "api/Holidays", headers.toArray(new Header[0]),
                null, new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response)
                    {
                        ArrayList<Holiday> holidayArray = new ArrayList<>();
                        HolidayAdapter holidayAdapter = new HolidayAdapter(MainActivity.this, holidayArray);

                        for (int i = 0; i < response.length(); i++)
                        {
                            if (i == 4 || i == 5 || i == 6 || i == 7)
                            try {
                                holidayAdapter.add(new Holiday(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        bankHolidayList = findViewById(R.id.list_bank_holidays);
                        bankHolidayList.setAdapter(holidayAdapter);
                    }
                });
    }

}
