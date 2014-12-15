package com.example.myresume;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AcademicsFragment extends Fragment {

	private String[] levels = { "SSLC", "PUC", "B.E" };
	String[] qualification_details;

	Spinner spinner_qualification;
	TextView tvQualificationDetails;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.academics_fragment,
				container, false);

		qualification_details = getActivity().getResources().getStringArray(
				R.array.qualification_details);

		tvQualificationDetails = (TextView) rootView.findViewById(
				R.id.tvQualificationDetails);

		spinner_qualification = (Spinner) rootView
				.findViewById(R.id.spinner_qualification);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, levels);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_qualification.setAdapter(adapter);

		spinner_qualification
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						spinner_qualification.setSelection(position);
						((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
						switch (position) {
						case 0:
							tvQualificationDetails
									.setText(qualification_details[0]);
							break;
						case 1:
							tvQualificationDetails
									.setText(qualification_details[1]);
							break;
						case 2:
							tvQualificationDetails
									.setText(qualification_details[2]);
							break;
						default:
							break;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});

		return rootView;
	}
}
