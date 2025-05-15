package mx.edu.isc.tesoem.exap2bem;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {

    private View view;
    private String fragmentType;
    private int currentTextColor = Color.BLACK;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentType = getArguments().getString("fragment_type", "menu");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);

        switch (fragmentType) {
            case "menu":
                configurarMenu();
                view.setBackgroundColor(Color.RED);
                break;
            case "suma":
                configurarSuma();
                view.setBackgroundColor(Color.BLUE);
                break;
            case "resta":
                configurarResta();
                view.setBackgroundColor(Color.WHITE);
                break;
        }

        return view;
    }

    private void configurarMenu() {
        TextView title = view.findViewById(R.id.title);
        title.setText("MENU");
        title.setTextColor(currentTextColor);

        Button btn1 = view.findViewById(R.id.button1);
        btn1.setText("Cambiar color fragmento Actual");
        btn1.setVisibility(View.VISIBLE);
        btn1.setTextColor(currentTextColor);
        btn1.setOnClickListener(v -> {
            ((MainActivity)getActivity()).cambiarColorFragmentoActual();
        });

        Button btn2 = view.findViewById(R.id.button2);
        btn2.setText("Cambiar color fragmento Suma");
        btn2.setVisibility(View.VISIBLE);
        btn2.setTextColor(currentTextColor);
        btn2.setOnClickListener(v -> {
            ((MainActivity)getActivity()).cambiarColorSuma();
            ((MainActivity)getActivity()).cambiarTab(1);
        });

        Button btn3 = view.findViewById(R.id.button3);
        btn3.setText("Cambiar color fragmento Resta");
        btn3.setVisibility(View.VISIBLE);
        btn3.setTextColor(currentTextColor);
        btn3.setOnClickListener(v -> {
            ((MainActivity)getActivity()).cambiarColorResta();
            ((MainActivity)getActivity()).cambiarTab(2);
        });

        // Ocultar elementos no usados
        view.findViewById(R.id.text1).setVisibility(View.GONE);
        view.findViewById(R.id.text2).setVisibility(View.GONE);
        view.findViewById(R.id.edit1).setVisibility(View.GONE);
        view.findViewById(R.id.edit2).setVisibility(View.GONE);
        view.findViewById(R.id.calculateBtn).setVisibility(View.GONE);
        view.findViewById(R.id.result).setVisibility(View.GONE);
    }

    private void configurarSuma() {
        TextView title = view.findViewById(R.id.title);
        title.setText("Fragmento Suma");
        title.setTextColor(currentTextColor);

        TextView text1 = view.findViewById(R.id.text1);
        text1.setText("Primer número");
        text1.setTextColor(currentTextColor);
        text1.setVisibility(View.VISIBLE);

        TextView text2 = view.findViewById(R.id.text2);
        text2.setText("Segundo número");
        text2.setTextColor(currentTextColor);
        text2.setVisibility(View.VISIBLE);

        EditText edit1 = view.findViewById(R.id.edit1);
        edit1.setVisibility(View.VISIBLE);
        edit1.setTextColor(currentTextColor);

        EditText edit2 = view.findViewById(R.id.edit2);
        edit2.setVisibility(View.VISIBLE);
        edit2.setTextColor(currentTextColor);

        Button calculateBtn = view.findViewById(R.id.calculateBtn);
        calculateBtn.setText("Calcular");
        calculateBtn.setVisibility(View.VISIBLE);
        calculateBtn.setTextColor(currentTextColor);
        calculateBtn.setOnClickListener(v -> calcularResultado(true));

        // Ocultar botones del menú
        view.findViewById(R.id.button1).setVisibility(View.GONE);
        view.findViewById(R.id.button2).setVisibility(View.GONE);
        view.findViewById(R.id.button3).setVisibility(View.GONE);
    }

    private void configurarResta() {
        TextView title = view.findViewById(R.id.title);
        title.setText("Fragmento Resta");
        title.setTextColor(currentTextColor);

        TextView text1 = view.findViewById(R.id.text1);
        text1.setText("Primer número");
        text1.setTextColor(currentTextColor);
        text1.setVisibility(View.VISIBLE);

        TextView text2 = view.findViewById(R.id.text2);
        text2.setText("Segundo número");
        text2.setTextColor(currentTextColor);
        text2.setVisibility(View.VISIBLE);

        EditText edit1 = view.findViewById(R.id.edit1);
        edit1.setVisibility(View.VISIBLE);
        edit1.setTextColor(currentTextColor);

        EditText edit2 = view.findViewById(R.id.edit2);
        edit2.setVisibility(View.VISIBLE);
        edit2.setTextColor(currentTextColor);

        Button calculateBtn = view.findViewById(R.id.calculateBtn);
        calculateBtn.setText("Calcular");
        calculateBtn.setVisibility(View.VISIBLE);
        calculateBtn.setTextColor(currentTextColor);
        calculateBtn.setOnClickListener(v -> calcularResultado(false));

        // Ocultar botones del menú
        view.findViewById(R.id.button1).setVisibility(View.GONE);
        view.findViewById(R.id.button2).setVisibility(View.GONE);
        view.findViewById(R.id.button3).setVisibility(View.GONE);
    }

    private void calcularResultado(boolean esSuma) {
        EditText edit1 = view.findViewById(R.id.edit1);
        EditText edit2 = view.findViewById(R.id.edit2);
        TextView result = view.findViewById(R.id.result);

        try {
            double num1 = Double.parseDouble(edit1.getText().toString());
            double num2 = Double.parseDouble(edit2.getText().toString());
            double res = esSuma ? num1 + num2 : num1 - num2;
            result.setText("Resultado: " + res);
        } catch (NumberFormatException e) {
            result.setText("Ingrese números válidos");
        }
        result.setTextColor(currentTextColor);
        result.setVisibility(View.VISIBLE);
    }

    public void cambiarColorFondo(int color) {
        if (view != null) {
            view.setBackgroundColor(color);
        }
    }

    public void cambiarColorTexto(int color) {
        currentTextColor = color;

        TextView title = view.findViewById(R.id.title);
        if (title != null) title.setTextColor(color);

        Button btn1 = view.findViewById(R.id.button1);
        if (btn1 != null && btn1.getVisibility() == View.VISIBLE) btn1.setTextColor(color);

        Button btn2 = view.findViewById(R.id.button2);
        if (btn2 != null && btn2.getVisibility() == View.VISIBLE) btn2.setTextColor(color);

        Button btn3 = view.findViewById(R.id.button3);
        if (btn3 != null && btn3.getVisibility() == View.VISIBLE) btn3.setTextColor(color);

        TextView text1 = view.findViewById(R.id.text1);
        if (text1 != null && text1.getVisibility() == View.VISIBLE) text1.setTextColor(color);

        TextView text2 = view.findViewById(R.id.text2);
        if (text2 != null && text2.getVisibility() == View.VISIBLE) text2.setTextColor(color);

        EditText edit1 = view.findViewById(R.id.edit1);
        if (edit1 != null && edit1.getVisibility() == View.VISIBLE) edit1.setTextColor(color);

        EditText edit2 = view.findViewById(R.id.edit2);
        if (edit2 != null && edit2.getVisibility() == View.VISIBLE) edit2.setTextColor(color);

        Button calculateBtn = view.findViewById(R.id.calculateBtn);
        if (calculateBtn != null && calculateBtn.getVisibility() == View.VISIBLE) calculateBtn.setTextColor(color);

        TextView result = view.findViewById(R.id.result);
        if (result != null) result.setTextColor(color);
    }
}