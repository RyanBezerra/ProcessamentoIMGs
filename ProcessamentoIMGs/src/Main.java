import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String caminhoImagem = "C:\\Users\\Ryan\\Desktop\\image 1.png"; // Use double backslashes in the file path

        BufferedImage imagem = abrirImagem(caminhoImagem);

        if (imagem != null) {
            // Exibir informações da imagem
            int largura = imagem.getWidth();
            int altura = imagem.getHeight();
            int totalPixels = largura * altura;

            System.out.println("Largura da imagem: " + largura + " pixels");
            System.out.println("Altura da imagem: " + altura + " pixels");
            System.out.println("Quantidade total de pixels: " + totalPixels);

            String nomeImagem = "C:\\Users\\Ryan\\Desktop\\image 1.png"; // Remove file extension from the file name
            String formatoImagem = "png"; // Enclose the image format in double quotes

            // Salvar a imagem
            salvarImagem(imagem, nomeImagem, formatoImagem);

            System.out.println("Imagem salva com sucesso!");
        } else {
            System.out.println("Não foi possível abrir a imagem.");
        }

        scanner.close();
    }

    private static BufferedImage abrirImagem(String caminho) {
        try {
            return ImageIO.read(new File(caminho));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void salvarImagem(BufferedImage imagem, String nome, String formato) {
        try {
            ImageIO.write(imagem, formato, new File(nome + "." + formato.toLowerCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
