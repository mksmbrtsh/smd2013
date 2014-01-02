package com.maximsblog.blogspot.com.smd2013;

import java.util.Locale;

import com.actionbarsherlock.view.Window;
import com.maximsblog.blogspot.com.smd2013.R;

import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutFragment extends DialogFragment {
	public static AboutFragment newInstance(String content) {
		AboutFragment fragment = new AboutFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle(R.string.about);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.about_fragment, container, false);
		try {
			String nameversion = getString(R.string.app_name)
					+ " ver. "
					+ getActivity().getPackageManager().getPackageInfo(
							getActivity().getPackageName(), 0).versionName;
			((TextView) layout.findViewById(R.id.version_txt))
					.setText(nameversion);
		} catch (NameNotFoundException e) {

		}
		TextView t = (TextView) layout.findViewById(R.id.textView1);

		t.setTextColor(Color.GRAY);
		Linkify.addLinks(t, Linkify.ALL);
		t.setMovementMethod(LinkMovementMethod.getInstance());
		Configuration sysConfig = getResources().getConfiguration();
		Locale curLocale = sysConfig.locale;
		if (curLocale.getDisplayLanguage().contains("русский"))
			t.setText(Html
					.fromHtml("Программа разработана специально для схемотехников.<br><br>Разработчик: <a href=\"mailto:mksmbtrsh@gmail.com\">Maksim Bartosh</a> (<a href=\"https://play.google.com/store/search?q=maksim+bartosh&amp;c=apps\">GooglePlay</a>)<br><br>Перевод: <a href=\"mailto:address.for.job@gmail.com\">Ксения</a>.<br><br>Дизайн иконки приложения: <a href=\"mailto:znachima@gmail.com\">Аня</a><br><br> Замечания, предложения, свои базы данных, сообщения об ошибках пишите <a href=\"mailto:mksmbtrsh@gmail.com\">мне</a>.<br><br>Для просмотра Datasheet необходим просмотрщик файлов формата PDF."));
		else
			t.setText(Html
					.fromHtml("The programme is specially made for circuit design engineers.<br><br>Programmer: <a href=\"mailto:mksmbtrsh@gmail.com\">Maksim Bartosh</a>(<a href=\"https://play.google.com/store/search?q=maksim+bartosh&amp;c=apps\">GooglePlay</a>)<br><br>Translated by <a href=\"mailto:address.for.job@gmail.com\">Ksenia</a><br><br>Icon launcher design: <a href=\"mailto:znachima@gmail.com\">Anna</a><br><br>You are welcome <a href=\"mailto:mksmbtrsh@gmail.com\">to send me</a> notes, offers, your database, error reports.<br><br>You need PDF-viewer for browsing Datasheet."));
		return layout;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}