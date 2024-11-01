// import java.util.Scanner;

// public class MainApp {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         DatabaseHelper dbHelper = new DatabaseHelper();

//         System.out.print("Digite o caminho do vídeo: ");
//         String videoPath = scanner.nextLine();

//         System.out.print("Digite o nome do GIF (sem extensão): ");
//         String gifName = scanner.nextLine();

//         String outputGifPath = gifName + ".gif";

//         // Converte o vídeo para GIF
//         boolean conversionSuccess = VideoConverter.convertVideoToGif(videoPath, outputGifPath);
//         if (conversionSuccess) {
//             System.out.println("Vídeo convertido para GIF com sucesso!");

//             // Insere as informações do GIF no banco de dados
//             dbHelper.insertGif(gifName, outputGifPath);
//             System.out.println("Informações do GIF salvas no banco de dados.");

//             // Lista todos os GIFs no banco de dados
//             dbHelper.listGifs();
//         } else {
//             System.out.println("Falha na conversão do vídeo para GIF.");
//         }

//         scanner.close();
//     }
// }
