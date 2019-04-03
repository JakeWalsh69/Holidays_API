package com.example.holiday_app_android;

import android.content.Context;

import com.example.holiday_app_android.adapters.HolidayAdapter;
import com.example.holiday_app_android.clients.HolidayRestClient;
import com.example.holiday_app_android.models.Holiday;
import com.loopj.android.http.JsonHttpResponseHandler;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("ALL")
@RunWith(AndroidJUnit4.class)
public class RestDataTest extends AppCompatActivity {
    // Creating Holiday ArrayLists and Objects to populate the ArrayLists
    private final ArrayList<Holiday> allHolidays = new ArrayList<>();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private ArrayList<Holiday> holidayList;
    private final Holiday h1 = new Holiday("Easter", "Sunday, 21 April");
    private final Holiday h2 = new Holiday("Father's Day", "Sunday, 16 June");
    private final Holiday h3 = new Holiday("Good Friday", "Friday, 19 April");
    private final Holiday h4 = new Holiday("Easter Monday", "Monday, 22 April");
    private final Holiday h5 = new Holiday("May Bank Holiday", "Monday, 06 May");
    private final Holiday h6 = new Holiday("June Bank Holiday", "Monday, 03 June");
    private final Holiday h7 = new Holiday("August Bank Holiday", "Monday, 05 August");
    private final Holiday h8 = new Holiday("October Bank Holiday", "Monday, 28 October");
    private final Holiday h9 = new Holiday("Christmas Day", "Wednesday, 25 December");
    private final Holiday h10 = new Holiday("St. Stephens Day", "Thursday, 26 December");

    @Before
    public void setUp()
    {
        // Populating the arrayList to test values against it
        allHolidays.add(h1);
        allHolidays.add(h2);
        allHolidays.add(h3);
        allHolidays.add(h4);
        allHolidays.add(h5);
        allHolidays.add(h6);
        allHolidays.add(h7);
        allHolidays.add(h8);
        allHolidays.add(h9);
        allHolidays.add(h10);
    }

    // This Method is created to populate arrayList with Holiday data
    // from the REST API to be used in tests.
    public void loadHolidayData()
    {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));

        HolidayRestClient.get(MainActivity.class.cast(Context.class), "api/Holidays", headers.toArray(new Header[0]),
                null, new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response)
                    {
                        ArrayList<Holiday> holidayArray = new ArrayList<>();
                        HolidayAdapter holidayAdapter = new HolidayAdapter(MainActivity.class.cast(Context.class), holidayArray);

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                holidayAdapter.add(new Holiday(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @SuppressWarnings("deprecation")
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.holiday_app_android", appContext.getPackageName());
    }

    // Method to loop through both arrayLists and validate that data is the same
    @Test
    public void checkHolidayNameAndDate()
    {
        loadHolidayData();
        for (int i = 0; i < holidayList.size(); i++)
        {
            assertSame(allHolidays.get(i).getName(), holidayList.get(i).getName());
        }
    }
}
