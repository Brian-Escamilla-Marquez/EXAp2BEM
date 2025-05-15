package mx.edu.isc.tesoem.exap2bem;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        // Crear pestañas
        tabHost.addTab(tabHost.newTabSpec("Menu").setIndicator("Menu"), BlankFragment.class, createBundle("menu"));
        tabHost.addTab(tabHost.newTabSpec("Suma").setIndicator("Suma"), BlankFragment.class, createBundle("suma"));
        tabHost.addTab(tabHost.newTabSpec("Resta").setIndicator("Resta"), BlankFragment.class, createBundle("resta"));
    }

    private Bundle createBundle(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("fragment_type", type);
        return bundle;
    }

    public void cambiarColorFragmentoActual() {
        BlankFragment currentFragment = (BlankFragment) getSupportFragmentManager()
                .findFragmentByTag(tabHost.getCurrentTabTag());
        if (currentFragment != null) {
            currentFragment.cambiarColorFondo(Color.GREEN);
            currentFragment.cambiarColorTexto(Color.WHITE);
        }
    }

    public void cambiarColorSuma() {
        int colorFondo = generarColorAleatorio();
        int colorTexto = obtenerColorTextoContrastante(colorFondo);

        BlankFragment sumaFragment = (BlankFragment) getSupportFragmentManager()
                .findFragmentByTag("Suma");
        if (sumaFragment != null) {
            sumaFragment.cambiarColorFondo(colorFondo);
            sumaFragment.cambiarColorTexto(colorTexto);
        }
    }

    public void cambiarColorResta() {
        int colorFondo = generarColorAleatorio();
        int colorTexto = obtenerColorTextoContrastante(colorFondo);

        BlankFragment restaFragment = (BlankFragment) getSupportFragmentManager()
                .findFragmentByTag("Resta");
        if (restaFragment != null) {
            restaFragment.cambiarColorFondo(colorFondo);
            restaFragment.cambiarColorTexto(colorTexto);
        }
    }

    public void cambiarTab(int tab) {
        tabHost.setCurrentTab(tab);
    }

    private int generarColorAleatorio() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private int obtenerColorTextoContrastante(int colorFondo) {
        double brightness = Color.red(colorFondo) * 0.299 +
                Color.green(colorFondo) * 0.587 +
                Color.blue(colorFondo) * 0.114;
        return brightness > 186 ? Color.BLACK : Color.WHITE;
    }
}