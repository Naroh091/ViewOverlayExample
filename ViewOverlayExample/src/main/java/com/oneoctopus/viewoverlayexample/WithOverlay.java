package com.oneoctopus.viewoverlayexample;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

public class WithOverlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overlay);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new WO()).commit();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.with_overlay, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
			case R.id.action_settings:
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class WO extends Fragment {

		public WO() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.with_overlay, container, false);

			// Referenciamos el botón como final
			Button wejecuta = (Button) rootView.findViewById(R.id.wejecuta);

			// Obtenemos el cuadrado y el ViewGroup, que será el padre del cuadrado (que es donde lo queremos añadir)
			final RelativeLayout cuadrado = (RelativeLayout) rootView.findViewById(R.id.cuadrado);
			final ViewGroup padre = (ViewGroup) cuadrado.getParent();

			// Si lo pulsamos, pasa esto:
			wejecuta.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					// Añadimos el botón al elemento padre
					padre.getOverlay().add(cuadrado);

					// Creamos el interpolador para la animación
					OvershootInterpolator sOvershooter = new OvershootInterpolator(2f);


					// Animaciones para el cuadrado y un listener para detectar cambios en la animación.
					cuadrado.animate().setDuration(8000);
					cuadrado.animate().setInterpolator(sOvershooter).scaleX(7f).scaleY(7f).alpha(0).translationY(padre.getHeight()).setListener(new Animator.AnimatorListener() {

						/* Tras el fin de la animación (o su posible cancelación) eliminamos el TextView, que es lo que queríamos
						realizar. Cuando se añade a la Overlay se elimina de su padre, por lo cual sólo hace falta eliminarlo
						de la OverlayLayout. */

						@Override
						public void onAnimationStart(Animator animation) {
							// No nos interesa hacer nada cuando comienza.
						}

						@Override
						public void onAnimationEnd(Animator animation) {
							// Animación acabada, eliminamos el cuadrado
							padre.getOverlay().remove(cuadrado);
						}

						@Override
						public void onAnimationCancel(Animator animation) {
							// Animación cancelada, eliminamos el cuadrado
							padre.getOverlay().remove(cuadrado);
						}

						@Override
						public void onAnimationRepeat(Animator animation) {
							// No nos interesa hacer nada
						}
					});

					// Iniciamos animación
					cuadrado.animate().start();


				}
			});

			return rootView;
		}
	}

}
