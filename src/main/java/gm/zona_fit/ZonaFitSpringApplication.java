package gm.zona_fit;

import gm.zona_fit.model.Cliente;
import gm.zona_fit.service.IClienteServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitSpringApplication implements CommandLineRunner {

    @Autowired
    private IClienteServ clienteServ;

    private static final Logger logger = LoggerFactory.getLogger(ZonaFitSpringApplication.class);

    String nl = System.lineSeparator();

    public static void main(String[] args) {
        SpringApplication.run(ZonaFitSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        zonaFitApp();
    }

    private void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);
        while (!salir) {
            var option = mostrarMenu(consola);
            salir = ejecutarOpciones(consola, option);
            logger.info(nl);
        }
    }

    private int mostrarMenu(Scanner consola) {
        logger.info("""
                *** Aplicación Zona Fit (GYM) - Clientes ***
                1. Listar Clientes
                2. Buscar Clientes
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elige una opción: \s""");
        return Integer.parseInt(consola.nextLine());
    }

    private boolean ejecutarOpciones(Scanner consola, int option) {
        var salir = false;
        switch (option) {
            case 1 -> {
                logger.info(nl + " --- Listado de Clientes ---" + nl);
                List<Cliente> clientes = clienteServ.listarCliente();
                clientes.forEach(cliente -> logger.info(cliente.toString() + nl));
            }
            case 2 -> {
                logger.info(nl + " 0_o Buscar Cliente Por Id o_0" + nl);
                logger.info("Id Cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                Cliente cliente = clienteServ.buscarClienteId(idCliente);
                if (cliente != null)
                    logger.info("Cliente encontrado: " + cliente + nl);
                else
                    logger.info("Cliente no encontrado: " + cliente + nl);
            }
            case 3 -> {
                logger.info(" +++ Agregar Cliente +++");
                logger.info("Nombre: ");
                var nombre = consola.nextLine();
                logger.info("Apellido: ");
                var apellido = consola.nextLine();
                logger.info("Membresía: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setMembresia(membresia);
                clienteServ.saveCliente(cliente);
                logger.info("Cliente: " + cliente.getNombre() + " guardado correctamente");
            }
            case 4 -> {
                logger.info(" +++ Modificar Cliente +++");
                logger.info("Id de Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                Cliente cliente = clienteServ.buscarClienteId(idCliente);
                if (cliente != null) {
                    logger.info("Nombre: ");
                    var nombre = consola.nextLine();
                    logger.info("Apellido: ");
                    var apellido = consola.nextLine();
                    logger.info("Membresia: ");
                    var membresia = Integer.parseInt(consola.nextLine());
                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setMembresia(membresia);
                    clienteServ.saveCliente(cliente);
                    logger.info("Cliente: " + cliente.getNombre() + " modificado correctamente");
                } else {
                    logger.info("Cliente no encontrado");
                }
            }
            case 5 -> {
                logger.info(" --- Eliminar Cliente ---");
                logger.info("Id del Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = clienteServ.buscarClienteId(idCliente);
                if (cliente != null) {
                    clienteServ.eliminarCliente(idCliente);
                    logger.info("Cliente eliminado correctamente");
                } else
                    logger.info("No se ha encontrado el cliente");
            }
            case 6 -> {
                logger.info("^_^ ¡Hasta Proto! ^_^" + nl);
                salir = true;
            }
            default -> logger.info("Opción no valida, intente de nuevo.");
        }
        return salir;
    }
}