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

            // Aplicar alterações na imagem original
            alterarImagemOriginal(image);

            // Salvar a imagem alterada como "modificado1.png"
            salvarImagem(image, "Imagens\\modificado1.png");

            // Converter a imagem para escala de cinza
            BufferedImage greyscaleImage = converterParaEscalaDeCinza(image);

            // Salvar a imagem em escala de cinza como "modificado2.png"
            salvarImagem(greyscaleImage, "Imagens\\modificado2.png");

            // Exibir o RGB de cada pixel na imagem original
            exibirRGB(image);

            System.out.println("Imagens salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para aplicar alterações na imagem original
    public static void alterarImagemOriginal(BufferedImage image) {
        int largura = image.getWidth();
        int altura = image.getHeight();

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
    }

    // Função para converter a imagem para escala de cinza
    public static BufferedImage converterParaEscalaDeCinza(BufferedImage image) {
        int largura = image.getWidth();
        int altura = image.getHeight();

        BufferedImage greyscaleImage = new BufferedImage(largura, altura, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Calcular a média dos valores RGB
                int media = (red + green + blue) / 3;

                // Definir o mesmo valor de cinza para os três componentes RGB
                int greyValue = (media << 16) | (media << 8) | media;

                greyscaleImage.setRGB(x, y, greyValue);
            }
        }

        return greyscaleImage;
    }

    // Função para salvar a imagem
    public static void salvarImagem(BufferedImage image, String filePath) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(image, "png", outputFile);
    }

    // Função para exibir o RGB de cada pixel na imagem original
    public static void exibirRGB(BufferedImage image) {
        int largura = image.getWidth();
        int altura = image.getHeight();

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                System.out.println("Pixel (" + x + ", " + y + "): RGB = (" + red + ", " + green + ", " + blue + ")");
            }
        }
    }
}
