import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        try {
            String apiKey = "26bdd66c728cb9a3092e9c22"; // Reemplaza "TU_API_KEY" con tu propia clave de API



            Scanner leer = new Scanner(System.in);
            int option = 0;
            while (option != 7){
                // Mostrar el menú
                System.out.println("**************************************************");
                System.out.println("Sea bienvenido/a  al conversor de moneda\n");

                System.out.println("1) Dolar -> Peso Argentino");
                System.out.println("2) Peso Argentino -> Dolar");
                System.out.println("3) Dolar -> Real brasileño");
                System.out.println("4) Real brasileño -> Dolar");
                System.out.println("5) Dolar -> Peso Colombiano");
                System.out.println("6) Peso Colombiano -> Dolar");
                System.out.println("7) Salir\n");


                System.out.print("Elija una opción válida: ");


                try {

                    option = Integer.parseInt(leer.nextLine());
                    System.out.println("**************************************************");

                } catch (NumberFormatException e) {
                    option = 0;
                }


                // Procesar la opción seleccionada

                switch (option) {


                    case 1:
                        System.out.println("Ingresa el valor  que deseas convertir. ");
                        double monto = Integer.parseInt(leer.nextLine());

                        ConvertidorDivisas("USD", "ARS", monto);

                        break;
                    case 2:
                        System.out.println("Ingresa el valor que deseas convertir. ");
                        monto = Integer.parseInt(leer.nextLine());
                        ConvertidorDivisas("ARS", "USD", monto);
                        break;
                    case 3:
                        System.out.println("Ingresa el valor que deseas convertir. ");
                        monto = Integer.parseInt(leer.nextLine());
                        ConvertidorDivisas("USD", "BRL", monto);
                        break;
                    case 4:
                        System.out.println("Ingresa el valor que deseas convertir. ");
                        monto = Integer.parseInt(leer.nextLine());
                        ConvertidorDivisas("BRL", "USD", monto);
                        break;
                    case 5:
                        System.out.println("Ingresa el valor que deseas convertir. ");
                        monto = Integer.parseInt(leer.nextLine());
                        ConvertidorDivisas("USD", "COP", monto);
                        break;
                    case 6:
                        System.out.println("Ingresa el valor que deseas convertir. ");
                        monto = Integer.parseInt(leer.nextLine());
                        ConvertidorDivisas("COP", "USD", monto);

                        break;
                    case 7:
                        System.out.println("Gracias por usar el conversor de moneda. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Por favor, seleccione una opción válida.\n");
                }
            }



        } catch (IOException |InterruptedException | NumberFormatException e) {
            e.getMessage();
        }
    }

    private static void ConvertidorDivisas(String monedaInicial, String monedaFinal, double monto) throws IOException, InterruptedException {

        String direccion = "https://v6.exchangerate-api.com/v6/26bdd66c728cb9a3092e9c22/pair/"+monedaInicial+"/"+monedaFinal+"/"+monto;

        // prueba de nueva conexion

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        //System.out.printf(json);

        // leer con Gson prueba

        Gson gson = new Gson();
        JsonObject obj =  gson.fromJson(json, JsonObject.class );
       // System.out.println(obj);
        double result = obj.get("conversion_result").getAsDouble();
       // System.out.println("Este es el final" + result);
        System.out.println("El valor  : " + monto + " [" + monedaInicial +"]  Corresponde al valor final de =>>> " + result + " [" + monedaFinal+"]");
        System.out.println();


//
    }
}
