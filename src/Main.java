import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        try {
            // Carregar a imagem
            File file = new File("Imagens\\download.png");
            BufferedImage image = ImageIO.read(file);

            // Obter largura e altura da imagem
            int largura = image.getWidth();
            int altura = image.getHeight();

            // Calcular quantidade total de pixels
            int totalPixels = largura * altura;

            // Pintar o primeiro pixel de azul
            image.setRGB(0, 0, Color.BLUE.getRGB());

            // Pintar o pixel central de verde
            int centroX = largura / 2;
            int centroY = altura / 2;
            image.setRGB(centroX, centroY, Color.GREEN.getRGB());

            // Pintar o último pixel de vermelho
            int ultimoX = largura - 1;
            int ultimoY = altura - 1;
            image.setRGB(ultimoX, ultimoY, Color.RED.getRGB());

            // Percorrer a imagem pixel a pixel e exibir o RGB de cada pixel
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    System.out.println("Pixel (" + x + ", " + y + "): RGB = (" + red + ", " + green + ", " + blue + ")");
                }
            }

            // Salvar a imagem com as alterações
            File outputFile = new File("Imagens\\modificado.png");
            ImageIO.write(image, "png", outputFile);

            System.out.println("Imagem salva com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
