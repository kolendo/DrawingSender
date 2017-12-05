package wkolendo.drawingsender;

import android.os.Bundle;

import com.humandevice.android.mvpframework.PresenterActivity;

import wkolendo.drawingsender.views.fragments.PaintFragment;

public class PaintActivity extends PresenterActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_only);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.fragment_container, PaintFragment.newInstance()).commit();
		}
	}
}
