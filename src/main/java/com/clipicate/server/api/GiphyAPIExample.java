package com.clipicate.server.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GiphyAPIExample {
    private static final String API_KEY = "tVxh51oAvkYgkN4hn9PKV24wVXxkGDTj";  
    private static final String BASE_URL = "https://api.giphy.com/v1/gifs/search";

    public static void main(String[] args) {
        // Palavra-chave para buscar GIFs
        String query = "happy";  // Exemplo de busca
        int limit = 5;  // Número de GIFs a serem retornados

        // Chama o método para buscar GIFs
        searchGiphy(query, limit);
    }

    public static void searchGiphy(String query, int limit) {
        try {
            // Formata a URL da requisição com a chave da API, o termo de busca e o limite de resultados
            String urlString = BASE_URL + "?api_key=" + API_KEY + "&q=" + query + "&limit=" + limit;

            // Cria o objeto URL e estabelece uma conexão
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Lê a resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Resposta JSON da API Giphy: ");
            System.out.println(response.toString());

            parseGiphyResponse(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para processar e extrair dados do JSON retornado pela API
    public static void parseGiphyResponse(String jsonResponse) {
        try {
            // Usa o Gson para parsear o JSON e extrair os dados
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

            // Obtém o array de dados (GIFs) da resposta
            JsonArray gifs = jsonObject.getAsJsonArray("data");

            // Exibe informações sobre cada GIF
            for (int i = 0; i < gifs.size(); i++) {
                JsonObject gif = gifs.get(i).getAsJsonObject();
                String title = gif.get("title").getAsString();
                String gifUrl = gif.getAsJsonObject("images")
                                   .getAsJsonObject("original")
                                   .get("url").getAsString();

                System.out.println("Título: " + title);
                System.out.println("URL do GIF: " + gifUrl);
                System.out.println("----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
