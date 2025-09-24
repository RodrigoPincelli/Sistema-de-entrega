import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String ARQ_EVENTOS = "eventos.dat";
    private static final String ARQ_USUARIOS = "usuarios.dat";

    public static void salvarEventos(List<Evento> eventos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQ_EVENTOS))) {
            oos.writeObject(eventos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Evento> carregarEventos() {
        File f = new File(ARQ_EVENTOS);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Evento>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar eventos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQ_USUARIOS))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> carregarUsuarios() {
        File f = new File(ARQ_USUARIOS);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
