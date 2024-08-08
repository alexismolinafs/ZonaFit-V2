package gm.zona_fit.service;

import gm.zona_fit.model.Cliente;
import gm.zona_fit.repository.ClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServ implements IClienteServ {

    @Autowired
    private ClienteDao dao;

    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> listaCliente = dao.findAll();
        return listaCliente;
    }

    @Override
    public Cliente buscarClienteId(Integer idCliente) {
        Cliente cliente = dao.findById(idCliente).orElse(null);
        return cliente;
    }

    @Override
    public void saveCliente(Cliente cliente) {
        dao.save(cliente);
    }

    @Override
    public void eliminarCliente(Integer idCliente) {
        dao.deleteById(idCliente);
    }
}