import java.io.*;
import java.util.Scanner;

abstract class Algorithm {

    abstract String encrypt(String message, int key);

    abstract String decrypt(String message, int key);
}

class shiftAlgo extends Algorithm {

    public String encrypt(String message, int key) {
        StringBuilder plainText = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                int shift = Character.isUpperCase(c) ? 65 : 97;
                plainText.append((char) (modulo(c - shift + key) + shift));
            } else {
                plainText.append(c);
            }
        }

        return plainText.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, -key);
    }

    private static int modulo(int x) {
        return (x % 26 + 26) % 26;
    }
}

class uniAlgo extends Algorithm {

    public String encrypt(String message, int key) {
        StringBuilder cipherText = new StringBuilder();

        for (char c : message.toCharArray()) {
            cipherText.append((char) (c + key));
        }

        return cipherText.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, -key);
    }
}

class Problem {
    int key = 0;
    String mode = "enc";
    String data = "";
    String inputFile = "";
    String outputFile = "";
    Algorithm algorithm = new shiftAlgo();

    public void execute() {
        readData();

        switch (this.mode) {
            case "enc":
                String cipherText = algorithm.encrypt(data, key);
                output(cipherText, outputFile);
                break;
            case "dec":
                String plainText = algorithm.decrypt(data, key);
                output(plainText, outputFile);
                break;
            default:
                System.err.println("unknown mode");
                System.exit(1);
        }
    }

    private static void output(String message, String outputFile) {
        if (outputFile.equals("")) {
            System.out.println(message);
        } else {
            File file = new File(outputFile);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(message);
            } catch (IOException e) {
                System.err.println("can't write to " + outputFile);
                System.exit(1);
            }
        }
    }

    private void readData() {
        if (data.equals("")) {  // prefer "-data" over "-in"
            String fileName = inputFile;
            File file = new File(fileName);
            try (Scanner scanner = new Scanner(file)) {
                data = scanner.nextLine();
            } catch (FileNotFoundException e) {
                System.err.println(fileName + " not found");
                System.exit(1);
            }
        }
    }

    public void arguments(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-alg":
                    switch (args[i + 1]) {
                        case "shift":
                            algorithm = new shiftAlgo();
                            break;
                        case "unicode":
                            algorithm = new uniAlgo();
                            break;
                        default:
                            System.err.println("unknown algorithm " + args[i + 1]);
                            System.exit(1);
                    }
                    break;
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-in":
                    inputFile = args[i + 1];
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-out":
                    outputFile = args[i + 1];
                    break;
                default:
                    System.err.println("unknown argument");
                    System.exit(1);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Problem probSolu = new Problem();
        probSolu.arguments(args);
        probSolu.execute();
    }
}