package aero.panasonic.testespresso;

import android.content.Context;
import android.os.PowerManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class testEspressoTest {
    private static final String TAG = testEspressoTest.class.getSimpleName();
    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void createMainActivity(){
        Log.v(TAG, "in before");
        mMainActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testStatusClick(){
        onView(withId(R.id.status_button)).perform(click());
        onView(withId(R.id.status_t)).check(matches(withText("getDisplayState: 1")));
    }

    @Test
    public void testGetDisplayState(){
        Log.v(TAG, "I am testGetDisplayState");
        Context context = getContext();

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock pmWake = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, TAG);
        pmWake.acquire();

        assertEquals(mMainActivity.getDisplayState(), 1);
    }

    @Test
    public void useAppContext() throws Exception {
        Log.v(TAG, "I am in useAppContext");
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("aero.panasonic.testespresso", appContext.getPackageName());
    }

    private Context getContext(){
        return InstrumentationRegistry.getContext();
    }
}
