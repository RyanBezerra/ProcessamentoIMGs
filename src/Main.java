import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            // Carregar a imagem
            File file = new File("Imagens\\download.png");
            BufferedImage image = ImageIO.read(file);

            // Aplicar alterações na imagem original
            BufferedImage firstModifiedImage = alterarImagemOriginal(image);

            // Salvar a imagem alterada como "modificado.png"
            salvarImagem(firstModifiedImage, "Imagens\\modificado.png");

            // Converter a imagem para escala de cinza
            BufferedImage greyscaleImage = converterParaEscalaDeCinza(image);

            // Salvar a imagem em escala de cinza como "modificado_greyscale.png"
            salvarImagem(greyscaleImage, "Imagens\\modificado_greyscale.png");

            // Salvar a imagem em escala de vermelho como "modificado_red.png"
            BufferedImage redScaleImage = converterParaEscalaColorida(image, 'r');
            salvarImagem(redScaleImage, "Imagens\\modificado_red.png");

            // Salvar a imagem em escala de verde como "modificado_green.png"
            BufferedImage greenScaleImage = converterParaEscalaColorida(image, 'g');
            salvarImagem(greenScaleImage, "Imagens\\modificado_green.png");

            // Salvar a imagem em escala de azul como "modificado_blue.png"
            BufferedImage blueScaleImage = converterParaEscalaColorida(image, 'b');
            salvarImagem(blueScaleImage, "Imagens\\modificado_blue.png");

            // Exibir o RGB de cada pixel na primeira imagem modificada
            exibirRGB(firstModifiedImage);

            System.out.println("Imagens salvas com sucesso!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Função para aplicar alterações na imagem original
    public static BufferedImage alterarImagemOriginal(BufferedImage image) {
        int largura = image.getWidth();
        int altura = image.getHeight();

        // Criar uma cópia da imagem original
        BufferedImage modifiedImage = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                modifiedImage.setRGB(x, y, image.getRGB(x, y));
            }
        }

        // Pintar o primeiro pixel de azul
        modifiedImage.setRGB(0, 0, 0x0000FF);

        // Pintar o pixel central de verde
        int centroX = largura / 2;
        int centroY = altura / 2;
        modifiedImage.setRGB(centroX, centroY, 0x00FF00);

        // Pintar o último pixel de vermelho
        int ultimoX = largura - 1;
        int ultimoY = altura - 1;
        modifiedImage.setRGB(ultimoX, ultimoY, 0xFF0000);

        return modifiedImage;
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

    // Função para converter a imagem para escala de cor especificada
    public static BufferedImage converterParaEscalaColorida(BufferedImage image, char color) {
        int largura = image.getWidth();
        int altura = image.getHeight();

        BufferedImage colorScaleImage = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Ajustar os valores de acordo com a cor especificada
                switch (color) {
                    case 'r':
                        red = red;
                        green = 0;
                        blue = 0;
                        break;
                    case 'g':
                        red = 0;
                        green = green;
                        blue = 0;
                        break;
                    case 'b':
                        red = 0;
                        green = 0;
                        blue = blue;
                        break;
                    default:
                        break;
                }

                int colorValue = (red << 16) | (green << 8) | blue;

                colorScaleImage.setRGB(x, y, colorValue);
            }
        }

        return colorScaleImage;
    }

    // Função para salvar a imagem
    public static void salvarImagem(BufferedImage image, String filePath) throws IOException {
        File outputFile = new File(filePath);
        ImageIO.write(image, "png", outputFile);
    }

    // Função para exibir o RGB de cada pixel na imagem
    public static void exibirRGB(BufferedImage firstModifiedImage) {
        int largura = firstModifiedImage.getWidth();
        int altura = firstModifiedImage.getHeight();

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb = firstModifiedImage.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                System.out.println("Pixel (" + x + ", " + y + "): RGB = (" + red + ", " + green + ", " + blue + ")");
            }
        }
    }

}
